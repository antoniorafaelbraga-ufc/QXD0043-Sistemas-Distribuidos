/**
 * CAPÍTULO 5 — Invocação Remota (RPC/RMI)
 *
 * Este exemplo simula transparência de localização.
 * O Controller chama o @Service, que chama o @Component sem depender
 * de detalhes de rede ou da localização do serviço.
 *
 * Nesta demonstração os dois beans estão na mesma aplicação Spring.
 * Em uma implementação RPC real, o @Component poderia estar em
 * outro processo ou máquina, acessado por um proxy remoto.
 */

package sd.spring_rpc_simulado;

import org.springframework.stereotype.Service;

@Service
public class ClienteRpcService {

    private final CalculadoraRemota calculadoraRemota;

    public ClienteRpcService(CalculadoraRemota calculadoraRemota) {
        this.calculadoraRemota = calculadoraRemota;
    }

    /**
     * Simula uma chamada RPC.
     * O cliente chama um serviço por meio de uma interface/objeto,
     * sem precisar lidar com detalhes da localização dele.
     */
    public int solicitarSoma(int a, int b) {
        return calculadoraRemota.soma(a, b);
    }
}