<?php

namespace App\Controller;

use App\Entity\Colistype;
use App\Form\ColistypeType;
use App\Repository\ColistypeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


use App\Entity\Users;
/**
 * @Route("/colistype")
 */
class ColistypeController extends AbstractController
{
    /**
     * @Route("/", name="colistype_index", methods={"GET"})
     */
    public function index(ColistypeRepository $colistypeRepository, Request $request): Response
    {
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth') && UserController::checkAdmin($cookies->get('auth'),$userRepo)){
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        return $this->render('colistype/index.html.twig', [
            'actual_user' => $actual_user,
            'colistypes' => $colistypeRepository->findAll(),
        ]);
    }else
    {
      return $this->redirectToRoute('userLogin');
    }
    }
    /**
     * @Route("/f", name="fcolistype_index", methods={"GET"})
     */
    public function findex(ColistypeRepository $colistypeRepository): Response
    {
        return $this->render('colistype/fronttype.html.twig', [
            'colistypes' => $colistypeRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="colistype_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $colistype = new Colistype();
        $form = $this->createForm(ColistypeType::class, $colistype);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($colistype);
            $entityManager->flush();
            $this->addFlash('success', 'votre Types des Colis Ajoutée avec Success');


            return $this->redirectToRoute('colistype_index');
        }

        return $this->render('colistype/new.html.twig', [
            'colistype' => $colistype,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="colistype_show", methods={"GET"})
     */
    public function show(Colistype $colistype,Request $request): Response
    {
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
          if(!($actual_user)){return $this->redirectToRoute('userLogin');}
        }else{
          return $this->redirectToRoute('userLogin');
        }
        return $this->render('colistype/show.html.twig', [
            'colistype' => $colistype,
            'actual_user' => $actual_user,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="colistype_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Colistype $colistype): Response
    {
        $cookies = $request->cookies;
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth')){
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
          if(!($actual_user)){return $this->redirectToRoute('userLogin');}
        }else{
          return $this->redirectToRoute('userLogin');
        }
        $form = $this->createForm(ColistypeType::class, $colistype);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('colistype_index');
        }

        return $this->render('colistype/edit.html.twig', [
            'colistype' => $colistype,
            'actual_user' => $actual_user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="colistype_delete", methods={"POST"})
     */
    public function delete(Request $request, Colistype $colistype): Response
    {
        if ($this->isCsrfTokenValid('delete'.$colistype->getId(), $request->request->get('_token'))) {
          $entityManager = $this->getDoctrine()->getManager();
	   $entityManager->remove($colistype);
            $entityManager->flush();
            $this->addFlash('success', 'Votre Types des colis a été supprimé');
        }

        return $this->redirectToRoute('colistype_index');
    }


}
