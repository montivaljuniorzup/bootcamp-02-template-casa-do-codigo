package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import java.io.Serializable;

public class AutorResponse implements Serializable {


    private String nome;

    private String descricao;

    /**
     * @Deprecated
     */
    @Deprecated
    public AutorResponse() {
    }

    public AutorResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
