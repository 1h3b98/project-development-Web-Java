<?php

namespace App\Controller;

use App\Entity\Users;
use App\Entity\Login;
use App\Repository\UsersRepository;
use App\Form\UserType;
use App\Form\LoginType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\HttpFoundation\Cookie;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManagerInterface;
use Exception;

use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
class UserController extends AbstractController
{
    /**
     * @Route("/users", name="user")
     */
    public function index(UsersRepository $repo, Request $request){
      $cookies = $request->cookies;
      $erreur = "";
      $userRepo = $this->getDoctrine()->getRepository(Users::class);
      if ($cookies->has('auth')){
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);        
        if ($request->isMethod('post')){
          $firstname = $request->request->get('firstname');
          $lastname = $request->request->get('lastname');
          $email = $request->request->get('email');
          $username = $request->request->get('username');
          $phonenumber = $request->request->get('phonenumber');
          $birthday = $request->request->get('birthday');
          $password = $request->request->get('password_check');
          $password_check = $request->request->get('password');
          $erreur = "";          
          try {
            if ($password != $password_check) throw new Exception("Password does not match");
            $actual_user->setFirstName($firstname);
            $actual_user->setLastName($lastname);
            $actual_user->setPhoneNumber($phonenumber);
            $actual_user->setEmail($email);
            $old_username = $actual_user->getUsername();
            $actual_user->setUsername($username);
            $old_password = $actual_user->getPassword();    
            $actual_user->setPassword($password);
            $actual_user->setPhoneNumber($phonenumber);
            $actual_user->setBirthDate(new \DateTime($birthday));
            $em=$this->getDoctrine()->getManager();
            $em->persist($actual_user);
            $em->flush();
            if ($old_password != $actual_user->getPassword() || $old_username != $actual_user->getUsername()){
               return $this->redirectToRoute('logout');
            }else{
                return $this->render("user/front-user.html.twig",['actual_user' => $actual_user, 'erreur' =>$erreur ]); 
            }

        }catch(\Exception $e){
            $erreur = $e->getMessage();
        }
        var_dump($erreur);
        return $this->render("user/front-user.html.twig",['actual_user' => $actual_user, 'erreur' =>$erreur]); 
        }else {
          return $this->render("user/front-user.html.twig",['actual_user' => $actual_user, 'erreur' =>$erreur]);
        }
        



      }else
      {
        return $this->redirectToRoute('userLogin');
      }
    }
    /**
     * @Route("/soon", name="soon")
     */
    public function soon(){
      var_dump("coming");
      return $this->render("soon.html.twig",[]);
  }
    /**
     * @Route("/logout", name="logout")
     */
    public function logout(Request $request){
        $cookies = $request->cookies;
        if ($cookies->has('auth')){
            $response = new Response();
            $response = $this->render('admin/logout.html.twig',[]);
            $response->headers->clearCookie('auth');
            return $response;
        }else{
            return $this->redirectToRoute('userLogin');
        }
    }

    /**
     * @Route("Affiche/",name="A")
     */
    function Affiche(UsersRepository $repo, Request $request){
        //$name = $request->query->get('name');
        //$users=$repo->findOneBySomeField($name);
        //return $this->render('admin/Affiche.html.twig',
        //['c'=>$users]);
        return $this->redirectToRoute('userLogin');
    }

    /**
     * @Route ("Delete/{id}",name="D")
     */
    function remove($id,UsersRepository $repository){
        $user=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('adminUsers');

    }
    /**
     * @param Request $request
     * @return Response
     * @Route ("Ajout/", name="AddUser")
     */
    function ajout(Request $request){
        $user=new Users();
        $erreur = "";
        $form=$this->createForm(UserType::class,$user, );
        $form->add('birthDate',DateType::Class, array(
                 'widget' => 'choice',
                 'years' => range(date('Y')-130, date('Y')-18),
                 'months' => range(1, 12),
                 'days' => range(1, 31),
               ));
        $form->add('Ajout',SubmitType::class);
        try {
            $form->handleRequest($request);
            if($form->isSubmitted() &&$form->isValid() ){
                $em=$this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();
                return $this->redirectToRoute('adminUsers');
            }
        }
        catch(\Exception $e){
            var_dump($e->getMessage());
            $erreur = $e->getMessage();
            //error_log($e->getMessage());
        }
        return $this->render("user/Ajout.html.twig",
        ['f'=>$form->createView(), 'e' => $erreur]);
    }

    /**
     * @Route ("Update/{id}",name="U")
     */
    function Update(Request $request,UsersRepository $repository,$id){
        $user=$repository->find($id);
        $form=$this->createForm(UserType::class,$user);
        $form->add('birthDate',DateType::Class, array(
            'widget' => 'choice',
            'years' => range(date('Y')-130, date('Y')-18),
            'months' => range(1, 12),
            'days' => range(1, 31),
          ));
        $form->add('Update',SubmitType::class);
        try {
            $form->handleRequest($request);
            if($form->isSubmitted() &&$form->isValid() ){
                $em=$this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();
                return $this->redirectToRoute('adminUsers');
            }
        }
        catch(\Exception $e){
            var_dump($e->getMessage());
            $erreur = $e->getMessage();
            //error_log($e->getMessage());
        }
        return $this->render("user/Ajout.html.twig",
            ['f'=>$form->createView()]);

    }

    /**
     * @Route("/admin/PasswordReset", name="adminReset")
     */
    public function adminReset(MailerInterface $mailer,Request $request, UsersRepository $repository){
        function generateRandomString($length = 10) {
            $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
            $charactersLength = strlen($characters);
            $randomString = '';
            for ($i = 0; $i < $length; $i++) {
                $randomString .= $characters[rand(0, $charactersLength - 1)];
            }
            return $randomString;
        }
        $erreur = "";
        $username = $request->request->get('username');
        if ($username != ""){
            $user = $repository->findOneBy(['username' => $username]);
            if (!$user){
                $user = $repository->findOneBy(['email' => $username]);
            }
            if (!$user){
                $response = $this->render('admin/adminReset.html.twig',['erreurmsg'=>'User not Found']);
            }else {
                try {
                    $newPassword = generateRandomString();
                    $this->sendEmail($mailer, $user->getEmail(), $user->getFirstName(),$newPassword);
                    $user->setPassword($newPassword);
                    $em=$this->getDoctrine()->getManager();
                    $em->persist($user);
                    $em->flush();
                    $response = $this->render('admin/adminReset.html.twig',['erreurmsg'=>'New password sent to your mail : '.$user->getEmail()]);
                } catch (\Exception $e) {
                    $erreur = $e->getMessage();
                    $response = $this->render('admin/adminReset.html.twig',['erreurmsg'=>$erreur]);
                }
            }
        }else {
            $response = $this->render('admin/adminReset.html.twig',['erreurmsg'=>'']);
        }
        return $response;
    }

    /**
     * @Route("admin/profile/{id}", name="adminProfile")
     */
    public function adminProfile(Request $request,UsersRepository $repo ,$id){

        $cookies = $request->cookies;
        $statistic = $repo->staticPrivilege();
        $stat_sum = $statistic[0][1]+ $statistic[1][1]+ $statistic[2][1];
        //var_dump($statistic);
        $chart = "[]";
        if ($statistic){
          $chart = '[{y: '.round(($statistic[0][1]/$stat_sum)*100, 2).', label: "Admin"},
          {y: '.round(($statistic[1][1]/$stat_sum)*100, 2).', label: "Users"},
          {y: '.round(($statistic[2][1]/$stat_sum)*100,2).', label: "Drivers"}]';
        }
        //$chart = json_encode($statistic);

        
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth') && UserController::checkAdmin($cookies->get('auth'),$userRepo)){
            $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
            $update_user = $userRepo->find($id);


            
            $firstname = $request->request->get('firstname');
            $lastname = $request->request->get('lastname');
            $email = $request->request->get('email');
            $username = $request->request->get('username');
            $cin = $request->request->get('cin');
            $phonenumber = $request->request->get('phonenumber');
            $birthday = $request->request->get('birthday');
            $password = $request->request->get('password');
            $str_privilege = $request->request->get('privilege');
            $erreur = "";
            if ($request->isMethod('post')){
                try {
                    $update_user->setFirstName($firstname);
                    $update_user->setLastName($lastname);
                    $update_user->setCin($cin);
                    $update_user->setEmail($email);
                    $update_user->setUsername($username);
                    $old_password = $update_user->getPassword();
                    $update_user->setPassword($password);
                    $update_user->setPhoneNumber($phonenumber);
                    $update_user->setBirthDate(new \DateTime($birthday));
                    if ($str_privilege == "Admin")$update_user->setPrivilege(0);
                    if ($str_privilege == "User")$update_user->setPrivilege(1);
                    if ($str_privilege == "Driver")$update_user->setPrivilege(2);
                    $em=$this->getDoctrine()->getManager();
                    $em->persist($update_user);
                    $em->flush();
                    if ($old_password != $update_user->getPassword() && $actual_user->getUserId() == $update_user->getUserId()){
                       return $this->redirectToRoute('logout');
                    }else{
                        return $this->render('admin/profile.html.twig',
                        ['update_user'=> $update_user, 'actual_user' => $actual_user, 'erreur' => $erreur, 'chart' =>$chart]); 
                    }
    
                }catch(\Exception $e){
                    $erreur = $e->getMessage();
                }
                return $this->render('admin/profile.html.twig',
                ['update_user'=> $update_user,'actual_user' => $actual_user, 'erreur' => $erreur, 'chart' => $chart]);  
            }else{
                return $this->render('admin/profile.html.twig',
                ['update_user'=> $update_user,'actual_user' => $actual_user, 'erreur' => $erreur, 'chart' => $chart]);            
             }

        
        }else{
            return $this->redirectToRoute('userLogin');
        }
    }

    
    /**
     * @Route("admin/users", name="adminUsers")
     */
    public function adminUsers(UsersRepository $repo, Request $request){
        $cookies = $request->cookies;
        
        $userRepo = $this->getDoctrine()->getRepository(Users::class);
        if ($cookies->has('auth') && UserController::checkAdmin($cookies->get('auth'),$userRepo)){
        $actual_user = UserController::getConnectedUserInfo($cookies->get('auth'),$userRepo);
        $s_name = $request->query->get('name');
        $s_username = $request->query->get('username');
        $s_number = $request->query->get('number');
        $s_birth = $request->query->get('birth');

        $firstname = $request->request->get('firstname');
        $lastname = $request->request->get('lastname');
        $email = $request->request->get('email');
        $username = $request->request->get('username');
        $cin = $request->request->get('cin');
        $phonenumber = $request->request->get('phonenumber');
        $birthday = $request->request->get('birthday');
        $password = $request->request->get('password');
        $str_privilege = $request->request->get('privilege');

        $erreur = "";
        if ($request->isMethod('post')){
            $user = new Users();
            try {
                $user->setFirstName($firstname);
                $user->setLastName($lastname);
                $user->setCin($cin);
                $user->setEmail($email);
                $user->setUsername($username);
                $user->setPassword($password);
                $user->setPhoneNumber($phonenumber);
                $user->setBirthDate(new \DateTime($birthday));
                if ($str_privilege == "Admin")$user->setPrivilege(0);
                if ($str_privilege == "User")$user->setPrivilege(1);
                if ($str_privilege == "Driver")$user->setPrivilege(2);
                $em=$this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();

                return $this->render('admin/Users.html.twig',
                ['actual_user' => $actual_user,'c'=>$users, 'erreur' => $erreur, 'addShow'=>False,'searchvalues' => ["", $email, "", ""]]);
                
            }catch(\Exception $e){
                $erreur = $e->getMessage();
            }
            return $this->render('admin/Users.html.twig',
            ['actual_user' => $actual_user,'c'=>$users, 'erreur' => $erreur, 'addShow'=>True,'searchvalues' => [$s_name, $s_username, $s_number, $s_birth], 'formvalues' => [$firstname, $lastname,$email, $username, $cin, $phonenumber, $birthday, $password, $str_privilege]]);
        }else{
            $users=$repo->findOneBySomeField($s_name, $s_username, $s_number, $s_birth);
            return $this->render('admin/Users.html.twig',
            ['actual_user' => $actual_user,'c'=>$users,'erreur' => $erreur,'addShow'=>False, 'searchvalues' => [$s_name, $s_username, $s_number, $s_birth ]]);
        }
    }else{
        return $this->redirectToRoute('userLogin');
    }

    }
    
    /**
     * @Route("/admin", name="adminLogin")
     */
    public function adminLogin(Request $request, UsersRepository $repository, MailerInterface $mailer){
        $response = new Response();
        $cookies = $request->cookies;
        $cookie = null;
        $erreur = "";
        $login = new Login();
        $form=$this->createForm(LoginType::class,$login);
        $form->handleRequest($request);
        if ($form->isSubmitted() &&$form->isValid() && $login){
            $user = $repository->findOneBy(['username' => $login->getUsername(), 'password' => $login->getPassword()]);
            if ($user){
                if ($user->getPrivilege() == 0){
                    $cookie = new Cookie('auth',base64_encode($user->getUserId()."|".$user->getUsername()."|".$user->getPassword()),time() + (2 * 60 * 60));  // Expires 2 Hours.
                    $response = $this->redirectToRoute('adminUsers');
                    $response->headers->setCookie($cookie);
                    return $response;
                }else {
                    $erreur = "Unauthorized..Logging out";
                }

            }else{
                $erreur = " Wrong input";
            }
        }else {
            if ($cookies->has('auth'))
            {
                $auth = base64_decode($cookies->get('auth'));
                $auth_splitted = explode("|", $auth, 3);
                $len_auth = count($auth_splitted);
                if ($len_auth == 3){
                    $user = $repository->findOneBy(['username' => $auth_splitted[1], 'password' => $auth_splitted[2]]);
                    if ($user){
                        if ($user->getPrivilege() == 0){
                            return $this->redirectToRoute('adminUsers');
                        }else{
                            $erreur = "Unauthorized..Logging out";
                        }                        
                    }
                }
            }
        }
        if ($form->isSubmitted() && !($form->isValid())) {
            $erreur = "Erreur Captcha";
        
        }
        $response = $this->render('admin/adminLogin.html.twig',['erreurmsg'=>$erreur,'form'=> $form->createView()]);
        if ($cookie) {
            $response->headers->setCookie($cookie);
        }else{
            $response->headers->clearCookie('auth');
        }
        
        return $response;
        
    }
    /**
     * @Route("/", name="userLogin")
     */
    public function userLogin(Request $request, UsersRepository $repository){
      $response = new Response();
      $cookies = $request->cookies;
      $cookie = null;
      $erreur = "";
      $erreur2msg = "";
      $login = new Login();
      $user = new Users();
      $user_new = new Users();
      $form_new=$this->createForm(UserType::class,$user_new );
      $form_new->add('birthDate',DateType::Class, array(
               'widget' => 'choice',
               'years' => range(date('Y')-130, date('Y')-18),
               'months' => range(1, 12),
               'days' => range(1, 31),
             ));
      $form=$this->createForm(LoginType::class,$login);
      $form->remove('captcha');
      $form->handleRequest($request);

      if ($form->isSubmitted() && $login){
          $user = $repository->findOneBy(['username' => $login->getUsername(), 'password' => $login->getPassword()]);
          if (!$user){
            $user = $repository->findOneBy(['email' => $login->getUsername(), 'password' => $login->getPassword()]);
          }
          if ($user){
              if ($user->getPrivilege() == 0){
                  $cookie = new Cookie('auth',base64_encode($user->getUserId()."|".$user->getUsername()."|".$user->getPassword()),time() + (2 * 60 * 60));  // Expires 2 Hours.
                  $response = $this->redirectToRoute('userLogin');
                  $response->headers->setCookie($cookie);
                  return $response;
              }else if ($user->getPrivilege() == 1){
                $cookie = new Cookie('auth',base64_encode($user->getUserId()."|".$user->getUsername()."|".$user->getPassword()),time() + (2 * 60 * 60));  // Expires 2 Hours.
                $response = $this->redirectToRoute('fcarpooling_home');
                $response->headers->setCookie($cookie);
                return $response;
            }else
              
              {
                  $erreur = "Unauthorized..Logging out";
              }

          }else{
              $erreur = " Wrong input";
          }
      }else if ($cookies->has('auth')){
              $auth = base64_decode($cookies->get('auth'));
              $auth_splitted = explode("|", $auth, 3);
              $len_auth = count($auth_splitted);
              if ($len_auth == 3){
                  $user = $repository->findOneBy(['username' => $auth_splitted[1], 'password' => $auth_splitted[2]]);
                  if ($user){
                      if ($user->getPrivilege() == 1){
                          return $this->redirectToRoute('fcarpooling_home');
                      }else if ($user->getPrivilege() == 0){
                         return $this->redirectToRoute('adminUsers');
                      }else{
                        $erreur = "Driver interface not set yet";
                      }                     
                  }
              }
      }else{
        try {
          $form_new->handleRequest($request);
          if($form_new->isSubmitted() && $form_new->isValid() ){
              $em=$this->getDoctrine()->getManager();
              $user_new->setPrivilege(1);
              $password_check = $request->request->get('confirm_password');
              if (hash("sha256",$password_check) != $user_new->getPassword()) throw new Exception("Please type the same password");
              $em->persist($user_new);
              $em->flush();
              return $this->redirectToRoute('userLogin');
          }
      }
      catch(\Exception $e){
          var_dump($user_new->getUsername().$e->getMessage());
          $erreur2msg = $e->getMessage();
          //error_log($e->getMessage());
      }
      }

      if ($form_new->isSubmitted() && !($form_new->isValid())) {
        $erreur2msg = "Erreur Captcha"; 
    }
      $response = $this->render('user/login.html.twig',['erreurmsg'=>$erreur,'form'=> $form->createView(), 'form_new'=> $form_new->createView(), 'erreur2msg' => $erreur2msg]);
      if ($cookie) {
          $response->headers->setCookie($cookie);
      }else{
          $response->headers->clearCookie('auth');
      }
      
      return $response;
      
  }






    public static function checkAdmin(String $authHash,UsersRepository $repository  ){
        $auth = base64_decode($authHash);
        $auth_splitted = explode("|", $auth, 3);
        $len_auth = count($auth_splitted);
        if ($len_auth == 3){
            $user = $repository->findOneBy(['username' => $auth_splitted[1], 'password' => $auth_splitted[2]]);
            if ($user){
                if ($user->getPrivilege() == 0){
                    return True;
                }else{
                    return False;
                }                        
            }
        }return False;
    }
    public static function getConnectedUserInfo(String $authHash,UsersRepository $repository  ):?Users{
        $auth = base64_decode($authHash);
        $auth_splitted = explode("|", $auth, 3);
        $len_auth = count($auth_splitted);
        if ($len_auth == 3){
            $user = $repository->findOneBy(['username' => $auth_splitted[1], 'password' => $auth_splitted[2]]);
            if ($user){
                return $user;                       
            }
        }
        $tuser =  new Users();
        return $tuser;
    }
    public function sendEmail(MailerInterface $mailer, String $destination, String $name, String $new_password)
    {
        $email = (new Email())
            ->from('fithniti@outlook.com')
            ->to($destination)
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            ->priority(Email::PRIORITY_HIGH)
            ->subject('Password Reset')
            ->html('<!doctype html>
            <html>
              <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Password Reset</title>
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
                <span class="preheader" style="color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;">Your new password is ready</span>
            
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
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">Hi '.$name.',</p>
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">This is your new password.</p>
                                    <table role="presentation" border="0" cellpadding="0" cellspacing="0" class="btn btn-primary" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; box-sizing: border-box; width: 100%;" width="100%">
                                      <tbody>
                                        <tr>
                                          <td align="left" style="font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;" valign="top">
                                            <table role="presentation" border="0" cellpadding="0" cellspacing="0" style="border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;">
                                              <tbody>
                                                <tr>
                                                  <td style="font-family: sans-serif; font-size: 14px; vertical-align: top; border-radius: 5px; text-align: center; background-color: #3498db;" valign="top" align="center" bgcolor="#3498db"> 
                                                  <a target="_blank" style="border: solid 1px #3498db; border-radius: 5px; box-sizing: border-box; cursor: pointer;
                                                  display: inline-block; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-decoration: none;
                                                  text-transform: capitalize; background-color: #3498db; border-color: #3498db; color: #ffffff;">'.$new_password.'</a> </td>
                                                </tr>
                                              </tbody>
                                            </table>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </table>
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">Fell free to change it from your profile settings.</p>
                                    <p style="font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;">Good luck! Hope it works.</p>
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
            </html>
            ');

        $mailer->send($email);
    }
}








