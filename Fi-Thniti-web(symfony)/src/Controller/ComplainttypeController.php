<?php

namespace App\Controller;

use App\Entity\Complainttype;
use App\Form\ComplainttypeType;
use App\Repository\ComplainttypeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/complainttype")
 */
class ComplainttypeController extends AbstractController
{
    /**
     * @Route("/", name="complainttype_index", methods={"GET"})
     */
    public function index(ComplainttypeRepository $complainttypeRepository): Response
    {
        return $this->render('complainttype/index.html.twig', [
            'complainttypes' => $complainttypeRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="complainttype_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $complainttype = new Complainttype();
        $form = $this->createForm(ComplainttypeType::class, $complainttype);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($complainttype);
            $entityManager->flush();

            return $this->redirectToRoute('complainttype_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('complainttype/new.html.twig', [
            'complainttype' => $complainttype,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="complainttype_show", methods={"GET"})
     */
    public function show(Complainttype $complainttype): Response
    {
        return $this->render('complainttype/show.html.twig', [
            'complainttype' => $complainttype,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="complainttype_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Complainttype $complainttype, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ComplainttypeType::class, $complainttype);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('complainttype_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('complainttype/edit.html.twig', [
            'complainttype' => $complainttype,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="complainttype_delete", methods={"POST"})
     */
    public function delete(Request $request, Complainttype $complainttype, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$complainttype->getId(), $request->request->get('_token'))) {
            $entityManager->remove($complainttype);
            $entityManager->flush();
        }

        return $this->redirectToRoute('complainttype_index', [], Response::HTTP_SEE_OTHER);
    }


}
