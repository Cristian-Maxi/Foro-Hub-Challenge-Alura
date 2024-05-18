package ForoHubChallengeAlura.Entities;

import ForoHubChallengeAlura.Dto.CursoDTO;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;

    public Curso(CursoDTO curso) {
        this.nombre = curso.nombre();
        this.categoria = curso.categoria();
    }

    public Curso actualizarCurso(CursoDTO curso) {
        if (curso.nombre() != null) {
            this.nombre = curso.nombre();
        }
        if (curso.categoria() != null) {
            this.categoria = curso.categoria();
        }
        return this;
    }
}
