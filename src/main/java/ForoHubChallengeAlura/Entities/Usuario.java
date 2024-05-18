package ForoHubChallengeAlura.Entities;

import ForoHubChallengeAlura.Dto.UsuarioDTO;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correoElectronico;

    private String contrasena;

    @OneToMany(mappedBy = "autor")
    private List<Topico> topíco;

    @ManyToMany
    @JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfiles;

    public Usuario(UsuarioDTO autor) {
        this.nombre = autor.nombre();
        this.correoElectronico = autor.correoElectronico();
        this.contrasena = autor.contrasena();
    }

    public Usuario actualizarUsuario(UsuarioDTO autor) {
        if (autor.nombre() != null) {
            this.nombre = autor.nombre();
        }
        if (autor.correoElectronico() != null) {
            this.correoElectronico = autor.correoElectronico();
        }
        if (autor.contrasena() != null) {
            this.contrasena = autor.contrasena();
        }
        return this;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
