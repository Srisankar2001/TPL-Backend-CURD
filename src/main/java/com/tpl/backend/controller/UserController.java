package com.tpl.backend.controller;

import com.tpl.backend.config.Response;
import com.tpl.backend.dto.UserDTO;
import com.tpl.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public Response<?> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public Response<?> getUser(@PathVariable int id){
        UserDTO userDTO = UserDTO.builder().id(id).build();
        return userService.getUser(userDTO);
    }
//    @PostMapping("/")
//    public Response<?> createUser(@RequestBody UserDTO userDTO){
//        return userService.createUser(userDTO);
//    }
    @PutMapping("/{id}")
    public Response<?> updateUser(@PathVariable int id,@RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return userService.updateUser(userDTO);
    }
    @DeleteMapping("/{id}")
    public Response<?> deleteUser(@PathVariable int id){
        UserDTO userDTO = UserDTO.builder().id(id).build();
        return userService.deleteUser(userDTO);
    }
}
