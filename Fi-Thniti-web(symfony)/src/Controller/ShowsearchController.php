<?php

namespace App\Controller;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\CarpoolingRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Carpooling;


/**
 * @Route("showsearch")
 */
class ShowsearchController extends AbstractController
{
    /**
     * @Route("/", name="showsearch",methods={"GET"})
     */
    public function index(CarpoolingRepository $CarpoolingRepository, Request $request):  Response
    {
    

        if(isset($_GET['departureLocation'])) {
            $departureLocation = $_GET['departureLocation'];
        }else{
            $departureLocation = null;
        }
        if(isset($_GET['dropOffLocation'])) {
            $dropOffLocation = $_GET['dropOffLocation'];
        }else{
            $dropOffLocation = null;
        }
        if(isset($_GET['departureDate'])) {
            $departureDate = $_GET['departureDate'];
        }else{
            $departureDate = null;
        }
        
        $results = $CarpoolingRepository->searchby($departureLocation,$dropOffLocation,$departureDate);

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
    
        return $this->render('showsearch/index.html.twig', [
            'result' => $results,
            'searchform' => $searchform->createView(),
            'controller_name' => 'ShowsearchController',
        
            
        ]);
    }
        
    
}
