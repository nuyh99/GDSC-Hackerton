package com.example.gdsc_hackerton.dto;

import com.example.gdsc_hackerton.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadDto {
    private Category category;
    private String topic;
    private String code;
}
