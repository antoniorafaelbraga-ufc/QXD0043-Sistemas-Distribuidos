package sd.spring_replicacao_failover;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replicacao")
public class ReplicacaoController {

    private final GerenciadorReplicas gerenciador;

    public ReplicacaoController(GerenciadorReplicas gerenciador) {
        this.gerenciador = gerenciador;
    }

    @PostMapping("/adicionar")
    public Map<String, String> adicionar(@RequestParam String replica) {
        gerenciador.adicionarReplica(replica);

        return Map.of(
                "mensagem", "Réplica adicionada",
                "replica", replica
        );
    }

    @PostMapping("/falha")
    public Map<String, String> falha(@RequestParam String replica) {
        try {
            gerenciador.simularFalha(replica);

            return Map.of(
                    "mensagem", "Falha simulada",
                    "replica", replica
            );
        } catch (IllegalArgumentException e) {
            return Map.of("erro", e.getMessage());
        }
    }

    @GetMapping("/status")
    public Map<String, Boolean> status() {
        return gerenciador.status();
    }

    @GetMapping("/executar")
    public ResponseEntity<?> executar(@RequestParam String operacao) {
        try {
            String resultado = gerenciador.executar(operacao);

            return ResponseEntity.ok(
                    Map.of("resultado", resultado)
            );
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("erro", e.getMessage()));
        }
    }
}