<?php

namespace App\Repository;

use App\Entity\Carpooling;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Carpooling|null find($id, $lockMode = null, $lockVersion = null)
 * @method Carpooling|null findOneBy(array $criteria, array $orderBy = null)
 * @method Carpooling[]    findAll()
 * @method Carpooling[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CarpoolingRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Carpooling::class);
    }


    public function searchby($departureLocation,$dropOffLocation,$departureDate)
    {
        if($departureLocation!=null&&$dropOffLocation!=null&&$departureDate!=null){
            return $this->createQueryBuilder('c')
            ->andWhere('c.departureLocation LIKE :departureLocation')
            ->setParameter('departureLocation', '%'.$departureLocation.'%')
            ->andWhere('c.dropOffLocation LIKE :dropOffLocation')
            ->setParameter('dropOffLocation', '%'.$dropOffLocation.'%')
            ->andWhere('c.departureDate LIKE :departureDate')
            ->setParameter('departureDate', '%'.$departureDate.'%')
            ->getQuery()
            ->getResult();
        }
        elseif($departureLocation!=null&&$dropOffLocation=null&&$departureDate=null){
            return $this->createQueryBuilder('c')
            ->andWhere('c.departureLocation LIKE :departureLocation')
            ->setParameter('departureLocation', '%'.$departureLocation.'%')
            ->getQuery()
            ->getResult(); 
            
        }
        elseif($departureLocation=null&&$dropOffLocation!=null&&$departureDate=null){
            return $this->createQueryBuilder('u')
            ->andWhere('c.dropOffLocation LIKE : dropOffLocation')
            ->setParameter('dropOffLocation', '%'.$dropOffLocation.'%')
            ->getQuery()
            ->getResult();  
        }
        elseif($departureLocation=null&&$dropOffLocation=null&&$departureDate!=null){
            return $this->createQueryBuilder('u')
            ->andWhere('c.departureDate LIKE : departureDate')
            ->setParameter('departureDate', '%'.$departureDate.'%')
            ->getQuery()
            ->getResult();  
        }
    }
    // /**
    //  * @return Carpooling[] Returns an array of Carpooling objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('u.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Carpooling
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}