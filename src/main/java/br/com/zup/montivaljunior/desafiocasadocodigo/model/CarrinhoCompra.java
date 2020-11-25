package br.com.zup.montivaljunior.desafiocasadocodigo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CarrinhoCompra {

    @NotNull
    @Positive
    private BigDecimal total;

    @NotNull
    @Min(1)
    private List<Item> itens = new ArrayList<>();

    /**
     * @Deprecated
     */
    @Deprecated
    public CarrinhoCompra() {
    }

//    public CarrinhoCompra(EntityManager manager, @NotNull @Min(1) List<Item> itens) {
//        this.itens = itens;
//        Long total = itens.stream().map(i -> {
//            Livro livro = manager.find(Livro.class, i.getIdLivro());
//            BigDecimal precoTotal = livro.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade()));
//            return precoTotal;
//        }).count();
//
//        this.total = BigDecimal.valueOf(total);
//    }

//    public BigDecimal getTotal() {
//        return total;
//    }

    public List<Item> getItens() {
        return itens;
    }

//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrinhoCompra that = (CarrinhoCompra) o;
        return itens.equals(that.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itens);
    }
}
