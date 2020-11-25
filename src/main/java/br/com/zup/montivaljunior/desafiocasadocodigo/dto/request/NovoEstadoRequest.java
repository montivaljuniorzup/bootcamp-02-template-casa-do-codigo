package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.ExistId;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

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
        Assert.notNull(pais, "Não foi encontrado País com o id: " + this.paisId);
        return new Estado(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }
}
