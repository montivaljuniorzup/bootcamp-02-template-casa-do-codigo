package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDadosCompletosResponse {
    @NotBlank
   private String titulo;
    @NotBlank @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull @Min(20)
    private BigDecimal preco;

    @NotNull @Min(100)
    private int numeroDePaginas;

    @NotBlank
    private String isbn;

    @Future @NotNull
    private LocalDate dataLancamento;

    private CategoriaResponse categoria;

    private AutorResponse autor;

    @Deprecated
    public LivroDadosCompletosResponse() {
    }

    public LivroDadosCompletosResponse(@NotBlank String titulo,
                                       @NotBlank @Size(max = 500)
                                 String resumo,
                                       String sumario,
                                       @NotNull @Min(20) BigDecimal preco,
                                       @NotNull @Min(100) int numeroDePaginas,
                                       @NotBlank String isbn,
                                       @Future @NotNull LocalDate dataLancamento,
                                       CategoriaResponse categoria,
                                       AutorResponse autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
        this.autor = autor;
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public AutorResponse getAutor() {
        return autor;
    }
}
