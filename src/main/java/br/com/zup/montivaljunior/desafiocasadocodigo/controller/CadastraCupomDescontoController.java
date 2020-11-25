package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoCupomDescontoRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.CupomDescontoResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.CupomDesconto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

//3
@RestController
@RequestMapping("/cupons")
public class CadastraCupomDescontoController {

    private EntityManager manager;

    public CadastraCupomDescontoController(EntityManager manager) {
        this.manager = manager;
    }

    //3
    @PostMapping
    @Transactional
    public ResponseEntity criaNovoCupom(@Valid @RequestBody NovoCupomDescontoRequest novoCupom) {
        CupomDesconto cupomGerado = novoCupom.paraCupom();
        manager.persist(cupomGerado);
        return new ResponseEntity(new CupomDescontoResponse(cupomGerado), HttpStatus.OK);
    }
}
