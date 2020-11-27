package br.com.zup.montivaljunior.desafiocasadocodigo.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoEstadoRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Estado;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Pais;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

@SpringBootTest
public class NovoEstadoRequestTest {

    @MockBean
    private EntityManager manager;
    private Pais pais = new Pais("Brasil");
    private NovoEstadoRequest request = new NovoEstadoRequest("Bahia", 1L);


    @Test
    @DisplayName("Deve converter um estadoRequest num Estado")
    void test01(){
        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(pais);
        Estado estadoSalvo = request.toModel(manager);
        Assertions.assertTrue(estadoSalvo != null);
    }

    @Test
    @DisplayName("Deve gerar erro ao tentar criar um estado sem um país válido")
    void test02(){
        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(null);
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> request.toModel(manager));
        Assertions.assertTrue(exception.getMessage().contains( "Não foi encontrado País com o id: "));
    }
}
