package br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueIdValidator implements ConstraintValidator<UniqueId, Object> {

    private Class<?> aClass;

    private String field;

    @PersistenceContext
    private EntityManager manager;

    public UniqueIdValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(UniqueId constraintAnnotation) {
        this.aClass = constraintAnnotation.classe();
        this.field = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("Select x from " + aClass.getName() + " x where " + field + "=" + o);
        boolean isEmpty = query.getResultList().isEmpty(); //se n tem no banco é true
        Assert.state(!isEmpty, "Já existe um(a) " + aClass.getSimpleName() +" com o " + field + " igual a " + o );
        return isEmpty; //se tem no banco é true
    }
}
