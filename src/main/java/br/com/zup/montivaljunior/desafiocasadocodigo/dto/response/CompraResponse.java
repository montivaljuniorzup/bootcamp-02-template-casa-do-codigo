package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompraResponse {

    private String email;

    private String nome;

    private String sobrenome;

    private String documento;

    private String endereco;

    private String complemento;

    private String cidade;

    private PaisResponse pais;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EstadoResponse estado;

    private String telefone;

    private String cep;

    private CarrinhoCompraResponse carrinhoCompra;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CupomDescontoResponse cupom;

    private boolean existeCupom;

    private BigDecimal valorCupom;

    public BigDecimal getValorCupom() {
        return valorCupom;
    }

    public boolean isExisteCupom() {
        return existeCupom;
    }

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
        this.carrinhoCompra = new CarrinhoCompraResponse(manager, compra);
        if (compra.temCupomValido()) {
            this.cupom = new CupomDescontoResponse(compra.getCupomDesconto());
            this.existeCupom = true;
            this.valorCupom = carrinhoCompra.getTotal().multiply(BigDecimal.valueOf(compra.buscaValorDesconto()));
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
