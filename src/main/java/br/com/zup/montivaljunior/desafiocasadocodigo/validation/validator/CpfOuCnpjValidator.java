package br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator;

import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.CPFouCNPJ;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOuCnpjValidator implements ConstraintValidator<CPFouCNPJ, String> {

    private CPFValidator cpfValidator;
    private CNPJValidator cnpjValidator;


    @Override
    public void initialize(CPFouCNPJ constraintAnnotation) {

        cpfValidator = new CPFValidator();
        /*
         * No momento dessa implementação, o ContraintAnnotation esperado como parâmetro do método initialize não é utilizado na implementação do método,
         * Dessa forma foi passado null como parametro. Se numa atualização do Hibernate esse parametro for utilizado, pode gerar quebra na validação.
         */
        cpfValidator.initialize(null);

        cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        boolean cpfIsValid = cpfValidator.isValid(value, constraintValidatorContext);
        boolean cnpjIsValid = cnpjValidator.isValid(value, constraintValidatorContext);

        return cnpjIsValid || cpfIsValid;
    }
}
