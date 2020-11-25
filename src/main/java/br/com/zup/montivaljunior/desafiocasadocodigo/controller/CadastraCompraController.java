package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovaCompraRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.CompraResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import br.com.zup.montivaljunior.desafiocasadocodigo.validation.validator.ExisteLivroValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

//3
@RestController
@RequestMapping("/pagamentos")
public class CadastraCompraController {

    private EntityManager manager;

    public CadastraCompraController(EntityManager manager) {
        this.manager = manager;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new ExisteLivroValidator(manager));
    }

    //3
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
