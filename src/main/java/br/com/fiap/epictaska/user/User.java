package br.com.fiap.epictaska.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.UUID;

@Entity
@Data
@Table(name = "epicusers")
@NoArgsConstructor
public class User {

    @Id
    UUID id = UUID.randomUUID();

    String name;

    @Column(unique = true)
    String email;

    String avatar;

    @Min(0)
    int score;

    public User(OAuth2User principal){
        this.name = principal.getAttribute("name");
        this.email = principal.getAttribute("email");
        this.avatar = principal.getAttribute("avatar_url");
    }

}
