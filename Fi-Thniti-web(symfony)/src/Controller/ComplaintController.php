<?php

namespace App\Controller;

use App\Entity\Complaint;
use App\Form\ComplaintType;
use App\Repository\ComplaintRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Crypto\SMimeSigner;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;

use App\Entity\Users;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
/**
 * @Route("/complaint")
 */
class ComplaintController extends AbstractController
{



        /**
     * @Route("/newforuser", name="complaint_new_user", methods={"GET", "POST"})
     */
    public function newComplaint(Request $request, EntityManagerInterface $entityManager, MailerInterface $mailer): Response
    //MailerInterface $mailer
    {
        $complaint = new Complaint();
        $form = $this->createForm(ComplaintType::class, $complaint);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($complaint);
            $entityManager->flush();

            //envoie d email
            $email = (new Email())
               ->from('tabaani.app@gmail.com')

               ->to($complaint->getEmail())
                //->cc('cc@example.com')
                //->bcc('bcc@example.com')
                //->replyTo('fabien@example.com')
                ->priority(Email::PRIORITY_HIGH)
                ->subject('Confirmation de votre réclamation!')
                //->text('Sending emails is fun again!')
                //->html('<p>Bonjour, Nous vous confirmons que votre réclamation a été bien envoyée, vous serez informé dès que cette dernière sera traitée. Bien à vous.</p>');

            ->html('<!doctype html>
            <html>
                <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Confirmation de votre réclamation</title>
                <style>
            @media only screen and (max-width: 620px) {
              table.body h1 {
                font-size: 28px !important;
                margin-bottom: 10px !important;
              }
            
              table.body p,
            table.body ul,
            table.body ol,
            table.body td,
            table.body span,
            table.body a {
                font-size: 16px !important;
              }
            
              table.body .wrapper,
            table.body .article {
                padding: 10px !important;
              }
            
              table.body .content {
                padding: 0 !important;
              }
            
              table.body .container {
                padding: 0 !important;
                width: 100% !important;
              }
            
              table.body .main {
                border-left-width: 0 !important;
                border-radius: 0 !important;
                border-right-width: 0 !important;
              }
            
              table.body .btn table {
                width: 100% !important;
              }
            
              table.body .btn a {
                width: 100% !important;
              }
            
              table.body .img-responsive {
                height: auto !important;
                max-width: 100% !important;
                width: auto !important;
              }
            }
            @media all {
              .ExternalClass {
                width: 100%;
              }
            
              .ExternalClass,
            .ExternalClass p,
            .ExternalClass span,
            .ExternalClass font,
            .ExternalClass td,
            .ExternalClass div {
                line-height: 100%;
              }
            
              .apple-link a {
                color: inherit !important;
                font-family: inherit !important;
                font-size: inherit !important;
                font-weight: inherit !important;
                line-height: inherit !important;
                text-decoration: none !important;
              }
            
              #MessageViewBody a {
                color: inherit;
                text-decoration: none;
                font-size: inherit;
                font-family: inherit;
                font-weight: inherit;
                line-height: inherit;
              }
            
              .btn-primary table td:hover {
                background-color: #34495e !important;
              }
            
              .btn-primary a:hover {
                background-color: #34495e !important;
                border-color: #34495e !important;
              }
            }
            </style>
            </head>
            <body class="" style="background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;">
                <span class="preheader" style="color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;">You complaint is received</span>
            
                <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="body" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f6f6f6; width: 100%;" width="100%" bgcolor="#f6f6f6">
                  <tr>
                    <td style="font-family: sans-serif; font-size: 14px; vertical-align: top;" valign="top">&nbsp;</td>
                    <td class="container" style="font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; max-width: 580px; padding: 10px; width: 580px; margin: 0 auto;" width="580" valign="top">
                      <div class="content" style="box-sizing: border-box; display: block; margin: 0 auto; max-width: 580px; padding: 10px;">
            
                        <!-- START CENTERED WHITE CONTAINER -->
                        <table role="presentation" class="main" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background: #ffffff; border-radius: 3px; width: 100%;" width="100%">
            
                          <!-- START MAIN CONTENT AREA -->
                          <tr>
                            <td class="wrapper" style="font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;" valign="top">
                              <table role="presentation" border="0" cellpadding="0" cellspacing="0" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;" width="100%">
                                <img style="width : 100px" src="https://i.ibb.co/4g4NF3B/Logo-Fauna-et-Flora-sans-titre-avec-logo-crop.png" alt="Logo-Fauna-et-Flora-sans-titre-avec-logo-crop" border="0">
                                <tr>
                                  <td style="font-family: sans-serif; font-size: 14px; vertical-align: top;" valign="top"> 
                                  *******************************************************************
                                   
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">Hello, We confirm that your claim has been sent, you will be informed as soon as it is processed. Yours truly.</p>
                                    <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; box-sizing: border-box; width: 100%;" width="100%">
                                      <tbody>
                                        <tr>
                                          <td align="left" style="font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;" valign="top">
                                            <table role="presentation" border="0" cellpadding="0" cellspacing="0" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;">
                                              <tbody>
                                               
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">You can write to us at any time.</p>
                                   <p>You can visit our website:</p> <a href="/Email" target="_blank">http//:fithniti.tn</a> 
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">Have a nice day. your request will be processed as soon as possible.</p>
                                    
                                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
            
                        <!-- END MAIN CONTENT AREA -->
                        </table>
                        <!-- END CENTERED WHITE CONTAINER -->
            
                        <!-- START FOOTER -->
                        <div class="footer" style="clear: both; margin-top: 10px; text-align: center; width: 100%;">
                          <table role="presentation" border="0" cellpadding="0" cellspacing="0" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;" width="100%">
                            <tr>
                              <td class="content-block" style="font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; color: #999999; font-size: 12px; text-align: center;" valign="top" align="center">
                                <span class="apple-link" style="color: #999999; font-size: 12px; text-align: center;">Company Inc, 3 Abbey Road, San Francisco CA 94102</span>
            
                              </td>
                            </tr>
                            <tr>
                              <td class="content-block powered-by" style="font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; color: #999999; font-size: 12px; text-align: center;" valign="top" align="center">
                                Powered by <a href="http://htmlemail.io" style="color: #999999; font-size: 12px; text-align: center; text-decoration: none;">HTMLemail</a>.
                              </td>
                            </tr>
                          </table>
                        </div>
                        <!-- END FOOTER -->
            
                      </div>
                    </td>
                    <td style="font-family: sans-serif; font-size: 14px; vertical-align: top;" valign="top">&nbsp;</td>
                  </tr>
                </table>
              </body>
          
           </html>');
            $mailer->send($email);

           return $this->redirectToRoute('complaint_new_user', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('complaint/newFront.html.twig', [
            'complaint' => $complaint,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/", name="complaint_index", methods={"GET"})
     */
    public function index(ComplaintRepository $complaintRepository, Request $request): Response /// affichage
    {
      $cookies = $request->cookies;
      $userRepo = $this->getDoctrine()->getRepository(Users::class);
      if ($cookies->has('auth') && UserController::checkAdmin($cookies->get('auth'),$userRepo)){
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        return $this->render('complaint/index.html.twig', [
          'actual_user' => $actual_user,
            'complaints' => $complaintRepository->findAll(),
        ]);
      }else
      {
        return $this->redirectToRoute('userLogin');
      }
    }

    /**
     * @Route("/new", name="complaint_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response //ajout
    {
        $complaint = new Complaint();
        $form = $this->createForm(ComplaintType::class, $complaint);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($complaint);
            $entityManager->flush();

            return $this->redirectToRoute('complaint_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('complaint/new.html.twig', [
            'complaint' => $complaint,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="complaint_show", methods={"GET"})
     */
    public function show(Complaint $complaint,Request $request): Response
    {
      $cookies = $request->cookies;
      $userRepo = $this->getDoctrine()->getRepository(Users::class);
      if ($cookies->has('auth')){
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        if(!($actual_user)){return $this->redirectToRoute('userLogin');}
      }else{
        return $this->redirectToRoute('userLogin');
      }

        return $this->render('complaint/show.html.twig', [
            'complaint' => $complaint,
            'actual_user' => $actual_user,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="complaint_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Complaint $complaint, EntityManagerInterface $entityManager): Response
    {
      $cookies = $request->cookies;
      $userRepo = $this->getDoctrine()->getRepository(Users::class);
      if ($cookies->has('auth')){
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        if(!($actual_user)){return $this->redirectToRoute('userLogin');}
      }else{
        return $this->redirectToRoute('userLogin');
      }
        $form = $this->createForm(ComplaintType::class, $complaint);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('complaint_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('complaint/edit.html.twig', [
            'complaint' => $complaint,
            'actual_user' => $actual_user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="complaint_delete", methods={"POST"})
     */
    public function delete(Request $request, Complaint $complaint, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$complaint->getId(), $request->request->get('_token'))) {
            $entityManager->remove($complaint);
            $entityManager->flush();
        }

        return $this->redirectToRoute('complaint_index', [], Response::HTTP_SEE_OTHER);
    }

     /**
      * @Route("{id}/stat", name="stat_index")
      */
        public function indexAction(Request $request){
          $cookies = $request->cookies;
          $userRepo = $this->getDoctrine()->getRepository(Users::class);
          if (!($cookies->has('auth'))){
            return $this->redirectToRoute('userLogin');
          }
          $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        $repository = $this->getDoctrine()->getRepository(complaint::class);
         $complaint = $repository->findAll();
         $em = $this->getDoctrine()->getManager();

         $srv=0;
         $drv=0;
         $pri=0;


         foreach ($complaint as $complaint)
         {
             if (  $complaint->getComplainttype()=="service")  :

               $srv+=1;

             elseif ($complaint->getComplainttype()=="driver"):

                 $drv+=1;
              else :
                 $pri +=1;

              endif;

         }


     $pieChart = new PieChart();
      $pieChart->getData()->setArrayToDataTable(
         [['type', 'nombre'],
            ['service',     $srv],
             ['driver',      $drv],
             ['price',   $pri]
         ]
     );
       $pieChart->getOptions()->setTitle('Top complaints type');
       $pieChart->getOptions()->setHeight(500);
       $pieChart->getOptions()->setWidth(1500);
       $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
       $pieChart->getOptions()->getTitleTextStyle()->setColor('#FF1493');
       $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
             $pieChart->getOptions()->getTitleTextStyle()->setFontName('Calibri');
            $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

           return $this->render('chart/index.html.twig', array('piechart' => $pieChart, 'actual_user' => $actual_user));
     }



}
