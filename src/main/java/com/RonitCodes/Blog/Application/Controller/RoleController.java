package com.RonitCodes.Blog.Application.Controller;

import com.RonitCodes.Blog.Application.Dto.Role.RoleRequestDto;
import com.RonitCodes.Blog.Application.Dto.Role.RoleResponseDto;
import com.RonitCodes.Blog.Application.Service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/addRole")
    public ResponseEntity<RoleResponseDto> addRole(@RequestBody RoleRequestDto roleRequestDto) {

        RoleResponseDto response = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
