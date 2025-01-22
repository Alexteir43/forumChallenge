package alura.forumchallenge.repository;

import alura.forumchallenge.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientRepository extends JpaRepository<Client, Long> {

    UserDetails findByLogin(String username);
}
