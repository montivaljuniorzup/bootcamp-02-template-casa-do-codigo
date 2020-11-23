package br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator.CpfOuCnpjValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CpfOuCnpjValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFouCNPJ {

    String message() default "{br.com.zup.desafiocasadocodigo.beanvalidation.cpfoucnpj}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
