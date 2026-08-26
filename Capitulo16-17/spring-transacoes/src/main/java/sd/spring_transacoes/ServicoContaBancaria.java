package sd.spring_transacoes;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoContaBancaria {

    private final ContaRepository repository;

    public ServicoContaBancaria(ContaRepository repository) {
        this.repository = repository;
    }

    public ContaBancaria criar(String id, String titular, BigDecimal saldo) {
        return repository.save(new ContaBancaria(id, titular, saldo));
    }

    public List<ContaBancaria> listar() {
        return repository.findAll();
    }

    /**
     * CAPÍTULOS 16 E 17 — Transações
     *
     * @Transactional torna débito e crédito uma operação única.
     * Se ocorrer erro, como saldo insuficiente, uma exceção é lançada
     * e o Spring desfaz todas as alterações desta transferência (rollback).
     */
    @Transactional
    public void transferir(String origemId, String destinoId, BigDecimal valor) {
        ContaBancaria origem = repository.findById(origemId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Conta de origem não encontrada"));

        ContaBancaria destino = repository.findById(destinoId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Conta de destino não encontrada"));

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        origem.debitar(valor);
        destino.creditar(valor);
    }
}