<?php

namespace App\Controller;

use App\Entity\Product;
use App\Repository\ProductRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\ProductType;
use App\Entity\Users;

class ProductController extends AbstractController
{
    /**
     * @Route("/product", name="product")
     */
    public function index(): Response
    {
        return $this->render('product/index.html.twig', [
            'controller_name' => 'ProductController',
        ]);
    }

    /**
     * @Route("/AddProduct", name="AddProduct")
     */
    public function AddProduct(Request $request)
    {
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->add('Add', SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($product);
            $em->flush();
            //
            $request->getRequestFormat();

            return $this->redirectToRoute('Products');


        }
        return $this->render("Product/AddProduct.html.twig",[
            'form'=>$form->createView()
        ]);
    }
    /**
     * @Route("admin/AddProduct", name="adminAddProduct")
     */
    public function adminAddProduct(Request $request)
    {
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
          if(!($actual_user)){return $this->redirectToRoute('userLogin');}
          else if (!($actual_user->getPrivilege() == 0)) {return $this->redirectToRoute('userLogin');}
        }else{
          return $this->redirectToRoute('userLogin');
        }
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($product);
            $em->flush();
            //
            $request->getRequestFormat();

            return $this->redirectToRoute('adminProducts');


        }
        return $this->render("Product/adminAdd.html.twig",[
            'form'=>$form->createView(),
            'actual_user' => $actual_user,
        ]);
    }

    /**
     *
     * @param ProductRepository $repo
     * @Route("/ConsultProducts", name="Productsold")
     * @return Response
     */
    public function ConsultProductsold(ProductRepository $repo ){

        $Products=$repo->findAll();
        return $this->render('Product/Products.html.twig',
            ['Product'=>$Products]);
    }

    /**
     *
     * @param ProductRepository $repo
     * @Route("admin/ConsultProducts", name="adminProducts")
     * @return Response
     */
    public function adminProducts(ProductRepository $repo,Request $request ){
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
          if(!($actual_user)){return $this->redirectToRoute('userLogin');}
          else if (!($actual_user->getPrivilege() == 0)) {return $this->redirectToRoute('userLogin');}
        }else{
          return $this->redirectToRoute('userLogin');
        }

        $Products=$repo->findAll();
        return $this->render('Product/adminProducts.html.twig',
            ['Product'=>$Products,'actual_user' => $actual_user,]);
    }
    /**
     *
     * @param ProductRepository $repo
     * @Route("/products", name="Products")
     * @return Response
     */
    public function Products(ProductRepository $rep,Request $request ){
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
          if(!($actual_user)){return $this->redirectToRoute('userLogin');}
        }else{
          return $this->redirectToRoute('userLogin');
        }
        $d=$request->get('PCat');

        if ($d=='food')
            $products=$rep->findBy(['category'=>0]);

        elseif ($d=='drink')
            $products=$rep->findBy(['category'=>1]);

        elseif ($d=='medical')
            $products=$rep->findBy(['category'=>2]);

        elseif ($d=='other')
            $products=$rep->findBy(['category'=>3]);

        else
            $products=$rep->findAll();

        return $this->render('Product/userProduct.html.twig',
            ['Product'=>$products,'actual_user' => $actual_user,]);
    }
    /**
     *
     * @param ProductRepository $repo
     * @Route("/BrowseProducts", name="Shop")
     * @return Response
     */
    public function BrowseProducts(ProductRepository $repo ){

        $Products=$repo->findAll();
        return $this->render('Product/BrowseProducts.html.twig',
            ['Product'=>$Products]);
    }
    /**
     * @param ProductRepository $rep
     * @param $id
     * @Route("/deleteProduct/{id}",name="deleteProduct")
     * @return Response
     */
    public function DeleteProduct($id,ProductRepository $rep){
        $p=$rep->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($p);
        $em->flush();
        return $this->redirectToRoute('adminProducts');
    }
    /**
     * @param $id
     * @param ProductRepository $rep
     * @param Request $request
     * @Route("/Product/Update/{id}",name="updateProduct")
     * @return Response
     */
    function UpdateProduct($id,ProductRepository $rep,Request $request){
        $p=$rep->find($id);
        $form=$this->createForm(ProductType::class,$p);
        $form->add('Update',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('Products');
        }
        return $this->render("Product/updateProduct.html.twig",[
            'form'=>$form->createView()
        ]);

    }

    /**
     * @param $id
     * @param ProductRepository $rep
     * @param Request $request
     * @Route("admin/Product/Update/{id}",name="adminUpdateProduct")
     * @return Response
     */
    function adminUpdateProduct($id,ProductRepository $rep,Request $request){
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
          if(!($actual_user)){return $this->redirectToRoute('userLogin');}
          else if (!($actual_user->getPrivilege() == 0)) {return $this->redirectToRoute('userLogin');}
        }else{
          return $this->redirectToRoute('userLogin');
        }
        $p=$rep->find($id);
        $form=$this->createForm(ProductType::class,$p);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('adminProducts');
        }
        return $this->render("Product/adminEdit.html.twig",[
            'form'=>$form->createView(),
            'actual_user' => $actual_user,
        ]);

    }

    /**
     *
     * @param ProductRepository $rep
     * @param Request $request
     * @Route("/searchCat",name="searchCat")
     * @return Response
     */
    public function searchCat(Request $request, ProductRepository $rep){
        $data=$request->get('PCat');
        if ($data=='food')
            $products=$rep->findBy(['category'=>0]);

        elseif ($data=='drink')
            $products=$rep->findBy(['category'=>1]);

        elseif ($data=='medical')
            $products=$rep->findBy(['category'=>2]);

        elseif ($data=='other')
            $products=$rep->findBy(['category'=>3]);

        else
            $products=$rep->findAll();

        return $this->render('Product/BrowseProducts.html.twig',['Product'=>$products]);

    }
    /**
     *
     * @param ProductRepository $rep
     * @param Request $request
     * @Route("/searchCategory",name="SearchCatAdmin")
     * @return Response
     */
    public function SearchCatAdmin(Request $request, ProductRepository $rep){

        $d=$request->get('PCat');

        if ($d=='food')
            $products=$rep->findBy(['category'=>0]);

        elseif ($d=='drink')
            $products=$rep->findBy(['category'=>1]);

        elseif ($d=='medical')
            $products=$rep->findBy(['category'=>2]);

        elseif ($d=='other')
            $products=$rep->findBy(['category'=>3]);

        else
            $products=$rep->findAll();

        return $this->render('Product/Products.html.twig',['Product'=>$products]);

    }
}
