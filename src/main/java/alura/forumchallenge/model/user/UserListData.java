package alura.forumchallenge.model.user;

public record UserListData(
        Long id,
        String userName,
        String email
){
        public UserListData (User user) {
            this(user.getId(), user.getUserName(), user.getEmail());
        }
}
