package sd.spring_rpc_simulado;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpc")
public class RpcController {

    private final ClienteRpcService clienteRpcService;

    public RpcController(ClienteRpcService clienteRpcService) {
        this.clienteRpcService = clienteRpcService;
    }

    @GetMapping("/soma")
    public Map<String, Object> soma(
            @RequestParam int a,
            @RequestParam int b) {

        int resultado = clienteRpcService.solicitarSoma(a, b);

        return Map.of(
                "operacao", "soma",
                "resultado", resultado,
                "conceito", "RPC simulado com @Service e @Component"
        );
    }
}