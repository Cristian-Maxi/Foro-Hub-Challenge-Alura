package ForoHubChallengeAlura.Dto;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        UsuarioDTO autor,
        CursoDTO curso) {
}
