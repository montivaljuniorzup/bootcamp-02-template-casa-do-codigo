package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.*;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.CPFouCNPJ;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.ExistId;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NovaCompraRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CPFouCNPJ(message = "Você precisa informar um CPF ou um CNPJ válido")
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExistId(classe = Pais.class, atributo = "id", message = "Não foi encontrado Pais com o id informado")
    private Long paisId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotEmpty
    @Size(min = 1)
    private List<Item> itens = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ExistId(classe = CupomDesconto.class, atributo = "id", message = "Não foi encontrado Cupom com o id informado")
    private Long idCupom;

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
                             List<Item> itens,
                             Long idCupom) {
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
        this.idCupom = idCupom;
    }

    public Compra toModel(EntityManager manager) {
        Pais pais = buscaPais(manager);
        Estado estado = buscaEstado(manager, pais);

        Compra compra = new Compra(this.email,
                this.nome,
                this.sobrenome,
                this.documento,
                this.endereco,
                this.complemento,
                this.cidade,
                pais,
                estado,
                this.telefone,
                this.cep,
                this.itens);

        return adicionaCupom(manager, compra);
    }

    private Estado buscaEstado(EntityManager manager, Pais pais) {
        Estado estado = null;
        if (pais.temEstado()) {
            Assert.notNull(this.estadoId, "O País selecionado possui estados cadastrados. Você precisa informar um");
            estado = manager.find(Estado.class, this.estadoId);
            if (estado != null) {
                if (!estado.pertenceAoPais(pais)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O estado informado não pertence ao Pais informado");
                }
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi encontrado um Estado com o id igual a " + this.estadoId);
            }
        }
        return estado;
    }

    private Pais buscaPais(EntityManager manager) {
        Pais pais = manager.find(Pais.class, this.paisId);
        Assert.notNull(pais, "Não foi encontrado um Pais com o id igual a " + this.paisId);
        return pais;
    }

    private Compra adicionaCupom(EntityManager manager, Compra compra) {
        if (existeCupomAtreladoACompra()) {
            CupomDesconto cupomDesconto = manager.find(CupomDesconto.class, idCupom);
            if (cupomDesconto.isValid() && cupomDesconto != null) {
                compra.aplicaCupomDesconto(cupomDesconto);
            }
        }
        return compra;
    }

    private boolean existeCupomAtreladoACompra() {
        return this.idCupom != null;
    }

    public List<Long> buscaIdsDosLivros() {
        return this.itens.stream().map(i -> i.getIdLivro()).collect(Collectors.toList());
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

    public Long getIdCupom() {
        return idCupom;
    }
}
