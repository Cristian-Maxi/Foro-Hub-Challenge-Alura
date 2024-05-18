package ForoHubChallengeAlura.Repository;

import ForoHubChallengeAlura.Entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopico extends JpaRepository<Topico, Long> {
    
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
