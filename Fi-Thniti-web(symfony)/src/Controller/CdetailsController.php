<?php

namespace App\Controller;
use App\Entity\Carpooling;
use App\Entity\CDetails;
use App\Form\CDetailsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CarpoolingRepository;

/**
 * @Route("/cdetails")
 */
class CdetailsController extends AbstractController
{
    /**
     * @Route("/", name="cdetails_index", methods={"GET"})
     */
    public function index(): Response
    {
        $cDetails = $this->getDoctrine()
            ->getRepository(CDetails::class)
            ->findAll();

        return $this->render('cdetails/index.html.twig', [
            'c_details' => $cDetails,
        ]);
    }

    /**
     * @Route("/new", name="cdetails_new", methods={"GET","POST"})
     */
    public function new(Request $request, CarpoolingRepository $CarpoolingRepository): Response
    {
        $cDetail = new CDetails();
        $form = $this->createForm(CDetailsType::class, $cDetail);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            
            $lastentity = $CarpoolingRepository->findBy(array(),array('id'=>'DESC'),1,0);
            $em= $lastentity[0];
            $entityManager = $this->getDoctrine()->getManager();
            $cDetail->setDetail($em);
            $entityManager->persist($cDetail);
            $entityManager->flush();

            return $this->redirectToRoute('carpooling_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('cdetails/new.html.twig', [
            'c_detail' => $cDetail,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/fnew", name="fcdetails_new", methods={"GET","POST"})
     */
    public function fnew(Request $request, CarpoolingRepository $CarpoolingRepository): Response
    {
        $cDetail = new CDetails();
        $form = $this->createForm(CDetailsType::class, $cDetail);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            
            $lastentity = $CarpoolingRepository->findBy(array(),array('id'=>'DESC'),1,0);
            $em= $lastentity[0];
            $entityManager = $this->getDoctrine()->getManager();
            $cDetail->setDetail($em);
            $entityManager->persist($cDetail);
            $entityManager->flush();

            return $this->redirectToRoute('fcarpooling_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('cdetails/new.html.twig', [
            'c_detail' => $cDetail,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{detail}", name="cdetails_show", methods={"GET"})
     */
    public function show(CDetails $cDetail): Response
    {
        return $this->render('cdetails/show.html.twig', [
            'c_detail' => $cDetail,
            
        ]);
    }

    /**
     * @Route("/{detail}/edit", name="cdetails_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, CDetails $cDetail): Response
    {
        $form = $this->createForm(CDetailsType::class, $cDetail);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('carpooling_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('cdetails/edit.html.twig', [
            'c_detail' => $cDetail,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/{detail}/fedit", name="fcdetails_edit", methods={"GET","POST"})
     */
    public function fedit(Request $request, CDetails $cDetail): Response
    {
        $form = $this->createForm(CDetailsType::class, $cDetail);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('fcarpooling_index2', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('cdetails/fedit.html.twig', [
            'c_detail' => $cDetail,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{detail}", name="cdetails_delete", methods={"POST"})
     */
    public function delete(Request $request, CDetails $cDetail): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cDetail->getDetail(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cDetail);
            $entityManager->flush();
        }

        return $this->redirectToRoute('cdetails_index', [], Response::HTTP_SEE_OTHER);
    }
}
