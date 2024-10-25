package com.meditechy.service;

 import com.meditechy.dto.UserDto;
 import com.meditechy.entity.User;
 import com.meditechy.repository.UserRepository;
 import com.meditechy.util.UserPrincipal;
 import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

 import java.util.Objects;
 import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ ={@Lazy} )
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new UserPrincipal(user);
    }

    public User save(UserDto user) {

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            User userByEmail = userRepository.findByEmail(user.getEmail()).orElse(null);
            if(!Objects.isNull(userByEmail))
                throw new IllegalArgumentException("Email already exists!");
        }

        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (userByPhoneNumber.isPresent()) {
            throw new IllegalArgumentException("Phone number already exists!");
        }

        User newUser = new User();
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        // Save the new user in the database
        return userRepository.save(newUser);
    }
}
