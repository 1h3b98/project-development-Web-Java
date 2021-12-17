<?php

namespace App\Repository;


use App\Entity\Product;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

class ProductRepository extends ServiceEntityRepository

{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Product::class);
    }
    /**
     * @param $category
     * @return int|mixed|string
     */
    public function findAllByCat($category)
    {
        $em = $this->getEntityManager();
        $query = $em
            ->createQuery('SELECT p FROM App\Entity\Product WHERE p.category LIKE :' % $category % '');
        return $query->getResult();
    }
}