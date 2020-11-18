package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.AutorResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 400)
    private String descricao;

    @NotNull
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Autor() {
    }

    public Autor(@NotBlank String nome, @Email @NotBlank String email, @NotBlank @Size(min = 400) String descricao, @NotNull LocalDateTime instanteCriacao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.instanteCriacao = instanteCriacao;
    }

    public AutorResponse paraResponse() {
        return new AutorResponse(this.nome, this.email, this.descricao, this.instanteCriacao);
    }
}
