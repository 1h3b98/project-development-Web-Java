<?php
namespace App\Entity;

class Login {
    /**
    * @var string
    */
    private $username;

    /**
    * @var string
    */
    private $password;
    public function getUsername(): ?string
    {
        return $this->username;
    }

    private $captcha;

    public function setUsername(string $username): self
    {
            $this->username = $username;
        return $this;
    }
    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {

            $this->password = hash('sha256', $password);;

        return $this;
    }
}

?>