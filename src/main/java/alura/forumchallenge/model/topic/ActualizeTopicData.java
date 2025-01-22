package alura.forumchallenge.model.topic;

import jakarta.validation.constraints.NotNull;

public record ActualizeTopicData(
        @NotNull Long id,
        String message,
        String courseName,
        String title
) {
}
