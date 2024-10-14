package br.com.fiap.epictaska.user;

import jakarta.validation.constraints.Min;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(OAuth2User principal){
        if (userRepository.findByEmail(principal.getAttribute("email")).isEmpty())
            return userRepository.save(new User(principal));

        return null;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        return userRepository.findByEmail(email).orElseGet(
                () -> {
                    var user = new User(oauth2User);
                    return userRepository.save(user);
                }
        );
    }

    public void addScore(@Min(1) int score, User user) {

        user.setScore(user.getScore() + score);
        userRepository.save(user);

    }
}
