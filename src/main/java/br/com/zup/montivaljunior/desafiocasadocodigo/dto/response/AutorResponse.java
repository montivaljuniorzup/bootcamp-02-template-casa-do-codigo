package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AutorResponse implements Serializable {


    private String nome;

    private String email;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime instanteCriacao;

    @Deprecated
    public AutorResponse() {
    }

    public AutorResponse(String nome, String email, String descricao, LocalDateTime instanteCriacao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.instanteCriacao = instanteCriacao;
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
}
