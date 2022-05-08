package fiiPractic.TwitterWebApp.service;

import fiiPractic.TwitterWebApp.model.dto.UserRegisterDto;
import fiiPractic.TwitterWebApp.repos.Dao.UserDAO;
import fiiPractic.TwitterWebApp.repos.util.converter.UserConverter;
import fiiPractic.TwitterWebApp.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service

public class UserServiceImpl  implements UserService  {

    @Autowired
    private UserDAO userRepository;

    private UserConverter userConverter;
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Integer id) {
        return UserConverter.toUserDto(userRepository.getUserById(id));
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        return UserConverter.toUserDto(userRepository.getUserByUserName(userName));
    }

    @Override
    public List<UserDto> getUserByFirstName(String firstName) {
        return userRepository.getUserByFirstName(firstName).stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUserByLastName(String lastName) {
        return userRepository.getUserByLastName(lastName).stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void registerUser(UserRegisterDto user){
        userRepository.validateUser(user);
        userRepository.createUser(user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
    }




    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteUser(userId);
    }


}
