package com.RonitCodes.Blog.Application.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    @NotNull(message = "Category Title is Required")
    @Size(min = 4, message = "Title should be of minimum 4")
    private String categoryTitle;

    @NotNull(message = "Description is mandatory")
    @Size(max = 25, message = "Description should be of maximum 25")
    private String categoryDescription;
}
