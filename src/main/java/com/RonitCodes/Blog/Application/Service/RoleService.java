package com.RonitCodes.Blog.Application.Service;

import com.RonitCodes.Blog.Application.Dto.Role.RoleRequestDto;
import com.RonitCodes.Blog.Application.Dto.Role.RoleResponseDto;
import com.RonitCodes.Blog.Application.Model.Role;
import com.RonitCodes.Blog.Application.Repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        Role addRole = modelMapper.map(roleRequestDto, Role.class);
        Role saveRole = roleRepository.save(addRole);

        return modelMapper.map(saveRole, RoleResponseDto.class);
    }

    public void deleteRole(Long roleId) {
        Role findRole = roleRepository.findById(roleId)
                .orElseThrow(()-> new EntityNotFoundException("Role not found"));

        roleRepository.delete(findRole);
    }


}
