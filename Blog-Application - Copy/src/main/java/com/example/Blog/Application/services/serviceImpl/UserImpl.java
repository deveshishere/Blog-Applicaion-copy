package com.example.Blog.Application.services.serviceImpl;

import com.example.Blog.Application.DTO.GenericResponse;
import com.example.Blog.Application.DTO.UserDto;
import com.example.Blog.Application.entities.User;
import com.example.Blog.Application.exception.CodeException;
import com.example.Blog.Application.exception.ErrorCode;
import com.example.Blog.Application.exception.ResourceNotFoundException;
import com.example.Blog.Application.repositories.readOnlyRepo.UserRepo;
import com.example.Blog.Application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public ModelMapper modelMapper;
//
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public UserImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

//    @Autowired
//    private RoleRepo roleRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.DtoToUser(userDto);
        User save = this.userRepo.save(user);
        return this.UserToDto(save);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName().trim());
        user.setEmail(userDto.getEmail().trim());
        user.setPassword(userDto.getPassword().trim());
        user.setAbout(userDto.getAbout().trim());
        User save = this.userRepo.save(user);
        UserDto userDto1 = this.UserToDto(save);
        return userDto1;
    }

    @Override
    public UserDto getUSerBtId(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return this.UserToDto(user);
    }

    @Override
    public ResponseEntity<GenericResponse> getAllUsers() {
        List<UserDto> collect = new ArrayList<>();
        try {
            List<User> users = this.userRepo.findAll();
            if (!CollectionUtils.isEmpty(users)){
                collect = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
            }else{
                throw new CodeException("User Not found", ErrorCode.COMMON);
            }
            return new ResponseEntity<>(new GenericResponse("User Fetched Successfully", true, HttpStatus.OK.value(), collect), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new GenericResponse(e.getMessage(), true, HttpStatus.OK.value(), null), HttpStatus.OK);
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

//    @Override
//    public UserDto registerNewUser(UserDto userDto) {
//
//        User user = this.modelMapper.map(userDto, User.class);
//
//        // encoded the password
//        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//
//        // roles
//        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
//
//        user.getRoles().add(role);
//
//        User newUser = this.userRepo.save(user);
//
//        return this.modelMapper.map(newUser, UserDto.class);
//    }

    private User DtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto UserToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

//
//    @Override
//    public ResponseEntity<GenericResponse> fetchAPI() {
//        ResponseEntity<RestAPIResponse> forEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/1", RestAPIResponse.class);
//        return ResponseEntity.ok(new GenericResponse("Success", true, HttpStatus.OK.value(), forEntity.getBody()));
//    }

}
