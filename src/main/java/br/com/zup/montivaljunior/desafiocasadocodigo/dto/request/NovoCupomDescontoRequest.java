package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.CupomDesconto;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.annotation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

public class NovoCupomDescontoRequest implements Serializable {

    @NotBlank
    @UniqueValue(classe = CupomDesconto.class, atributo = "codigo")
    private String codigo;

    @Positive
    @NotNull
    @Max(100)
    private double desconto;

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate validade;

    @Deprecated
    public NovoCupomDescontoRequest() {
    }

    public NovoCupomDescontoRequest(@NotBlank String codigo, @Positive @NotNull double desconto, @Future LocalDate validade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validade = validade;
    }

    public CupomDesconto paraCupom() {
        double descontoEmPorcentagem = this.desconto / 100;
        return new CupomDesconto(this.codigo, descontoEmPorcentagem, this.validade);
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
