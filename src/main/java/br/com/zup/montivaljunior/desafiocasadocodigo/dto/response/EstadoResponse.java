package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;

import java.io.Serializable;

public class EstadoResponse implements Serializable {

    private String nome;

    /**
     * @Deprecated
     */
    @Deprecated
    EstadoResponse() {
    }

    public EstadoResponse(String nome) {
        this.nome = nome;
    }

    public EstadoResponse(Estado estado) {
        if(estado != null) {
            this.nome = estado.getNome();
        }
    }



    public String getNome() {
        return nome;
    }

    public boolean possuiNomeCadastrado() {
        return !(this.nome == null);
    }
}
