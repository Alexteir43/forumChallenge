package alura.forumchallenge.model.topic;

import alura.forumchallenge.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
    private String courseName;
    private String title;
    private LocalDateTime creationDate;
    private Boolean active;

    public Topic(){}

    public Topic(RegisterTopicData registerTopicData){
        this.message = registerTopicData.message();
        this.courseName = registerTopicData.courseName();
        this.title = registerTopicData.title();
        this.active = true;
    }

    //Getters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
//Setters


    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void actualizeData(@Valid ActualizeTopicData actualizeTopicData) {
        if (actualizeTopicData.courseName() != null){
            this.courseName = actualizeTopicData.courseName();
        }
        if (actualizeTopicData.message() != null){
            this.message = actualizeTopicData.message();
        }
        if (actualizeTopicData.title() != null){
            this.title = actualizeTopicData.title();
        }
    }

    public void disableTopic() {
        this.active = false;
    }
}
