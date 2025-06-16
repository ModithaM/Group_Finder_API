package com.moditha.group_finder.service;

import com.moditha.group_finder.exceptions.ServerErrorException;
import com.moditha.group_finder.exceptions.UserNotFoundException;
import com.moditha.group_finder.model.User;
import com.moditha.group_finder.model.dto.RegisterRequestDTO;
import com.moditha.group_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User updateProfile(RegisterRequestDTO updateRequest) {
        try {
            Optional<User> oldUser = userRepository.findByUsername(updateRequest.getUsername());
            if (oldUser.isPresent()) {
                User user = oldUser.get();
                user.setEmail(updateRequest.getEmail());
                user.setFirstName(updateRequest.getFirstName());
                user.setLastName(updateRequest.getLastName());
                user.setSpecialization(updateRequest.getSpecialization());
                user.setYear(updateRequest.getYear());
                user.setSemester(updateRequest.getSemester());
                user.setBio(updateRequest.getBio());
                user.setSkills(updateRequest.getSkills());
                user.setGithub(updateRequest.getGithub());
                user.setLinkedin(updateRequest.getLinkedin());
                user.setProfilePicture(updateRequest.getProfilePicture());

                return userRepository.save(user);
            }
            else {
                throw new UserNotFoundException("User not found");
            }
        } catch (Exception e) {
            throw new ServerErrorException(e.getMessage());
        }
    }
}
