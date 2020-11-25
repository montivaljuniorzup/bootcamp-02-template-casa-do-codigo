package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Item;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CarrinhoCompraResponse {

    private BigDecimal total;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal totalComDesconto;

    private List<Item> itens = new ArrayList<>();

    /**
     * @Deprecated
     */
    @Deprecated
    public CarrinhoCompraResponse() {
    }

    public CarrinhoCompraResponse(EntityManager manager, Compra compra) {
        this.itens = compra.getItens();

        Double itensSomados = buscaValorTotalDosItens(manager, compra);
        this.total = BigDecimal.valueOf(itensSomados);
        this.totalComDesconto = calculaValorComDesconto(compra);
    }

    private Double buscaValorTotalDosItens(EntityManager manager, Compra compra) {
        return itens.stream().mapToDouble(i -> {
            Livro livro = manager.find(Livro.class, i.getIdLivro());
            Assert.state(livro != null, "Não foi encontrado livro com o id " + i.getIdLivro());
            Assert.state(i.getQuantidade() > 0, "A quantidade de livros deve ser no mínimo 1");

            BigDecimal valorSemDesconto = livro.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade()));
            return valorSemDesconto.doubleValue();
        }).sum();

    }

    public BigDecimal calculaValorComDesconto(Compra compra) {

        if (compra.temCupom() && compra.temCupomValido()) {
            double desconto = compra.getCupomDesconto().getDesconto();
            return this.total.multiply(BigDecimal.valueOf((1 - desconto)));
        }
        return this.total;
    }
//    private Double buscaValorTotalDosItens(EntityManager manager, Compra compra) {
//        Double itensSomados = itens.stream().mapToDouble(i -> {
//
//            Livro livro = manager.find(Livro.class, i.getIdLivro());
//            Assert.state(livro != null, "Não foi encontrado livro com o id " + i.getIdLivro());
//            Assert.state(i.getQuantidade() > 0, "A quantidade de livros deve ser no mínimo 1");
//
//            BigDecimal valorSemDesconto = livro.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade()));
//
//            if (compra.temCupom() && compra.temCupomValido()) {
//
//                double desconto = compra.getCupomDesconto().getDesconto();
//
//                return valorSemDesconto.multiply(BigDecimal.valueOf((1 - desconto))).doubleValue();
//
//            } else {
//
//                return valorSemDesconto.doubleValue();
//            }
//        }).sum();
//        return itensSomados;
//    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Item> getItens() {
        return itens;
    }

    public BigDecimal getTotalComDesconto() {
        return totalComDesconto;
    }
}
