
package com.example.SecurityTest;

import com.example.SecurityTest.domain.User;
import com.example.SecurityTest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Bean
    private void AuctionInit() {


        User user = new User();
        user.setUsername("test");
        user.setPassword(encoder.encode("1234"));
        user.setRoles("ROLE_MEMBER");

        User user2 = new User();
        user2.setUsername("root");
        user2.setPassword(encoder.encode("1234"));
        user2.setRoles("ROLE_ADMIN");

        userRepository.save(user);

        userRepository.save(user2);

    }
}