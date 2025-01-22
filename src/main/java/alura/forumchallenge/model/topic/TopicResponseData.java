package alura.forumchallenge.model.topic;

import alura.forumchallenge.model.user.User;

import java.time.LocalDateTime;

public record TopicResponseData(
        Long id,
        String message,
        String courseName,
        String title,
        LocalDateTime creationDate
) {
}
