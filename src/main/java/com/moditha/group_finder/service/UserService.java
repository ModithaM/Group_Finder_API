package com.moditha.group_finder.service;

import com.moditha.group_finder.model.User;
import com.moditha.group_finder.model.dto.RegisterRequestDTO;
import com.moditha.group_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean addUser(RegisterRequestDTO registerRequest) {
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword());
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

        if(userRepository.findByUsername(newUser.getUsername()).isPresent()){
            return false;
        }
        else{
            userRepository.save(newUser);
            return true;
        }
    }
}
