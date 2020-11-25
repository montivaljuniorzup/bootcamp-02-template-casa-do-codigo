package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.CompraResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/compras/finaliza")
public class BuscaCompraController {
    @PersistenceContext
    private EntityManager manager;

    public BuscaCompraController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscaCompraPeloId(@PathVariable("id") Long id) {
        Compra compra = manager.find(Compra.class, id);
        Assert.notNull(compra, "Não foi encontrada compra com o id informado");
        CompraResponse compraResponse = new CompraResponse(compra, manager);

        return ResponseEntity.ok(compraResponse);
    }
}
