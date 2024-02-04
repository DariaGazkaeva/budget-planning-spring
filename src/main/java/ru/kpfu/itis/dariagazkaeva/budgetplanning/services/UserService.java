package ru.kpfu.itis.dariagazkaeva.budgetplanning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.LoginDto;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.User;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.dto.RegistrationDto;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.entities.UserAuthority;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.AuthorityRepository;
import ru.kpfu.itis.dariagazkaeva.budgetplanning.repositories.UserRepository;

import java.util.HashSet;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public boolean checkEmailUniqueness(String email) {
        return userRepository.findByEmail(email) == null;
    }

    @Transactional
    public User register(RegistrationDto registrationDto) {
        User user = User.builder()
                .email(registrationDto.getEmail())
                .password(bCryptPasswordEncoder.encode(registrationDto.getPassword()))
                .name(registrationDto.getName())
                .authorities(new HashSet<>())
                .build();
        user.addAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }
}
