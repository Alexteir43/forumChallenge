package alura.forumchallenge.model.topic;

import alura.forumchallenge.model.user.User;

import java.time.LocalDateTime;

public record TopicListData(
        Long id,
        String message,
        String courseName,
        String title,
        LocalDateTime creationDate
){
public TopicListData(Topic topic) {
    this(topic.getId(), topic.getMessage(), topic.getCourseName(), topic.getTitle(), topic.getCreationDate());
}
}
