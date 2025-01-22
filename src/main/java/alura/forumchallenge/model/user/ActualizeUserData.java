package alura.forumchallenge.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizeUserData(
        @NotNull Long id,
        String userName,
        String email
) {
}
