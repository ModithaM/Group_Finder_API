package com.moditha.group_finder.service;

import com.moditha.group_finder.model.User;
import com.moditha.group_finder.model.dto.LoginRequestDTO;
import com.moditha.group_finder.model.dto.RegisterRequestDTO;
import com.moditha.group_finder.model.enums.Role;
import com.moditha.group_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(LoginRequestDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

    public boolean addUser(RegisterRequestDTO registerRequest) {
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setEmail(registerRequest.getEmail());
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setSpecialization(registerRequest.getSpecialization());
        newUser.setYear(registerRequest.getYear());
        newUser.setSemester(registerRequest.getSemester());
        newUser.setBio(registerRequest.getBio());
        newUser.setSkills(registerRequest.getSkills());
        newUser.setGithub(registerRequest.getGithub());
        newUser.setLinkedin(registerRequest.getLinkedin());
        newUser.setRole(Role.MEMBER);

        if(userRepository.findByUsername(newUser.getUsername()).isPresent()){
            return false;
        }
        else{
            userRepository.save(newUser);
            return true;
        }
    }
}
