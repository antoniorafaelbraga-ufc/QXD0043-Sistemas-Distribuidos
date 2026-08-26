package sd.spring_ipc_http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CONCEITO: Comunicação entre Processos (IPC) — Seção 4.2
 *
 * Simula uma chamada de procedimento remoto (RPC).
 * No mundo real, esse @Service estaria em outro processo/máquina.
 * O Spring injeta a dependência de forma transparente, ilustrando
 * o princípio de transparência de localização dos sistemas distribuídos.
 *
 * @Component / @Service → registra o bean no contexto Spring (equivale
 * a publicar o serviço num servidor de nomes local).
 */
@Service
public class ServicoCalculadora {

    private static final Logger log = LoggerFactory.getLogger(ServicoCalculadora.class);

    /**
     * RPC síncrona simples: cliente envia (a, b), servidor devolve resultado.
     * Equivalente ao modelo requisição-resposta do Coulouris (Fig. 4.1).
     */
    public int soma(int a, int b) {
        log.info("[IPC] Requisição recebida: soma({}, {})", a, b);
        int resultado = a + b;
        log.info("[IPC] Resposta enviada: {}", resultado);
        return resultado;
    }

    public double divide(double a, double b) {
        log.info("[IPC] Requisição recebida: divide({}, {})", a, b);
        double resultado = a / b;
        log.info("[IPC] Resposta enviada: {}", resultado);
        return resultado;
    }

    /**
     * Demonstra tratamento de exceção remota.
     * No RPC distribuído, exceções do servidor viajam de volta ao cliente.
     */
    public void divideSeguro(double a, double b) {
        try {
            if (b == 0) throw new ArithmeticException("Divisão por zero não permitida");
            double resultado = a / b;
            log.info("[IPC] Resultado: {}", resultado);
        } catch (ArithmeticException e) {
            log.warn("[IPC] Exceção remota capturada no cliente: {}", e.getMessage());
        }
    }
}

