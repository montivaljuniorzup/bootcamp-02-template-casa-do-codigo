package br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovaCompraRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ExisteLivroValidator implements Validator {


    private EntityManager manager;

    public ExisteLivroValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object value, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) value;

        List<Long> listaDeIds = novaCompraRequest.buscaIdsDosLivros();

        listaDeIds.stream().map(id -> {
            Livro livro = manager.find(Livro.class, id);

            if(livro == null){
                errors.rejectValue("itens", null, "Não foi encontrado um Livro com esse id");
            }
            return livro;
        }).collect(Collectors.toList());
    }
}

