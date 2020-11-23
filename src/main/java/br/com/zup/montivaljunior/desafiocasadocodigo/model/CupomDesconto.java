package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CupomDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;

    @Positive
    @NotNull
    private double desconto;

    @Future
    private LocalDate validade;

    @Deprecated
    public CupomDesconto() {
    }

    public CupomDesconto(@NotBlank String codigo, @Positive @NotNull double desconto, @Future LocalDate validade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validade = validade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CupomDesconto that = (CupomDesconto) o;
        return Double.compare(that.desconto, desconto) == 0 &&
                codigo.equals(that.codigo) &&
                validade.equals(that.validade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, desconto, validade);
    }

    public String getCodigo() {
        return codigo;
    }

    public double getDesconto() {
        return desconto;
    }

    public LocalDate getValidade() {
        return validade;
    }
}
