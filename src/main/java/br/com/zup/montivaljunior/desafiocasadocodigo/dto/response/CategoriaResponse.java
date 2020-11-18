package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import java.io.Serializable;

public class CategoriaResponse implements Serializable {

    private String nome;

    @Deprecated
    public CategoriaResponse() {
    }

    public CategoriaResponse(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
