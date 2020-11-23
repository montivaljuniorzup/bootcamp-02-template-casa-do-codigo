package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CompraResponse implements Serializable {

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

    private CarrinhoCompraResponse carrinhoCompra;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CupomDescontoResponse cupom;

    /**
     * @Deprecated
     */
    @Deprecated
    public CompraResponse() {
    }

    public CompraResponse(Compra compra, EntityManager manager) {
        this.email = compra.getEmail();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.pais = new PaisResponse(compra.getPais());

        this.estado = new EstadoResponse(compra.getEstado());
        if (!this.estado.possuiNomeCadastrado()) {
            this.estado = null;
        }

        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.carrinhoCompra = new CarrinhoCompraResponse(manager, compra.getItens());
        if(compra.temCupom()){
        this.cupom = new CupomDescontoResponse(compra.getCupomDesconto());
        }
    }

    public CompraResponse(@NotBlank @Email String email,
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

    public CarrinhoCompraResponse getCarrinhoCompra() {
        return carrinhoCompra;
    }

    public CupomDescontoResponse getCupom() {
        return cupom;
    }
}
