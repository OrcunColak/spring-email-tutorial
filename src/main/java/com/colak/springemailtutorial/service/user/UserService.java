package com.colak.springemailtutorial.service.user;


import com.colak.springemailtutorial.constants.UserConstants;
import com.colak.springemailtutorial.dto.UserDetailsDto;
import com.colak.springemailtutorial.dto.UserDto;
import com.colak.springemailtutorial.exception.ResourceNotFoundException;
import com.colak.springemailtutorial.exception.UserAlreadyExistsException;
import com.colak.springemailtutorial.jpa.User;
import com.colak.springemailtutorial.mapper.UserMapper;
import com.colak.springemailtutorial.repository.UserRepository;
import com.colak.springemailtutorial.service.email.EmailDetails;
import com.colak.springemailtutorial.service.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    public void registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException(UserConstants.USER_ALREADY_EXISTS);
        }
        User user = UserMapper.mapToUser(new User(), userDto);
        userRepository.save(user);

        EmailDetails registrationSuccess = EmailDetails.builder()
                .recipient(userDto.getEmail())
                .subject("REGISTRATION SUCCESS")
                .messageBody("Registration Successful with mail id: " + userDto.getEmail())
                .build();
        emailService.sendEmail(registrationSuccess);
    }

    @Transactional
    public UserDetailsDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::mapToUserDetails)
                .orElseThrow(() -> new ResourceNotFoundException(UserConstants.USER_NOT_FOUND));
    }

    @Transactional
    public List<UserDetailsDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDetails)
                .toList();
    }

    @Transactional
    public boolean updateUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail())
                .map(user -> {
                    User updatedUser = UserMapper.mapToUser(user, userDto);
                    userRepository.save(updatedUser);
                    return true;
                })
                .orElseThrow(() -> new ResourceNotFoundException(UserConstants.USER_NOT_FOUND));
    }

    @Transactional
    public boolean deleteUser(String email) {
        if (!userRepository.existsByEmail(email))
            throw new ResourceNotFoundException(UserConstants.USER_NOT_FOUND);
        userRepository.deleteByEmail(email);
        return true;
    }
}
