package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.CupomDesconto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class CupomDescontoResponse {

    private String codigo;

    private double desconto;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    @Deprecated
    public CupomDescontoResponse() {
    }

    public CupomDescontoResponse(CupomDesconto cupomDesconto) {
        this.codigo = cupomDesconto.getCodigo();
        this.desconto = cupomDesconto.getDesconto() * 100;
        this.validade = cupomDesconto.getValidade();
    }

    public CupomDescontoResponse(@NotBlank String codigo, @Positive @NotNull double desconto, @Future LocalDate validade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validade = validade;
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
