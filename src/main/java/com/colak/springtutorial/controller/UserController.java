package com.colak.springtutorial.controller;

import com.colak.springtutorial.constants.UserConstants;
import com.colak.springtutorial.dto.ResponseDto;
import com.colak.springtutorial.dto.UserDetailsDto;
import com.colak.springtutorial.dto.UserDto;
import com.colak.springtutorial.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return new ResponseEntity<>(ResponseDto.builder()
                .statusCode(HttpStatus.CREATED.toString())
                .statusMsg(UserConstants.REGISTRATION_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<UserDetailsDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDetailsDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(UserDto userDto) {
        boolean isUpdated = userService.updateUser(userDto);
        if (!isUpdated)
            return new ResponseEntity<>(ResponseDto.builder()
                    .statusCode(HttpStatus.EXPECTATION_FAILED.toString())
                    .statusMsg(UserConstants.UPDATE_FAILED)
                    .build(), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(ResponseDto.builder()
                .statusMsg(UserConstants.UPDATE_SUCCESS)
                .statusCode(HttpStatus.OK.toString())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable String email) {
        boolean isDeleted = userService.deleteUser(email);
        if (!isDeleted)
            return new ResponseEntity<>(ResponseDto.builder()
                    .statusCode(HttpStatus.EXPECTATION_FAILED.toString())
                    .statusMsg(UserConstants.DELETION_FAILED)
                    .build(), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(ResponseDto.builder()
                .statusMsg(UserConstants.DELETION_SUCCESS)
                .statusCode(HttpStatus.OK.toString())
                .build(), HttpStatus.OK);
    }
}
