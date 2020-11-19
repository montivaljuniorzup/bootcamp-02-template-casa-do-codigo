package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroResponse;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false)
    private String resumo;

    private String sumario;

    @NotNull
    @Min(20)
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    @Column(nullable = false)
    private int numeroDePaginas;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate dataLancamento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo,
                 @NotBlank @Size(max = 500) String resumo,
                 String sumario,
                 @NotNull @Min(20) BigDecimal preco,
                 @NotNull @Min(100) int numeroDePaginas,
                 @NotBlank String isbn,
                 @Future LocalDate dataLancamento,
                 Categoria categoria,
                 Autor autor) {
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

    public LivroResponse paraResponse() {
        return new LivroResponse(this.titulo,
                this.resumo,
        this.sumario,
        this.preco,
        this.numeroDePaginas,
        this.isbn,
        this.dataLancamento,
        this.categoria.paraResponse(),
        this.autor.paraResponse());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return titulo.equals(livro.titulo) &&
                isbn.equals(livro.isbn) &&
                autor.equals(livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, isbn, autor);
    }
}

