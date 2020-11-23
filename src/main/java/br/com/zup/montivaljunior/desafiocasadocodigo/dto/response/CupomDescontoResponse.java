package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.CupomDesconto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

public class CupomDescontoResponse implements Serializable {

    private String codigo;

    private double desconto;

    private LocalDate validade;

    @Deprecated
    public CupomDescontoResponse() {
    }

    public CupomDescontoResponse(CupomDesconto cupomDesconto) {
        this.codigo = cupomDesconto.getCodigo();
        this.desconto = cupomDesconto.getDesconto();
        this.validade = cupomDesconto.getValidade();
    }

    public CupomDescontoResponse(@NotBlank String codigo, @Positive @NotNull double desconto, @Future LocalDate validade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validade = validade;
    }

    public CupomDesconto paraCupom() {
        return new CupomDesconto(this.codigo, this.desconto, this.validade);
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
