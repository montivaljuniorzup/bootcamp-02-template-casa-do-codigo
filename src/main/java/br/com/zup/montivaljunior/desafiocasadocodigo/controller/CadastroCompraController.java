package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovaCompraRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.CompraResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class CadastroCompraController {

    @PersistenceContext
    private EntityManager manager;

    public CadastroCompraController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity criaNovaCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest, UriComponentsBuilder builder) {
        Compra compra = novaCompraRequest.toModel(manager);
        manager.persist(compra);
        CompraResponse compraResponse = new CompraResponse(compra, manager);
        URI uri = builder.path("/compras/finaliza/{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(uri).body(compraResponse);
    }
}
