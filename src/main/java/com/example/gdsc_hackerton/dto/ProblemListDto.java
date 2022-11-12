package com.example.gdsc_hackerton.dto;

import com.example.gdsc_hackerton.domain.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ProblemListDto {
    private final Long id;
    private final Category category;
    private final String topic;
}
