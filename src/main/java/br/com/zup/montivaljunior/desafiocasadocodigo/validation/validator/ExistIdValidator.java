package br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.ExistId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistId, Object> {

    private Class<?> aClass;

    private String field;

    @PersistenceContext
    private EntityManager manager;

    public ExistIdValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ExistId constraintAnnotation) {
        this.aClass = constraintAnnotation.classe();
        this.field = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            return true;
        }

        Query query = manager.createQuery("Select x from " + aClass.getName() + " x where " + field + "=" + o);
        boolean isEmpty = query.getResultList().isEmpty();
        Assert.state(!isEmpty, "Não foi encontrado um(a) " + aClass.getSimpleName() + " com o " + field + " igual a " + o);
        return !isEmpty;
    }
}
