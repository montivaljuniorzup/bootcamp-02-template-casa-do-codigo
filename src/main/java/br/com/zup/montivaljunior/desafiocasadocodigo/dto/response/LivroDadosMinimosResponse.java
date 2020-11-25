package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;

public class LivroDadosMinimosResponse {

    private Long id;

    private String nome;

    /**
     * @Deprecated
     */
    @Deprecated
    public LivroDadosMinimosResponse() {
    }

    public LivroDadosMinimosResponse(Livro livro) {
        this.id = livro.getId();
        this.nome = livro.getTitulo();
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
