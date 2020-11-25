package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @ElementCollection
    @CollectionTable(name = "itens")
    private List<Item> itens;

    @ManyToOne
    private CupomDesconto cupomDesconto;

    /**
     * @Deprecated
     */
    @Deprecated
    public Compra() {
    }

    public Compra(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, @NotNull Pais pais, Estado estado, @NotBlank String telefone, @NotBlank String cep, List<Item> itens) {
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
        this.itens = itens;
    }

    public boolean temCupomValido() {
        if (this.cupomDesconto != null) {
            return this.cupomDesconto.isValid();
        }
        return false;
    }

    public double buscaValorDesconto() {
        return this.getCupomDesconto().getDesconto();

    }

    public CupomDesconto getCupomDesconto() {
        if (temCupomValido()) {
            return cupomDesconto;
        }
        return new CupomDesconto();
    }

    public void aplicaCupomDesconto(CupomDesconto cupomDesconto) {
        if (this.id == null) {
            this.cupomDesconto = cupomDesconto;
        }
    }

    public Long getId() {
        return id;
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

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return email.equals(compra.email) &&
                documento.equals(compra.documento) &&
                itens.equals(compra.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, documento, itens);
    }

}
