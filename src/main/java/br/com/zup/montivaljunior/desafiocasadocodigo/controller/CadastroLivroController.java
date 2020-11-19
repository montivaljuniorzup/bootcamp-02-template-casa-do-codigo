package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.request.NovoLivroRequest;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosCompletosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

//2
@RestController
@RequestMapping("/livros")
public class CadastroLivroController {

    @PersistenceContext
    private EntityManager manager;

    public CadastroLivroController(EntityManager manager) {
        this.manager = manager;
    }

    //2
    @PostMapping
    @Transactional
    public ResponseEntity criaNovoLivro(@Valid @RequestBody NovoLivroRequest livroRequest){
        Livro livro = livroRequest.paraLivro(manager);
        manager.persist(livro);
        return ResponseEntity.ok(new LivroDadosCompletosResponse(livro));
    }
}
