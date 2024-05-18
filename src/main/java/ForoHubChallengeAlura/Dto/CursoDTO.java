package ForoHubChallengeAlura.Dto;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria) {
}
