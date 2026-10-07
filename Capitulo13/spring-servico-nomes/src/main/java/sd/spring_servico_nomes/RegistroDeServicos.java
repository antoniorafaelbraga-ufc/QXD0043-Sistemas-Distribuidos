/**
 * CAPÍTULO 13 — Serviço de Nomes
 *
 * Simula um registry de serviços. Cada serviço possui um nome lógico
 * e um endereço físico (URL). Clientes podem registrar e buscar serviços
 * sem precisar conhecer previamente o endereço deles.
 *
 * O @Component tem escopo singleton por padrão: existe um único registro
 * compartilhado durante a execução da aplicação.
 */

package sd.spring_servico_nomes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class RegistroDeServicos {

    private final Map<String, String> servicos = new ConcurrentHashMap<>();

    public void registrar(String nome, String endereco) {
        servicos.put(nome, endereco);
    }

    public String buscar(String nome) {
        return servicos.get(nome);
    }

    public Map<String, String> listar() {
        return servicos;
    }

    public void remover(String nome) {
        servicos.remove(nome);
    }
}