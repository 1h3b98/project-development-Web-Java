<?php

namespace App\Form;

use App\Entity\Complainttype;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ComplainttypeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('type',TextType::class,[
                'attr' => ['class' => 'form-control' , 'placeholder' => 'type',
                    ]
                    ]
                    )
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Complainttype::class,
        ]);
    }
}
