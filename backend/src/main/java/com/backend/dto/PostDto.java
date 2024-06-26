package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int id;
    private int companyId;
    private BigDecimal maxSalary;
    private BigDecimal minSalary;
    private String phoneNumber;
    private String email;
    private String content;
    private String images;
    private String experience;
    private int locationId;
    private int levelId;
    private int languagesId;
}