package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Autor;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Categoria;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.ExistId;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest implements Serializable {

    @NotBlank(message = "{livro.titulo.obrigatorio}")
    @UniqueValue(classe = Livro.class, atributo = "titulo")
    private String titulo;

    @NotBlank(message = "{livro.resumo.obrigatorio}")
    @Size(max = 500, message = "{livro.resumo.tamanho}")
    private String resumo;

    private String sumario;

    @NotNull(message = "{livro.preco.obrigatorio}")
    @Min(message = "{livro.preco.minimo}", value = 20)
    private BigDecimal preco;

    @NotNull(message = "{livro.numeropaginas.obrigatorio}")
    @Min(value = 100, message = "{livro.numeropaginas.minimo}")
    private int numeroDePaginas;

    @NotBlank(message = "{livro.isbn.obrigatorio}")
    @UniqueValue(classe = Livro.class, atributo = "isbn")
    private String isbn;

    @Future(message = "{livro.data.invalido}")
    @NotNull(message = "{livro.data.obrigatorio}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @NotNull(message = "{livro.categoria.obrigatorio}")
    @ExistId(classe = Categoria.class, atributo = "id")
    private Long categoriaId;

    @NotNull(message = "{livro.autor.obrigatorio}")
    @ExistId(atributo = "id", classe = Autor.class)
    private Long autorId;

    /**
     * @Deprecated
     */
    @Deprecated
    public NovoLivroRequest() {
    }

    public NovoLivroRequest(@NotBlank String titulo,
                            @NotBlank @Size(max = 500) String resumo,
                            String sumario,
                            @NotNull BigDecimal preco,
                            @NotNull @Min(100) int numeroDePaginas,
                            @NotBlank String isbn,
                            @Future LocalDate dataLancamento,
                            @NotNull Long categoriaId,
                            @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro paraLivro(EntityManager manager) {
        Categoria categoria = buscaCategoria(manager);
        Autor autor = buscaAutor(manager);
        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.numeroDePaginas,
                this.isbn,
                this.dataLancamento,
                categoria,
                autor);
    }

    private Autor buscaAutor(EntityManager manager) {
        Autor autor = manager.find(Autor.class, this.autorId);
        Assert.state(autor != null, "Não existe um autor(a) associado(a) a esse Id");
        return autor;
    }

    private Categoria buscaCategoria(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, this.categoriaId);
        Assert.state(categoria != null, "Não existe uma categoria associada a esse Id");
        return categoria;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }
}
