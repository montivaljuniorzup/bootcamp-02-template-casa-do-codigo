package br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.UniqueValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String atributo;

    private Class<?> classe;

    @Autowired
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.atributo = constraintAnnotation.atributo();
        this.classe = constraintAnnotation.classe();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Assert.state(manager != null, "Será que você colocou essa annotation @UniqueValue numa classe anotada com @Entity? Se colocou não vai funcionar." +
                " Ela só irá conseguir injetar o EntityManager, se estiver no contexto do Spring. Tente colocar no Objeto de Request");

        Query query = manager.createQuery("Select 1 from " + classe.getSimpleName() + " where " + atributo + "=:object");
        query.setParameter("object", object);
        List<Object> resultadoEncontrado = query.getResultList();
        Assert.state(resultadoEncontrado.isEmpty(), "Ja existe um(a) " + classe.getSimpleName() + " com esse " + atributo + " registrado");
        return resultadoEncontrado.isEmpty();
    }
}
