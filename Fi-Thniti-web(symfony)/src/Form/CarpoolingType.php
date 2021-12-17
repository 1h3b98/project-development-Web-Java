<?php

namespace App\Form;

use App\Entity\Carpooling;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CarpoolingType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('departureDate')
            ->add('departureLocation')
            ->add('dropOffLocation')
            ->add('driverName')
            ->add('phoneNumber')
            ->add('placesNumber')
            ->add('baggage')
            ->add('preference')
            
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Carpooling::class,
        ]);
    }
}
