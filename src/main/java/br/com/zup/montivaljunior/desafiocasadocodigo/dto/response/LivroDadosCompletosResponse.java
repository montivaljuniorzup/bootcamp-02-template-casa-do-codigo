package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class LivroDadosCompletosResponse {

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private int numeroDePaginas;

    private String isbn;

    private String dataLancamento;

    private CategoriaResponse categoria;

    private AutorResponse autor;

    /**
     * @Deprecated
     */
    @Deprecated
    public LivroDadosCompletosResponse() {
    }

    public LivroDadosCompletosResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataLancamento = livro.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.categoria = livro.getCategoria().paraResponse();
        this.autor = livro.getAutor().paraResponse();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public AutorResponse getAutor() {
        return autor;
    }
}
