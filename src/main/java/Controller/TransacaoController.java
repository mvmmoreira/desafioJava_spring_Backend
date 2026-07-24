package Controller;

import dto.TransacaoRequest;
import model.Transacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TransacaoService;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    private TransacaoController(TransacaoService  transacaoService ){
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransacao(@RequestBody TransacaoRequest request){
        if (request.getDataHora().isAfter(OffsetDateTime.now())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        transacaoService.addTransacao(new Transacao(request.getValor(), request.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransacoes(){
        transacaoService.clearTransacao();
        return ResponseEntity.ok().build();
    }

}








