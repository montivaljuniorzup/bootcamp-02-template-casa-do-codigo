package br.com.zup.montivaljunior.desafiocasadocodigo.controller;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosCompletosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.LivroDadosMinimosResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.exceptions.ErroApi;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//4
@RestController
@RequestMapping("/livros/produtos")
public class BuscaLivroController {

    @PersistenceContext
    private EntityManager manager;

    public BuscaLivroController(EntityManager manager) {
        this.manager = manager;
    }

    //2
    @GetMapping
    @Transactional
    public ResponseEntity buscaLivros(){
        List<Livro> livros = manager.createQuery("Select u from Livro u").getResultList();
        List<LivroDadosMinimosResponse> livrosResponse = livros.stream().map(l -> l.paraLivroDadosMinimosResponse()).collect(Collectors.toList());
        return ResponseEntity.ok(livrosResponse);
    }

    //2
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> buscaLivroPeloId(@PathVariable("id") Long id){
        Livro livro = manager.find(Livro.class, id);
        if(livro == null){
            return new ResponseEntity<ErroApi>(new ErroApi("Não foi encontrado Livro com esse Id: " + id), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new LivroDadosCompletosResponse(livro));
    }
}
