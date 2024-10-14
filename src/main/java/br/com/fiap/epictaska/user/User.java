package br.com.fiap.epictaska.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@Table(name = "epicusers")
public class User extends DefaultOAuth2User {

    @Id
    UUID id = UUID.randomUUID();

    String name;

    @Column(unique = true)
    String email;

    String avatar;

    @Min(0)
    int score;

    public User(OAuth2User principal){
        super(
                List.of(new SimpleGrantedAuthority("USER")),
                principal.getAttributes(),
                "name"
        );
        this.name = principal.getAttribute("name");
        this.email = principal.getAttribute("email");
        this.avatar = principal.getAttribute("avatar_url");
    }

    public User() {
        super(
                List.of(),
                Map.of("name", "Anonymous"),
                "name"
        );
    }
}
