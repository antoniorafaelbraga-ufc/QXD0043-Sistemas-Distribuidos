package sd.spring_servico_nomes;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/naming")
public class NamingController {

    private final RegistroDeServicos registro;

    public NamingController(RegistroDeServicos registro) {
        this.registro = registro;
    }

    @PostMapping("/registrar")
    public Map<String, String> registrar(@RequestBody Map<String, String> dados) {
        String nome = dados.get("nome");
        String endereco = dados.get("endereco");

        registro.registrar(nome, endereco);

        return Map.of(
                "mensagem", "Serviço registrado",
                "nome", nome,
                "endereco", endereco
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(@RequestParam String nome) {
        String endereco = registro.buscar(nome);

        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of(
                "nome", nome,
                "endereco", endereco
        ));
    }

    @GetMapping("/listar")
    public Map<String, String> listar() {
        return registro.listar();
    }

    @DeleteMapping("/remover")
    public Map<String, String> remover(@RequestParam String nome) {
        registro.remover(nome);
        return Map.of("mensagem", "Serviço removido", "nome", nome);
    }
}