<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * CDetails
 *
 * @ORM\Table(name="c_details")
 * @ORM\Entity
 */
class CDetails
{
    /**
     * @var int
     *
     * @ORM\Column(name="driver_cin", type="integer", nullable=false)
     */
    private $driverCin;

    /**
     * @var bool
     *
     * @ORM\Column(name="music", type="boolean", nullable=false)
     */
    private $music;

    /**
     * @var bool
     *
     * @ORM\Column(name="climatisation", type="boolean", nullable=false)
     */
    private $climatisation;

    /**
     * @var \Carpooling
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Carpooling")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="detail_id", referencedColumnName="id")
     * })
     */
    private $detail;

    public function getDriverCin(): ?int
    {
        return $this->driverCin;
    }

    public function setDriverCin(int $driverCin): self
    {
        $this->driverCin = $driverCin;

        return $this;
    }

    public function getMusic(): ?bool
    {
        return $this->music;
    }

    public function setMusic(bool $music): self
    {
        $this->music = $music;

        return $this;
    }

    public function getClimatisation(): ?bool
    {
        return $this->climatisation;
    }

    public function setClimatisation(bool $climatisation): self
    {
        $this->climatisation = $climatisation;

        return $this;
    }

    public function getDetail(): ?Carpooling
    {
        return $this->detail;
    }

    public function setDetail(?Carpooling $detail): self
    {
        $this->detail = $detail;

        return $this;
    }


}