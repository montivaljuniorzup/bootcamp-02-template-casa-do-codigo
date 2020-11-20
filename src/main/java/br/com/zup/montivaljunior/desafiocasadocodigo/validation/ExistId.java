package br.com.zup.montivaljunior.desafiocasadocodigo.validation;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator.ExistIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {


    String message() default "{br.com.zup.desafiocasadocodigo.beanvalidation.existid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String atributo();

    Class<?> classe();
}
