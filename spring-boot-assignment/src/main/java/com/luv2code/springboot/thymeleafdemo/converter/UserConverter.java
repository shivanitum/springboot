package com.luv2code.springboot.thymeleafdemo.converter;

import com.luv2code.springboot.thymeleafdemo.dto.UserDTO;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO entityToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEnabled(user.getEnabled());

        userDTO.setTheAuthority(user.getTheAuthority());
        return userDTO;

    }

    /*public User dtoToEntity(UserDTO userDTO)
    {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEnabled(userDTO.getEnabled());
        user.setTheAuthority(userDTO.getTheAuthority());

        return user;
    }*/
}
