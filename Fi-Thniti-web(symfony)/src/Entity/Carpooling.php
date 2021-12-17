<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Carpooling
 *
 * @ORM\Table(name="carpooling", indexes={@ORM\Index(name="userid", columns={"userid"})})
 * @ORM\Entity
 */
class Carpooling
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="departure_date", type="date", nullable=false)
     */
    private $departureDate;

    /**
     * @var string
     *
     * @ORM\Column(name="departure_location", type="string", length=50, nullable=false)
     */
    private $departureLocation;

    /**
     * @var string
     *
     * @ORM\Column(name="drop_off_location", type="string", length=50, nullable=false)
     */
    private $dropOffLocation;

    /**
     * @var string
     *
     * @ORM\Column(name="driver_name", type="string", length=20, nullable=false)
     */
    private $driverName;

    /**
     * @var int
     *
     * @ORM\Column(name="phone_number", type="integer", nullable=false)
     */
    private $phoneNumber;

    /**
     * @var int
     *
     * @ORM\Column(name="places_number", type="integer", nullable=false)
     */
    private $placesNumber;

    /**
     * @var string
     *
     * @ORM\Column(name="baggage", type="string", length=3, nullable=false)
     */
    private $baggage;

    /**
     * @var string|null
     *
     * @ORM\Column(name="preference", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $preference = 'NULL';

    /**
     * @var \Users
     *
     * @ORM\ManyToOne(targetEntity="Users")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="userid", referencedColumnName="user_id")
     * })
     */
    private $userid;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDepartureDate(): ?\DateTimeInterface
    {
        return $this->departureDate;
    }

    public function setDepartureDate(\DateTimeInterface $departureDate): self
    {
        $this->departureDate = $departureDate;

        return $this;
    }

    public function getDepartureLocation(): ?string
    {
        return $this->departureLocation;
    }

    public function setDepartureLocation(string $departureLocation): self
    {
        $this->departureLocation = $departureLocation;

        return $this;
    }

    public function getDropOffLocation(): ?string
    {
        return $this->dropOffLocation;
    }

    public function setDropOffLocation(string $dropOffLocation): self
    {
        $this->dropOffLocation = $dropOffLocation;

        return $this;
    }

    public function getDriverName(): ?string
    {
        return $this->driverName;
    }

    public function setDriverName(string $driverName): self
    {
        $this->driverName = $driverName;

        return $this;
    }

    public function getPhoneNumber(): ?int
    {
        return $this->phoneNumber;
    }

    public function setPhoneNumber(int $phoneNumber): self
    {
        $this->phoneNumber = $phoneNumber;

        return $this;
    }

    public function getPlacesNumber(): ?int
    {
        return $this->placesNumber;
    }

    public function setPlacesNumber(int $placesNumber): self
    {
        $this->placesNumber = $placesNumber;

        return $this;
    }

    public function getBaggage(): ?string
    {
        return $this->baggage;
    }

    public function setBaggage(string $baggage): self
    {
        $this->baggage = $baggage;

        return $this;
    }

    public function getPreference(): ?string
    {
        return $this->preference;
    }

    public function setPreference(?string $preference): self
    {
        $this->preference = $preference;

        return $this;
    }

    public function getUserid(): ?Users
    {
        return $this->userid;
    }

    public function setUserid(?Users $userid): self
    {
        $this->userid = $userid;

        return $this;
    }
    public function __toString(){
        $var = strval($this->id);
        return $var;
    }


}