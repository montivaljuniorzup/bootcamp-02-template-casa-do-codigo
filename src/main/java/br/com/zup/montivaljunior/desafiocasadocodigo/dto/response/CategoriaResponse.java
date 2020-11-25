package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

public class CategoriaResponse {

    private String nome;

    /**
     * @Deprecated
     */
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
