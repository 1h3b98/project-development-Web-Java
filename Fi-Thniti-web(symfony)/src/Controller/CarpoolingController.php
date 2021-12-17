<?php

namespace App\Controller;

use App\Entity\Carpooling;
use App\Entity\CDetails;
use App\Form\CarpoolingType;
use App\Form\CDetailsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Users;
use App\Repository\CarpoolingRepository;
use Doctrine\ORM\Mapping\Id;

/**
 * @Route("/carpooling")
 */
class CarpoolingController extends AbstractController
{
    /**
     * @Route("/", name="carpooling_index", methods={"GET"})
     */
    public function index(Request $request): Response
    {
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);

        if ($cookies->has('auth') && UserController::checkAdmin($cookies->get('auth'),$userRepo)){
            $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        $carpoolings = $this->getDoctrine()
            ->getRepository(Carpooling::class)
            ->findAll();
    
            $searchform = $this->createFormBuilder(null)
                ->add('departureLocation')
                ->add('dropOffLocation')
                ->add('departureDate')
                ->getForm();
        
            $searchform->handleRequest($request);
        
            if ($searchform->isSubmitted() && $searchform->isValid()) {
                $data = $searchform->getData();
                    
                return $this->redirectToRoute('showsearch', $data);
            }

        return $this->render('carpooling/index.html.twig', [
            'carpoolings' => $carpoolings,
            'searchform' => $searchform->createView(),
            'actual_user' => $actual_user
        ]);
    }else{
        return $this->redirectToRoute('userLogin');
    }

    }

    /**
     * @Route("/f", name="fcarpooling_index", methods={"GET"})
     */
    public function findex(Request $request): Response
    {
        $carpoolings = $this->getDoctrine()
            ->getRepository(Carpooling::class)
            ->findAll();
        $cDetails = $this->getDoctrine()
            ->getRepository(CDetails::class)
            ->findAll();

            $searchform = $this->createFormBuilder(null)
            ->add('departureLocation')
            ->add('dropOffLocation')
            ->add('departureDate')
            ->getForm();
    
            $searchform->handleRequest($request);
    
            if ($searchform->isSubmitted() && $searchform->isValid()) {
                $data = $searchform->getData();
                
                return $this->redirectToRoute('showsearch', $data);
            }

        return $this->render('fcarpooling/findex.html.twig', [
            'carpoolings' => $carpoolings,
            'c_details' => $cDetails,
            'searchform' => $searchform->createView()
        ]);
    }

     /**
     * @Route("/f2", name="fcarpooling_index2", methods={"GET"})
     */
    public function findex2(Request $request): Response
    {
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
            $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
            var_dump($actual_user->getUserId());
        
        $carpoolings = $this->getDoctrine()
            ->getRepository(Carpooling::class)
            ->findBy(['userid' => $actual_user->getUserId()]);
        $cDetails = $this->getDoctrine()
            ->getRepository(CDetails::class)
            ->findAll();

            $cDetails = array_filter($cDetails, function($cdet) use($actual_user){
                return ($cdet->getDetail()->getUserid()->getUserId() == $actual_user->getUserId());
            });

        return $this->render('fcarpooling/findex2.html.twig', [
            'carpoolings' => $carpoolings,
            'c_details' => $cDetails,
        ]);
    }
    }

     /**
     * @Route("/home", name="fcarpooling_home", methods={"GET"})
     */
    public function home(Request $request): Response
    {
        $carpoolings = $this->getDoctrine()
            ->getRepository(Carpooling::class)
            ->findAll();
        $cDetails = $this->getDoctrine()
            ->getRepository(CDetails::class)
            ->findAll();
        return $this->render('fcarpooling/home.html.twig', [
            'carpoolings' => $carpoolings,
            'c_details' => $cDetails,

        ]);
    }

    /**
     * @Route("/new", name="carpooling_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $carpooling = new Carpooling();
        $form = $this->createForm(CarpoolingType::class, $carpooling);
        
        $form->handleRequest($request);
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        if ($form->isSubmitted() && $form->isValid() && $actual_user) {
            $entityManager = $this->getDoctrine()->getManager();
            /**$user=$this->get('security.token_storage')->getToken()->getUser();
            $users = new Users();
            $users->sui(2);**/
            $carpooling->setUserid($actual_user);
            $entityManager->persist($carpooling);
            $entityManager->flush();

            return $this->redirectToRoute('cdetails_new', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('carpooling/new.html.twig', [
            'carpooling' => $carpooling,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/fnew", name="fcarpooling_new", methods={"GET","POST"})
     */
    public function fnew(Request $request): Response
    {
        $carpooling = new Carpooling();
        $form = $this->createForm(CarpoolingType::class, $carpooling);
        $form->handleRequest($request);
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
            $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
            
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            /**$user=$this->get('security.token_storage')->getToken()->getUser();
            $users = new Users();
            $users->sui(2);*/
            $carpooling->setUserid($actual_user);
            $entityManager->persist($carpooling);
            $entityManager->flush();

            return $this->redirectToRoute('fcdetails_new', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('fcarpooling/fnew.html.twig', [
            'carpooling' => $carpooling,
            'form' => $form->createView(),
        ]);
        }else
      {
        return $this->redirectToRoute('userLogin');
      }
    }


    /**
     * @Route("/{id}", name="carpooling_show", methods={"GET"})
     */
    public function show(Carpooling $carpooling,CDetails $cDetail): Response
    {
        return $this->render('carpooling/show.html.twig', [
            'carpooling' => $carpooling,
            'c_detail' => $cDetail,
        ]);
    }
    /**
     * @Route("/f/{id}", name="fcarpooling_show", methods={"GET"})
     */
    public function fshow(Carpooling $carpooling,CDetails $cDetail): Response
    {
        return $this->render('fcarpooling/fshow.html.twig', [
            'carpooling' => $carpooling,
            'c_detail' => $cDetail,
            
        ]);
    }

    /**
     * @Route("/{id}/edit", name="carpooling_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Carpooling $carpooling): Response
    {
        $form = $this->createForm(CarpoolingType::class, $carpooling);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('carpooling_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('carpooling/edit.html.twig', [
            'carpooling' => $carpooling,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/f/{id}/edit", name="fcarpooling_edit", methods={"GET","POST"})
     */
    public function fedit(Request $request, Carpooling $carpooling,CDetails $cDetail): Response
    {
        $form = $this->createForm(CarpoolingType::class, $carpooling);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('fcarpooling_index2', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('fcarpooling/fedit.html.twig', [
            'carpooling' => $carpooling,
            'c_detail' => $cDetail,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="carpooling_delete", methods={"POST"})
     */
    public function delete(Request $request, Carpooling $carpooling): Response
    {
        if ($this->isCsrfTokenValid('delete'.$carpooling->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($carpooling);
            $entityManager->flush();
        }

        return $this->redirectToRoute('carpooling_index', [], Response::HTTP_SEE_OTHER);
    }

    // function to change the address to lat and long
function get_lat_long($address){

    $address = str_replace(" ", "+", $address);

    $json = file_get_contents("http://maps.google.com/maps/api/geocode/json?address=$address&sensor=false&region=$region");
    $json = json_decode($json);

    $lat = $json->{'results'}[0]->{'geometry'}->{'location'}->{'lat'};
    $long = $json->{'results'}[0]->{'geometry'}->{'location'}->{'lng'};
    return $lat.','.$long;
}

}

