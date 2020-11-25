package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.CompraResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Compra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

//3
@RestController
@RequestMapping("/compras/finaliza")
public class BuscaCompraController {

    private EntityManager manager;

    public BuscaCompraController(EntityManager manager) {
        this.manager = manager;
    }

    //3
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscaCompraPeloId(@PathVariable("id") Long id) {
        Compra compra = manager.find(Compra.class, id);
        if(compra == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado uma compra com o id informado");
        }
        CompraResponse compraResponse = new CompraResponse(compra, manager);
        return ResponseEntity.ok(compraResponse);
    }
}
