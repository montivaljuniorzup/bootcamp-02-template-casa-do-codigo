package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;

public class PaisResponse {

    private String nome;

    /**
     * @Deprecated
     */
    @Deprecated
    public PaisResponse() {
    }

    public PaisResponse(String nome) {
        this.nome = nome;
    }

    public PaisResponse(Pais pais) {
        this.nome = pais.getNome();
    }

    public String getNome() {
        return nome;
    }
}
