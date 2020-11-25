package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "pais")
    private List<Estado> estados = new ArrayList<>();

    /**
     * @Deprecated
     */
    @Deprecated
    public Pais() {
    }

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }

    public Pais(@NotBlank String nome, List<Estado> estados) {
        this.nome = nome;
        this.estados = estados;
    }

    public boolean temEstado() {
        return !this.estados.isEmpty();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return nome.equals(pais.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
