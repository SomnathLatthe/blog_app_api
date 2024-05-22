package com.somnath.blogappapis.services.Impl;

import com.somnath.blogappapis.config.AppConstants;
import com.somnath.blogappapis.entities.Role;
import com.somnath.blogappapis.entities.User;
import com.somnath.blogappapis.exceptions.ResourceNotFoundException;
import com.somnath.blogappapis.payloads.UserDto;
import com.somnath.blogappapis.repositories.RoleRepo;
import com.somnath.blogappapis.repositories.UserRepo;
import com.somnath.blogappapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        //encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        //roles
        Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoleSet().add(role);
        User newUser=this.userRepo.save(user);

        return this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);

        User savedUser=this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();

        List<UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto)
    {
        User user  =this.modelMapper.map(userDto,User.class);
        /*User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/
        return user;
    }

    public UserDto userToDto(User user)
    {
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        /*UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());*/
        return userDto;
    }
}
