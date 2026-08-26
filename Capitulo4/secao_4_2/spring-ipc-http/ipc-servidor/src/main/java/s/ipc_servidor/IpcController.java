/**
 * CAPÍTULO 4 — Comunicação entre Processos (IPC)
 *
 * Este projeto representa o SERVIDOR da comunicação.
 * Ele executa em uma aplicação Spring Boot independente,
 * configurada para a porta 8081.
 *
 * O cliente não chama esta classe diretamente. Ele envia uma
 * requisição HTTP para o endpoint /calculadora/soma.
 *
 * Assim, a troca de dados ocorre entre dois processos distintos
 * por HTTP, no modelo requisição-resposta.
 */

package s.ipc_servidor;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class IpcController {

    private final ServicoCalculadora calculadora;

    public IpcController(ServicoCalculadora calculadora) {
        this.calculadora = calculadora;
    }

    @GetMapping("/soma")
    public Map<String, Object> soma( @RequestParam int a, @RequestParam int b) {

        return Map.of(
                "a", a,
                "b", b,
                "resultado", calculadora.soma(a, b),
                "servidor", "ipc-servidor"
        );
    }
}
