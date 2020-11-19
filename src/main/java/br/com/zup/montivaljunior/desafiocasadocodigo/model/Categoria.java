package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.CategoriaResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{catgoria.nome.obrigatorio}")
    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;


    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    public CategoriaResponse paraResponse() {
        return new CategoriaResponse(this.nome);
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return nome.equals(categoria.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
