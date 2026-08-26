package s.ipc_servidor;

import org.springframework.stereotype.Service;

@Service
public class ServicoCalculadora {

    public int soma(int a, int b) {
        return a + b;
    }
}