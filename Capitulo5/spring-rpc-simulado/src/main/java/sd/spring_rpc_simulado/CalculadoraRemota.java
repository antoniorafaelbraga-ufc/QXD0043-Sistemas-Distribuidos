package sd.spring_rpc_simulado;

import org.springframework.stereotype.Component;

@Component
public class CalculadoraRemota {

    public int soma(int a, int b) {
        return a + b;
    }
}