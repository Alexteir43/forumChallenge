package alura.forumchallenge.model.topic;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegisterTopicData(
        @NotNull Long userId,
        @NotBlank String message,
        @NotBlank String courseName,
        @NotBlank String title
) {
}
