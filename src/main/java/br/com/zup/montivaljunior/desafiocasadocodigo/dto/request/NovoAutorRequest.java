package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Autor;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorRequest {

    @NotBlank(message = "{autor.nome.obrigatorio}")
    private String nome;

    @Email(message = "{autor.email.invalido}")
    @NotBlank(message = "{autor.email.obrigatorio}")
    @UniqueValue(classe = Autor.class, atributo = "email")
    private String email;

    @NotBlank(message = "{autor.descricao.obrigatorio}")
    @Size(max = 400, message = "{autor.descricao.invalido}")
    private String descricao;

    /**
     * @Deprecated
     */
    @Deprecated
    public NovoAutorRequest() {
    }

    public NovoAutorRequest(@NotBlank String nome,
                            @Email @NotBlank String email,
                            @NotBlank @Size(min = 400) String descricao) {
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

    public Autor paraAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
