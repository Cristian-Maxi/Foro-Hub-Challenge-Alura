package ForoHubChallengeAlura.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String mensaje;
    
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    
    private LocalDateTime fechaCreacion;
    
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    
    private String solucion;
}
