package sd.spring_transacoes;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ContaBancaria {

    @Id
    private String id;

    private String titular;
    private BigDecimal saldo;

    public ContaBancaria() {
    }

    public ContaBancaria(String id, String titular, BigDecimal saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }
}