package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NovoAutorRequest {

    @NotBlank(message = "{autor.nome.obrigatorio}")
    private String nome;

    @Email(message = "{autor.email.invalido}")
    @NotBlank(message = "{autor.email.obrigatorio}")
    private String email;

    @NotBlank(message = "{autor.descricao.obrigatorio}")
    @Size(max = 400, message = "{autor.descricao.invalido}")
    private String descricao;

    @NotNull(message = "{autor.nome.obrigatorio}")
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @Deprecated
    public NovoAutorRequest() {
    }

    public NovoAutorRequest(@NotBlank String nome, @Email @NotBlank String email, @NotBlank @Size(min = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Autor paraAutor() {
        return new Autor(this.nome, this.email, this.descricao, this.instanteCriacao);
    }
}
