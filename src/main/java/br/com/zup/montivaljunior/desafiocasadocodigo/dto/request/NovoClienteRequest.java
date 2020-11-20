package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Cliente;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.ExistId;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class NovoClienteRequest implements Serializable {

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
    @ExistId(classe = Pais.class, atributo = "id")
    private Long paisId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    /**
     * @Deprecated
     */
    @Deprecated
    public NovoClienteRequest() {
    }


    public NovoClienteRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, @NotNull Long paisId, Long estadoId, @NotBlank String telefone, @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente toModel(EntityManager manager){
       Pais pais = manager.find(Pais.class, this.paisId);
        Estado estado = null;
       if(pais.temEstado()) {
           Assert.notNull(this.estadoId, "O País selecionado possui estados cadastrados. Você precisa informar um");
           estado = manager.find(Estado.class, this.estadoId);
           Assert.notNull(estado, "Não foi encontrado um Estado com o id igual a " + this.estadoId);
       }
       return new Cliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento, this.cidade, pais, estado, this.telefone, this.cep);

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

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
