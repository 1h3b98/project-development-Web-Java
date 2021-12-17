<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\DateTime;
use Exception;
/**
 * Users
 *
 * @ORM\Table(name="users", uniqueConstraints={@ORM\UniqueConstraint(name="phone_number", columns={"phone_number"}), @ORM\UniqueConstraint(name="cin", columns={"cin"}), @ORM\UniqueConstraint(name="username", columns={"username"}), @ORM\UniqueConstraint(name="email", columns={"email"})})
 * @ORM\Entity(repositoryClass="App\Repository\UsersRepository")
 */
class Users
{
    /**
     * @var int
     *
     * @ORM\Column(name="user_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $userId;

    /**
     * @var string
     *
     * @ORM\Column(name="first_name", type="string", length=50, nullable=false)
     */
    private $firstName;

    /**
     * @var string
     *
     * @ORM\Column(name="last_name", type="string", length=50, nullable=false)
     */
    private $lastName;

    /**
     * @var string
     *
     * @ORM\Column(name="cin", type="string", length=8, nullable=false)
     */
    private $cin;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=50, nullable=false)
     */
    private $username;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=64, nullable=false)
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="phone_number", type="string", length=15, nullable=false)
     */
    private $phoneNumber;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="birth_date", type="date", nullable=false)
     */
    private $birthDate;

    /**
     * @var int|null
     *
     * @ORM\Column(name="privilege", type="integer", nullable=true)
     */
    private $privilege;

    public function getUserId(): ?int
    {
        return $this->userId;
    }

    public function getFirstName(): ?string
    {
        return $this->firstName;
    }

    public function setFirstName(string $firstName): self
    {
        if (preg_match("/^([a-zA-Z' ]+)$/", $firstName)){
            $this->firstName = $firstName;
        }else{
            throw new Exception('Invalid First Name');
        }
       

        return $this;
    }

    public function getLastName(): ?string
    {
        return $this->lastName;
    }

    public function setLastName(string $lastName): self
    {
        if (preg_match("/^([a-zA-Z' ]+)$/", $lastName)){
            $this->lastName = $lastName;
        }else{
            throw new Exception('Invalid Last Name');
        }

        return $this;
    }

    public function getCin(): ?string
    {
        return $this->cin;
    }

    public function setCin(string $cin): self
    {
        if (preg_match("/(?=\b\d{8}\b)(?=^[0-9]*$)(^[0-1])/", $cin)){
            $this->cin = $cin;
        }else{
            throw new Exception('Invalid CIN');
        }
        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        if (filter_var($email, FILTER_VALIDATE_EMAIL)){
            $this->email = $email;
        }else{
            throw new Exception('Invalid Email');
        }
        return $this;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        if (preg_match('/^(?=.{5,19}$)(?=[A-Za-z])[A-Za-z0-9]+(?:\.[A-Za-z0-9]+)*$/', $username)) {
            $this->username = $username;
        }else{
            throw new Exception('Invalid Username');
        }
        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        if ($password != ""){
            $this->password = hash('sha256', $password);
        }else{
            throw new Exception('Invalid Password');
        }
        return $this;
    }

    public function getPhoneNumber(): ?string
    {
        return $this->phoneNumber;
    }

    public function setPhoneNumber(string $phoneNumber): self
    {
        if (preg_match("/(?=\b\d{8}\b)(?=^[0-9]*$)(^[9,5,4,2])/", $phoneNumber)){
            $this->phoneNumber = $phoneNumber;
        }else{
            throw new Exception('Invalid Phone Number');
        }

        return $this;
    }

    public function getBirthDate(): ?\DateTimeInterface
    {
        return $this->birthDate;
    }

    public function setBirthDate(\DateTimeInterface $birthDate): self
    {
        $dateTime = new \DateTime("@{$birthDate->getTimeStamp()}");
        $now = new \DateTime("now");
        $age = $now->diff($dateTime, true)->y;

        if ($age > 18)
        {
            $this->birthDate = $birthDate;
        }else{
            throw new Exception('Under Aged');
        }
        

        return $this;
    }

    public function getPrivilege(): ?int
    {
        return $this->privilege;
    }

    public function setPrivilege(?int $privilege): self
    {
        if (($privilege >= 0) && ($privilege <= 2) ){
            $this->privilege = $privilege;
        }else{
            throw new Exception('Invalid Privilege');
        }
        return $this;
    }

    public function __toString()
{
    return $this->firstName;
}


}
