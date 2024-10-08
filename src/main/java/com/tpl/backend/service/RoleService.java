package com.tpl.backend.service;

import com.tpl.backend.config.Response;
import com.tpl.backend.dto.RoleDTO;
import com.tpl.backend.entity.Role;
import com.tpl.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Response<?> getRoles(){
        List<Role> roles = roleRepository.findAll();
        return Response.builder()
                .status(true)
                .message(null)
                .data(roles)
                .build();
    }

    public Response<?> getRole(RoleDTO roleDTO){
        Optional<Role> role = roleRepository.findById(roleDTO.getId());
        if(role.isPresent()){
            return Response.builder()
                    .status(true)
                    .message(null)
                    .data(role)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("Role Not Found")
                    .data(null)
                    .build();
        }
    }

    public Response<?> createRole(RoleDTO roleDTO){
        Optional<Role> existingRole = roleRepository.findOneByName(roleDTO.getName());
        if(existingRole.isEmpty()){
            Role role = Role.builder().name(roleDTO.getName()).build();
            roleRepository.save(role);
            return Response.builder()
                    .status(true)
                    .message("Role Created Successfully")
                    .data(null)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("Role Already Exists")
                    .data(null)
                    .build();
        }
    }

    public Response<?> updateRole(RoleDTO roleDTO){
        Optional<Role> existingRole = roleRepository.findById(roleDTO.getId());
        if(existingRole.isPresent()){
            Optional<Role> isRoleAlreadyExist = roleRepository.findOneByName(roleDTO.getName());
            if(isRoleAlreadyExist.isEmpty()){
                Role role = existingRole.get();
                role.setName(roleDTO.getName());
                roleRepository.save(role);
                return Response.builder()
                        .status(true)
                        .message("Role Updated Successfully")
                        .data(null)
                        .build();
            } else {
                return Response.builder()
                        .status(false)
                        .message("Role Already Exists")
                        .data(null)
                        .build();
            }
        }else{
            return Response.builder()
                    .status(false)
                    .message("Role Not Exists")
                    .data(null)
                    .build();
        }
    }

    public Response<?> deleteRole(RoleDTO roleDTO){
        Optional<Role> existingRole = roleRepository.findById(roleDTO.getId());
        if(existingRole.isPresent()){
            roleRepository.delete(existingRole.get());
            return Response.builder()
                    .status(true)
                    .message("Role Deleted Successfully")
                    .data(null)
                    .build();
        }else{
            return Response.builder()
                    .status(false)
                    .message("Role Not Exists")
                    .data(null)
                    .build();
        }
    }
}
