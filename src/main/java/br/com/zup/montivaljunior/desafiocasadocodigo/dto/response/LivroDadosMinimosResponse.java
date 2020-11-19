package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import java.io.Serializable;

public class LivroDadosMinimosResponse implements Serializable {

    private Long id;

    private String nome;

    @Deprecated
    public LivroDadosMinimosResponse() {
    }

    public LivroDadosMinimosResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
