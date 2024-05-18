package ForoHubChallengeAlura.Controller;

import ForoHubChallengeAlura.Dto.*;
import ForoHubChallengeAlura.Entities.Topico;
import ForoHubChallengeAlura.Services.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key") //Necesario para Swagger UI con Bearer Tokens
public class Controlador {

    @Autowired
    TopicoService topicoServicio;

    @GetMapping("/topicos")
    public ResponseEntity<List<ListarTopicoDTO>> listarTopicos() {
        List<Topico> topicos = topicoServicio.verTopicos();
        List<ListarTopicoDTO> listar = topicos.stream()
                .map(topico -> new ListarTopicoDTO(topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                true,
                new UsuarioDTO(topico.getAutor().getNombre(), topico.getAutor().getCorreoElectronico(), topico.getAutor().getContrasena()),
                new CursoDTO(topico.getCurso().getNombre(), topico.getCurso().getCategoria())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listar);
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<SelectTopicoDTO> mostrarTopico(@PathVariable Long id) {
        Topico topico = topicoServicio.elegirTopico(id);
        SelectTopicoDTO mostrar = new SelectTopicoDTO(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                true,
                new UsuarioDTO(topico.getAutor().getNombre(), topico.getAutor().getCorreoElectronico(), topico.getAutor().getContrasena()),
                new CursoDTO(topico.getCurso().getNombre(), topico.getCurso().getCategoria()));
        return ResponseEntity.ok(mostrar);
    }

    @Transactional
    @PutMapping("/modificar")
    public ResponseEntity<ActualizarTopicoDTO> modificarTopico(@RequestBody ActualizarTopicoDTO actualizar) {
        Topico topico = topicoServicio.modificarElTopico(actualizar);
        ActualizarTopicoDTO datosActualizados = new ActualizarTopicoDTO(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                new UsuarioDTO(topico.getAutor().getNombre(), topico.getAutor().getCorreoElectronico(), topico.getAutor().getContrasena()),
                new CursoDTO(topico.getCurso().getNombre(), topico.getCurso().getCategoria()));
        return ResponseEntity.ok(datosActualizados);
    }

    @Transactional
    @PostMapping("/crear")
    public ResponseEntity<TopicoDTO> crearPost(@RequestBody @Valid TopicoDTO topicoDTO) {
        topicoServicio.registrarTopico(topicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoDTO);
    }
    
    @Transactional
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarPost(@PathVariable Long id) {
        topicoServicio.eliminarTopico(id);
        return ResponseEntity.ok("El usuario fue eliminado con éxito.");
    }
}
