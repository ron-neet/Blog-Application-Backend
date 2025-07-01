package com.RonitCodes.Blog.Application.Dto.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {

    private Long roleId;
    private String role;
}
