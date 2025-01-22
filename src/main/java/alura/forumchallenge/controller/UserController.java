package alura.forumchallenge.controller;

import alura.forumchallenge.model.user.*;
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

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserResponseData> registerUser(@RequestBody @Valid RegisterUserData registerUserData, UriComponentsBuilder uriComponentsBuilder){
        User user = userRepository.save(new User(registerUserData));
        UserResponseData userResponseData = new UserResponseData(user.getId(), user.getUserName(), user.getEmail());
        URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(url).body(userResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<UserListData>> userList(@PageableDefault(size = 2)Pageable pagination){
        return ResponseEntity.ok(userRepository.findByActiveTrue(pagination).map(UserListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizeUser(@RequestBody @Valid ActualizeUserData actualizeUserData){
        User user = userRepository.getReferenceById(actualizeUserData.id());
        user.actualizeData(actualizeUserData);
        return  ResponseEntity.ok(new UserResponseData(user.getId(), user.getUserName(), user.getEmail()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        user.disableUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseData> returnUserData(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        var userData = new UserResponseData(user.getId(), user.getUserName(), user.getEmail());
        return ResponseEntity.ok(userData);
    }
}
