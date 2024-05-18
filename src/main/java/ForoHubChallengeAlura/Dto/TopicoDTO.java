package ForoHubChallengeAlura.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull 
        @Valid
        UsuarioDTO autor,
        @NotNull 
        @Valid
        CursoDTO curso) { 
}

