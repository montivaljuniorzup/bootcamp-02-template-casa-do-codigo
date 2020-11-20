package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClienteResponse implements Serializable {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    private PaisResponse pais;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EstadoResponse estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    /**
     * @Deprecated
     */
    @Deprecated
    public ClienteResponse() {
    }

    public ClienteResponse(Cliente cliente) {
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.documento = cliente.getDocumento();
        this.endereco = cliente.getEndereco();
        this.complemento = cliente.getComplemento();
        this.cidade = cliente.getCidade();
        this.pais = new PaisResponse(cliente.getPais());

        this.estado = new EstadoResponse(cliente.getEstado());
        if( !this.estado.possuiNomeCadastrado()) {
            this.estado = null;
        }

        this.telefone = cliente.getTelefone();
        this.cep = cliente.getCep();
    }
    public ClienteResponse(@NotBlank @Email String email,
                           @NotBlank String nome,
                           @NotBlank String sobrenome,
                           @NotBlank String documento,
                           @NotBlank String endereco,
                           @NotBlank String complemento,
                           @NotBlank String cidade,
                           @NotNull PaisResponse pais,
                           EstadoResponse estado,
                           @NotBlank String telefone,
                           @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public PaisResponse getPais() {
        return pais;
    }

    public EstadoResponse getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
