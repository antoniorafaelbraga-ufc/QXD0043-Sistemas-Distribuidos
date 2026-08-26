package sd.spring_ipc_http;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * CONCEITO: IPC via HTTP REST
 *
 * Expõe o ServicoCalculadora como endpoint HTTP.
 * Cada chamada GET/POST representa uma invocação remota de procedimento
 * através da rede — o modelo mais comum de IPC em sistemas distribuídos modernos.
 *
 * Teste:
 *   GET http://localhost:8080/ipc/soma?a=10&b=5
 *   GET http://localhost:8080/ipc/divide?a=10&b=2
 */
@RestController
@RequestMapping("/ipc")
public class IpcController {

    private final ServicoCalculadora calculadora;

    public IpcController(ServicoCalculadora calculadora) {
        this.calculadora = calculadora;
    }

    @GetMapping("/soma")
    public Map<String, Object> soma(@RequestParam int a, @RequestParam int b) {
        return Map.of(
                "operacao", "soma",
                "a", a, "b", b,
                "resultado", calculadora.soma(a, b),
                "conceito", "IPC síncrono - modelo requisição/resposta"
        );
    }

    @GetMapping("/divide")
    public Map<String, Object> divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            return Map.of("erro", "Divisão por zero — exceção remota");
        }
        return Map.of(
                "operacao", "divisao",
                "a", a, "b", b,
                "resultado", calculadora.divide(a, b),
                "conceito", "IPC síncrono - modelo requisição/resposta"
        );
    }
}

