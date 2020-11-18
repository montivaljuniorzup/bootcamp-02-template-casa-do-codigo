package br.com.zup.montivaljunior.desafiocasadocodigo.validation;


import br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

    String message() default "{br.com.zup.desafiocasadocodigo.beanvalidation.uniquevalue}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String atributo();

    Class<?> classe();
}
