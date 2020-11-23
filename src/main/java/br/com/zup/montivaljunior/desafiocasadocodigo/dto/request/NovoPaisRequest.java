package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class NovoPaisRequest implements Serializable {

    @NotBlank
    @UniqueValue(atributo = "nome", classe = Pais.class)
    private String nome;

    /**
     * @Deprecated
     */
    @Deprecated
    public NovoPaisRequest() {
    }

    public NovoPaisRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Pais toModel(){
        return new Pais(this.nome);
    }

    public String getNome() {
        return nome;
    }


}
