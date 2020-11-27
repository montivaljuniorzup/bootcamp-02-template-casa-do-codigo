package br.com.zup.montivaljunior.desafiocasadocodigo.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoLivroRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Autor;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class NovoLivroRequestTest {

    @MockBean
    EntityManager manager;

    private NovoLivroRequest request = new NovoLivroRequest("teste3",
            "siasjdijaisjoijad",
            "asodmaodmoamdsa",
            BigDecimal.valueOf(20),
            150,
            "isbn3",
            LocalDate.of(2031, 12, 31),
            1L,
            1L);

    Autor autor = new Autor("Teste", "teste@email.com", "Autor desconhecido");

    Categoria categoria = new Categoria("Teste");


    @Test
    @DisplayName("Deve converter um Livro request em Livro")
    void teste01() {
        Mockito.when(manager.find(Autor.class, 1L)).thenReturn(autor);
        Mockito.when(manager.find(Categoria.class, 1L)).thenReturn(categoria);
        Assertions.assertNotNull(request.paraLivro(manager));
    }

    @Test
    @DisplayName("Deve lançar exception ao tentar criar livro com autor inexistente no banco")
    void teste02() {
        Mockito.when(manager.find(Autor.class, 1L)).thenReturn(null);
        Mockito.when(manager.find(Categoria.class, 1L)).thenReturn(categoria);
        RuntimeException ex = Assertions.assertThrows(IllegalStateException.class, () -> request.paraLivro(manager));
        Assertions.assertTrue(ex.getMessage().contains("Não existe um autor(a) associado(a) a esse Id"));
        Assertions.assertEquals(ex.getMessage(), "Não existe um autor(a) associado(a) a esse Id");
    }

    @Test
    @DisplayName("Deve lançar exception ao tentar criar um livro com categoria ineistente no banco")
    void teste04() {
        Mockito.when(manager.find(Autor.class, 1L)).thenReturn(autor);
        Mockito.when(manager.find(Categoria.class, 1L)).thenReturn(null);
        IllegalStateException illegalStateException = Assertions.assertThrows(IllegalStateException.class, () -> request.paraLivro(manager));
        Assertions.assertEquals(illegalStateException.getMessage(), "Não existe uma categoria associada a esse Id");
    }
}
