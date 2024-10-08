package com.tpl.backend.service;

import com.tpl.backend.config.Response;
import com.tpl.backend.dto.UserDTO;
import com.tpl.backend.entity.User;
import com.tpl.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Response<?> getUser(UserDTO userDTO){
        Optional<User> user = userRepository.findById(userDTO.getId());
        if(user.isPresent()){
            return Response.builder()
                    .status(true)
                    .message(null)
                    .data(user)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("User Not Found")
                    .data(null)
                    .build();
        }
    }

    public Response<?> getUsers(){
        List<User> users = userRepository.findAll();
        return Response.builder()
                .status(true)
                .message(null)
                .data(users)
                .build();

    }

    public Response<?> updateUser(UserDTO userDTO){
        Optional<User> existingUser = userRepository.findById(userDTO.getId());
        if(existingUser.isPresent()){
            existingUser.get().setName(userDTO.getName());
            userRepository.save(existingUser.get());
            return Response.builder()
                    .status(true)
                    .message("User Updated Successfully")
                    .data(null)
                    .build();

        }else{
            return Response.builder()
                    .status(false)
                    .message("User Not Exists")
                    .data(null)
                    .build();
        }
    }
    public Response<?> deleteUser(UserDTO userDTO){
        Optional<User> existingUser = userRepository.findById(userDTO.getId());
        if(existingUser.isPresent()){
            userRepository.delete(existingUser.get());
            return Response.builder()
                    .status(true)
                    .message("User Deleted Successfully")
                    .data(null)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("User Not Exists")
                    .data(null)
                    .build();
        }
    }

}
