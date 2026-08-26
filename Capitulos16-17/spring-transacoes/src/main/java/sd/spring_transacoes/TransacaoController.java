package sd.spring_transacoes;

import java.math.BigDecimal;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final ServicoContaBancaria servico;

    public TransacaoController(ServicoContaBancaria servico) {
        this.servico = servico;
    }

    @PostMapping("/contas")
    public ContaBancaria criarConta(@RequestBody Map<String, String> dados) {
        return servico.criar(
                dados.get("id"),
                dados.get("titular"),
                new BigDecimal(dados.get("saldo"))
        );
    }

    @GetMapping("/contas")
    public Object listarContas() {
        return servico.listar();
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody Map<String, String> dados) {
        try {
            servico.transferir(
                    dados.get("origem"),
                    dados.get("destino"),
                    new BigDecimal(dados.get("valor"))
            );

            return ResponseEntity.ok(
                    Map.of("mensagem", "Transferência concluída")
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "erro", e.getMessage(),
                            "resultado", "rollback"
                    )
            );
        }
    }
}