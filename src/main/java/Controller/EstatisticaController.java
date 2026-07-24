package Controller;

import dto.EstatisticaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TransacaoService;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {
    private final TransacaoService transacaoService;

    public EstatisticaController(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaResponse> getEstatisca(){
        DoubleSummaryStatistics stats = transacaoService.getEstatitiscas();
        return ResponseEntity.ok(new EstatisticaResponse(stats));
    }

}
