package sd.spring_replicacao_failover;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorReplicas {

    private final Map<String, Boolean> replicas = new LinkedHashMap<>();

    public void adicionarReplica(String nome) {
        replicas.put(nome, true);
    }

    public void simularFalha(String nome) {
        if (!replicas.containsKey(nome)) {
            throw new IllegalArgumentException("Réplica não encontrada");
        }

        replicas.put(nome, false);
    }

    public Map<String, Boolean> status() {
        return new LinkedHashMap<>(replicas);
    }

    /**
     * CAPÍTULO 18 — Replicação e Tolerância a Falhas
     *
     * Tenta executar a operação em cada réplica disponível.
     * Caso uma réplica falhe, a exceção é capturada e o sistema
     * tenta a próxima, simulando failover.
     */
    public String executar(String operacao) {
        for (Map.Entry<String, Boolean> replica : replicas.entrySet()) {
            try {
                if (!replica.getValue()) {
                    throw new IllegalStateException("Réplica indisponível");
                }

                return "Operação '" + operacao
                        + "' executada pela " + replica.getKey();
            } catch (IllegalStateException e) {
                System.out.println(
                        "Falha em " + replica.getKey()
                                + ". Tentando a próxima réplica..."
                );
            }
        }

        throw new IllegalStateException(
                "Nenhuma réplica está disponível para executar a operação"
        );
    }
}