package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.AutorResponse;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 400)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Autor() {
    }

    public Autor(@NotBlank String nome, @Email @NotBlank String email, @NotBlank @Size(max = 400) String descricao, @NotNull LocalDateTime instanteCriacao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.instanteCriacao = instanteCriacao;
    }

    public AutorResponse paraResponse() {
        return new AutorResponse(this.nome, this.email, this.descricao, this.instanteCriacao);
    }
}
