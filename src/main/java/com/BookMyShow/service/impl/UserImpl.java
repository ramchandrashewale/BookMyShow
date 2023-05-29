package com.BookMyShow.service.impl;

import com.BookMyShow.dto.UserDto;
import com.BookMyShow.entity.User;
import com.BookMyShow.exception.DuplicateRecordException;
import com.BookMyShow.repository.UserRepository;
import com.BookMyShow.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.existsByMobile(userDto.getMobile())){
            throw  new DuplicateRecordException("User is already present with mobile No"+ userDto.getMobile());
        }
        User user=modelMapper.map(userDto,User.class);

        User saveUser=userRepository.save(user);
        return modelMapper.map(saveUser,UserDto.class);
    }

    @Override
    public UserDto getUserInfo(Long user_id) {
        Optional<User> user=userRepository.findById(user_id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("user is not present with mobile No"+user_id);
        }

        return modelMapper.map(user,UserDto.class);
    }
}
