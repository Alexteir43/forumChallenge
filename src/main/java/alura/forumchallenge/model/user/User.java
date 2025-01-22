package alura.forumchallenge.model.user;

import alura.forumchallenge.model.topic.Topic;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Topic> topics = new ArrayList<>();

    private String userName;
    private String email;
    private Boolean active;

    public User(){}

    public User(RegisterUserData registerUserData){
        this.userName = registerUserData.userName();
        this.email = registerUserData.email();
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() { return active;}



    public void actualizeData(@Valid ActualizeUserData actualizeUserData) {
        if (actualizeUserData.userName() != null){
            this.userName = actualizeUserData.userName();
        }
        if (actualizeUserData.email() != null){
            this.email = actualizeUserData.email();
        }


    }

    public void disableUser() {
            this.active =false;
    }
}
