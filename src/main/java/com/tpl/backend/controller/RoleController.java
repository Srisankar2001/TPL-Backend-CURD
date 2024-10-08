package com.tpl.backend.controller;

import com.tpl.backend.config.Response;
import com.tpl.backend.dto.RoleDTO;
import com.tpl.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public Response<?> getRoles(){
        return roleService.getRoles();
    }
    @GetMapping("/{id}")
    public Response<?> getRole(@PathVariable int id){
        RoleDTO roleDTO = RoleDTO.builder().id(id).build();
        return roleService.createRole(roleDTO);
    }
    @PostMapping("/")
    public Response<?> createRole(@RequestBody RoleDTO roleDTO){
        return roleService.createRole(roleDTO);
    }
    @PutMapping("/{id}")
    public Response<?> updateRole(@PathVariable int id,@RequestBody RoleDTO roleDTO){
        roleDTO.setId(id);
        return roleService.updateRole(roleDTO);
    }
    @DeleteMapping("/{id}")
    public Response<?> deleteRole(@PathVariable int id){
        RoleDTO roleDTO = RoleDTO.builder().id(id).build();
        return roleService.deleteRole(roleDTO);
    }
}
