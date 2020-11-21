package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.*;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.ExistId;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NovaCompraRequest implements Serializable {

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

    @NotEmpty
    private List<Item> itens = new ArrayList<>();

    /**
     * @Deprecated
     */
    @Deprecated
    public NovaCompraRequest() {
    }


    public NovaCompraRequest(@NotBlank @Email String email,
                             @NotBlank String nome,
                             @NotBlank String sobrenome,
                             @NotBlank String documento,
                             @NotBlank String endereco,
                             @NotBlank String complemento,
                             @NotBlank String cidade,
                             @NotNull Long paisId,
                             Long estadoId,
                             @NotBlank String telefone,
                             @NotBlank String cep,
                             List<Item> itens){
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
        this.itens = itens;
    }

    public Compra toModel(EntityManager manager){
       Pais pais = manager.find(Pais.class, this.paisId);
        Estado estado = null;
       if(pais.temEstado()) {
           Assert.notNull(this.estadoId, "O País selecionado possui estados cadastrados. Você precisa informar um");
           estado = manager.find(Estado.class, this.estadoId);
           Assert.notNull(estado, "Não foi encontrado um Estado com o id igual a " + this.estadoId);
       }


       return new Compra(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento, this.cidade, pais, estado, this.telefone, this.cep, this.itens );

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

    public List<Item> getItens() {
        return itens;
    }
}