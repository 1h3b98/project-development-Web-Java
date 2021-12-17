<?php

namespace App\Repository;

use App\Entity\Users;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Users|null find($id, $lockMode = null, $lockVersion = null)
 * @method Users|null findOneBy(array $criteria, array $orderBy = null)
 * @method Users[]    findAll()
 * @method Users[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class UsersRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Users::class);
    }

    // /**
    //  * @return Users[] Returns an array of Users objects
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

    
    public function findOneBySomeField($name, $username, $number, $birth)
    {
        
        
        
        $req = $this->createQueryBuilder('u');
        if ($name != ""){
            $name = "%".$name."%";
            $req->andWhere('u.firstName LIKE :name OR u.lastName LIKE :name')
            ->setParameter('name', $name);
        }
        if ($username != ""){
            $username = "%".$username."%";
            $req->andWhere('u.email LIKE :username OR u.username LIKE :username')
            ->setParameter('username', $username);            
        }
        if ($number != ""){
            $number = "%".$number."%";
            $req->andWhere('u.cin LIKE :number OR u.phoneNumber LIKE :number')
            ->setParameter('number', $number);            
        }
        if ($birth != ""){
            $req->andWhere('u.birthDate = :birth')
            ->setParameter('birth', $birth); 
        }
        return $req
            ->orderBy('u.userId', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }

    public function staticPrivilege() {
        return $this->createQueryBuilder('u')
                    ->select('u.privilege , count(u)')
                    ->groupBy('u.privilege')
                    ->getQuery()
                    ->getResult();

    }

}
