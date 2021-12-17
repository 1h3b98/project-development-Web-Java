<?php

namespace App\Controller;

use App\Entity\Cart;
use App\Entity\Users;
use App\Entity\Product;
use App\Form\Cart1Type;
use App\Repository\CartRepository;
use App\Repository\ProductRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/cart")
 */
class CartController extends AbstractController
{
    /**
     * @Route("/", name="cart_index", methods={"GET"})
     */
    public function index(CartRepository $cartRepository): Response
    {
        return $this->render('cart/index.html.twig', [
            'carts' => $cartRepository->findAll(),
        ]);
    }
    /**
     * @Route ("/AddToCart/{id}",name="AddToCart")
     * @param $id
     * @param ProductRepository $repo
     * @param CartRepository $CartRepo
     * @param Request $request
     *
     */
    public function AddToCart($id,ProductRepository $repo, CartRepository $CartRepo,Request $request)
    {
        $product=$repo->find($id);
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
            $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
            if(!($actual_user)){return $this->redirectToRoute('userLogin');}
          }else{
            return $this->redirectToRoute('userLogin');
          }
        $cart = new Cart();
        if ($product!=null){
            $qte = 1;
            $cart->setProductQte($qte);
            $cart->setTotal(($product->getPrice())*$qte);
            $cart->setUser($actual_user);
            $cart->setProducts($product);
            $em = $this->getDoctrine()->getManager();
            $em->persist($cart);
            $em->flush();
        }
        //$cart=$CartRepo->findOneBy(array('user'=>$userID));
        return $this->redirectToRoute('Products');


    }

    /**
     * @Route("/new", name="cart_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $cart = new Cart();
        $form = $this->createForm(Cart1Type::class, $cart);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($cart);
            $entityManager->flush();

            return $this->redirectToRoute('cart_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('cart/new.html.twig', [
            'cart' => $cart,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{cartId}", name="cart_show", methods={"GET"})
     */
    public function show(Cart $cart): Response
    {
        return $this->render('cart/show.html.twig', [
            'cart' => $cart,
        ]);
    }

    /**
     * @Route("/{cartId}/edit", name="cart_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Cart $cart, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Cart1Type::class, $cart);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('cart_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('cart/edit.html.twig', [
            'cart' => $cart,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{cartId}", name="cart_delete", methods={"POST"})
     */
    public function delete(Request $request, Cart $cart, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cart->getCartId(), $request->request->get('_token'))) {
            $entityManager->remove($cart);
            $entityManager->flush();
        }

        return $this->redirectToRoute('cart_index', [], Response::HTTP_SEE_OTHER);
    }
}
