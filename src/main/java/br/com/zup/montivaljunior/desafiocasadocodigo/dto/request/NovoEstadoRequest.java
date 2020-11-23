package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.ExistId;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NovoEstadoRequest implements Serializable {

    @NotBlank
    @UniqueValue(classe = Estado.class, atributo = "nome")
    private String nome;

    @NotNull
    @ExistId(classe = Pais.class, atributo = "id")
    private Long paisId;

    /**
     * @Deprecated
     */
    @Deprecated
    public NovoEstadoRequest() {
    }

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public Estado toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, this.paisId);
        if (pais == null) {
            throw new IllegalArgumentException("Não foi encontrado País com o id: " + this.paisId);
        }
        return new Estado(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }
}
