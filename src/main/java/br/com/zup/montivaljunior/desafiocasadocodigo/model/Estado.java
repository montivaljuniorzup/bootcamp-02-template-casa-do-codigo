package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pais_id", unique = true, nullable = false)
    private Pais pais;

    /**
     * @Deprecated
     */
    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome, @NotNull Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return nome.equals(estado.nome) &&
                pais.equals(estado.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, pais);
    }
}
