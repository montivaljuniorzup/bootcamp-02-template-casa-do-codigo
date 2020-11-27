package br.com.zup.montivaljunior.desafiocasadocodigo.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovaCompraRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Item;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class CompraTest {

    @MockBean
    private EntityManager manager;

    Pais brasil = new Pais("Brasil");
    Pais argentina = new Pais("Argentina");

    Estado estado = new Estado("Bahia", brasil);
    Estado estadoDois = new Estado("São Paulo", brasil);

    Item primeiroItem = new Item(1L, 1);
    Item segundoItem = new Item(2L, 2);

    private List<Item> itens = Arrays.asList(primeiroItem, segundoItem);

    NovaCompraRequest request = new NovaCompraRequest("teste@teste.com.br",
            "Teste",
            "Silva",
            "85830252074",
            "rua damião gomes de melo",
            "57",
            "Salvador",
            1L,
            1L,
            "71 99999999",
            "424360360",
            itens,
            1L);

    void adicionaCupomTest() {

    }
}
