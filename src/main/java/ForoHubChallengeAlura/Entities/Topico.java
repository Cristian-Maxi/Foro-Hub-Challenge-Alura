package ForoHubChallengeAlura.Entities;

import ForoHubChallengeAlura.Dto.ActualizarTopicoDTO;
import ForoHubChallengeAlura.Dto.TopicoDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    //@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion;

    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public Topico(TopicoDTO topicoDto) {
        this.titulo = topicoDto.titulo();
        this.mensaje = topicoDto.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autor = new Usuario(topicoDto.autor());
        this.curso = new Curso(topicoDto.curso());
    }

    public void actualizarTopico(ActualizarTopicoDTO datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.autor() != null) {
            this.autor = autor.actualizarUsuario(datos.autor());
        }
        if (datos.curso() != null) {
            this.curso = curso.actualizarCurso(datos.curso());
        }
    }
}

//Formatear Hora:
//LocalDate today = LocalDate.now();
//DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//String formattedDate = today.format(dateTimeFormatter);  //17-02-2022
