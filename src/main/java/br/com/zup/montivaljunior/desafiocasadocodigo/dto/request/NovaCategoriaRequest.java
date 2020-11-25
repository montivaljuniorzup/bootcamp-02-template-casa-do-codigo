package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Categoria;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank(message = "{catgoria.nome.obrigatorio}")
    @UniqueValue(classe = Categoria.class, atributo = "nome")
    private String nome;

    /**
     * @Deprecated
     */
    @Deprecated
    public NovaCategoriaRequest() {
    }

    public Categoria paraCategoria() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
