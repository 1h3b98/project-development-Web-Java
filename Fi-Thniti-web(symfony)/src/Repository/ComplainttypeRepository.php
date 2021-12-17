<?php

namespace App\Repository;

use App\Entity\Complainttype;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Complainttype|null find($id, $lockMode = null, $lockVersion = null)
 * @method Complainttype|null findOneBy(array $criteria, array $orderBy = null)
 * @method Complainttype[]    findAll()
 * @method Complainttype[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ComplainttypeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Complainttype::class);
    }

    // /**
    //  * @return Complainttype[] Returns an array of Complainttype objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Complainttype
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
