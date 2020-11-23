package br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator.UniqueIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueId {


    String message() default "{br.com.zup.desafiocasadocodigo.beanvalidation.uniqueid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String atributo();

    Class<?> classe();
}
