package service;

import model.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransacaoService {
    private final Queue<Transacao> transacoes = new ConcurrentLinkedQueue<>();

    public void addTransacao(Transacao transacao){
        transacoes.add(transacao);
    }

    public void clearTransacao() {
        transacoes.clear();
    }

    public DoubleSummaryStatistics getEstatitiscas(){
        OffsetDateTime now = OffsetDateTime.now();
        return transacoes.stream()
                .filter(t-> t.getDataHora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();
    }
}
