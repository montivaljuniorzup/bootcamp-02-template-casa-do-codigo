package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Categoria;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class NovaCategoriaRequest implements Serializable {

    @NotBlank
    @UniqueValue(classe = Categoria.class, atributo = "nome")
    private String nome;

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
