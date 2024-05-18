package ForoHubChallengeAlura.Dto;

import java.time.LocalDateTime;

public record SelectTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean estado,
        UsuarioDTO autor,
        CursoDTO curso) {
}
