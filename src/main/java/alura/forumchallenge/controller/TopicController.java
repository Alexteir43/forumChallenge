package alura.forumchallenge.controller;

import alura.forumchallenge.model.topic.*;
import alura.forumchallenge.model.user.User;
import alura.forumchallenge.repository.TopicRepository;
import alura.forumchallenge.repository.UserRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topics")

public class TopicController {

    @Autowired
   private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TopicResponseData> registerTopic(@RequestBody @Valid RegisterTopicData registerTopicData, UriComponentsBuilder uriComponentsBuilder){
       //User entity
        User user = userRepository.findById(registerTopicData.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + registerTopicData.userId()));
        //Topic entity
        Topic topic = new Topic(registerTopicData);
        topic.setUser(user);
        topic.setCreationDate(LocalDateTime.now());
        topic = topicRepository.save(topic);

        TopicResponseData topicResponseData = new TopicResponseData(
                topic.getId(),
                topic.getMessage(),
                topic.getCourseName(),
                topic.getTitle(),
                topic.getCreationDate()
        );

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponseData);

    }


    @GetMapping
    public ResponseEntity<Page<TopicListData>> topicList(@PageableDefault(size = 3)Pageable paginations){

        Page<Topic> topics =topicRepository.findByActiveTrue(paginations);
        Page<TopicListData> topicListDataPage = topics.map(TopicListData::new);
        return ResponseEntity.ok(topicListDataPage);

    }

    @PutMapping("/topics/{id}")
    @Transactional
    public ResponseEntity actualizeTopic(@PathVariable Long id, @RequestBody @Valid ActualizeTopicData actualizeTopicData){
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Topic not found with id: " + id));
        topic.actualizeData(actualizeTopicData);

       TopicResponseData responseData = new TopicResponseData(topic.getId(), topic.getMessage(), topic.getCourseName(), topic.getTitle(), topic.getCreationDate());
        return ResponseEntity.ok(responseData);

    }

    @DeleteMapping("/topics/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic =topicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Topic not found with id: " + id));
        topic.disableTopic();
        return ResponseEntity.noContent().build();
    }
}
