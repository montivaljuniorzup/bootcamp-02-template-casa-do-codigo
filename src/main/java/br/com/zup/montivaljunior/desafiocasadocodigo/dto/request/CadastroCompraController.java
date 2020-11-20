package br.com.zup.montivaljunior.desafiocasadocodigo.dto.request;

import br.com.zup.montivaljunior.desafiocasadocodigo.dto.response.ClienteResponse;
import br.com.zup.montivaljunior.desafiocasadocodigo.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
    public ResponseEntity criaNovaCompra(@RequestBody @Valid NovoClienteRequest cliente) {
        Cliente cli = cliente.toModel(manager);
        return ResponseEntity.ok(new ClienteResponse(cli));
    }
}
