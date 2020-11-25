package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;

public class EstadoResponse {

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
        if (estado != null) {
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
