package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "pais")
    private List<Estado> estados;

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
}
