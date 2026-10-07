/**
 * CAPÍTULO 4 — Comunicação entre Processos (IPC)
 *
 * Este projeto representa o CLIENTE da comunicação.
 * Ele executa em uma aplicação Spring Boot independente,
 * configurada para a porta 8080.
 *
 * Ao receber uma chamada em /ipc/soma, o cliente utiliza RestTemplate
 * para enviar uma requisição HTTP ao servidor em localhost:8081.
 *
 * O cliente conhece somente a URL do servidor; ele não possui acesso
 * direto às classes nem aos métodos Java do outro projeto.
 * Isso demonstra IPC usando HTTP entre processos independentes.
 */

package sd.ipc_cliente;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ipc")
public class IpcClienteController {

    private final RestTemplate restTemplate;

    public IpcClienteController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/soma")
    public ResponseEntity<?> soma(
            @RequestParam int a,
            @RequestParam int b) {

        String url = "http://localhost:8081/calculadora/soma?a=" + a + "&b=" + b;

        try {
            // Envia a solicitação pela rede ao outro processo (servidor IPC).
            // Se o servidor estiver desligado, ocorrerá uma exceção de comunicação.
            Map resposta = restTemplate.getForObject(url, Map.class);
            return ResponseEntity.ok(resposta);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of(
                            "erro", "O servidor IPC não está disponível",
                            "detalhe", e.getMessage()
                    ));
        }
    }
}