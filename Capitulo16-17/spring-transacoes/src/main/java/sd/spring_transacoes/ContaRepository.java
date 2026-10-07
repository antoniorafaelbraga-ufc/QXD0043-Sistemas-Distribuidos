package sd.spring_transacoes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaBancaria, String> {
}