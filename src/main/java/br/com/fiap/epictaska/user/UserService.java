package br.com.fiap.epictaska.user;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(OAuth2User principal){
        if (userRepository.findByEmail(principal.getAttribute("email")).isEmpty())
            return userRepository.save(new User(principal));

        return null;
    }

}
