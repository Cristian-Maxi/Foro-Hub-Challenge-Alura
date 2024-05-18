package ForoHubChallengeAlura.Dto;

import java.time.LocalDateTime;

public record ListarTopicoDTO(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean estado,
        UsuarioDTO autor,
        CursoDTO curso
        ) {
}
