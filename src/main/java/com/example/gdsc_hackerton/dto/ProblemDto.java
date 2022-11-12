package com.example.gdsc_hackerton.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class ProblemDto {
    private final Long problemId;
    private final String topic;
    private final List<String> codes;
}
