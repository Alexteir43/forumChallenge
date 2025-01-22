package alura.forumchallenge.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserData(
        @NotBlank String userName,
        @Email String email
) {
}
