<?php

namespace App\Form;

use App\Entity\Complaint;
use App\Entity\Complainttype as EntityComplainttype;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Doctrine\DBAL\Types\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType as TypeIntegerType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;

class ComplaintType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('object',TextType::class,[
                'attr' => ['class' => 'form-control' , 'placeholder' => 'objet',
                    ]
            ])
            ->add('description',TextType::class,[
                'attr' => ['class' => 'form-control' , 'placeholder' => 'description',
                    ]
                    ]
                    )
            ->add('email',EmailType::class,[
                'attr' => ['class' => 'form-control' , 'placeholder' => 'email',
                    ]])
            ->add('complainttype',EntityType::class,[
                'class'=>EntityComplainttype::class,
                'attr' => ['class' => 'form-control' 
                    ]])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Complaint::class,
        ]);
    }
}
