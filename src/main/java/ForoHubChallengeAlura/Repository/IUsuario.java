package ForoHubChallengeAlura.Repository;

import ForoHubChallengeAlura.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuario extends JpaRepository<Usuario, Long> {
    UserDetails findByNombre(String username);
}
