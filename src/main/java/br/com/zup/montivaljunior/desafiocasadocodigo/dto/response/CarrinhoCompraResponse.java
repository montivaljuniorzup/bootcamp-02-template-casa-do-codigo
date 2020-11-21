package br.com.zup.montivaljunior.desafiocasadocodigo.dto.response;

import br.com.zup.montivaljunior.desafiocasadocodigo.model.Item;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CarrinhoCompraResponse {

    @NotNull
    @Positive
    private BigDecimal total;

    @NotEmpty
    private List<Item> itens = new ArrayList<>();

    /**
     * @Deprecated
     */
    @Deprecated
    public CarrinhoCompraResponse() {
    }

    public CarrinhoCompraResponse(EntityManager manager, @NotEmpty List<Item> itens) {
        this.itens = itens;

        Double itensSomados = itens.stream().mapToDouble(i -> {
            Livro livro = manager.find(Livro.class, i.getIdLivro());
            Assert.state(livro != null, "Não foi encontrado livro com o id " + i.getIdLivro());
            Assert.state(i.getQuantidade() > 0, "A quantidade de livros deve ser no mínimo 1");
            return livro.getPreco().multiply(BigDecimal.valueOf(i.getQuantidade())).doubleValue();
        }).sum();

        this.total = BigDecimal.valueOf(itensSomados);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Item> getItens() {
        return itens;
    }
}
