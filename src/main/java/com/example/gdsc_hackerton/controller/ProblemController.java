package com.example.gdsc_hackerton.controller;

import com.example.gdsc_hackerton.dto.ProblemDto;
import com.example.gdsc_hackerton.dto.ProblemListDto;
import com.example.gdsc_hackerton.dto.UploadDto;
import com.example.gdsc_hackerton.dto.UserAnswerDto;
import com.example.gdsc_hackerton.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @PostMapping("code")
    @ResponseBody
    public String upload(@RequestBody UploadDto data) {
        problemService.save(data.getTopic(), data.getCode(), data.getCategory());

        return "OK";
    }

    @GetMapping("code")
    @ResponseBody
    public List<ProblemListDto> getProblemList() {
        return problemService.getProblemList();
    }

    @GetMapping("code/{problemId}")
    @ResponseBody
    public ProblemDto getProblem(@PathVariable("problemId") Long problemId) {
        return problemService.getProblem(problemId);
    }

    @PostMapping("code/{problemId}")
    @ResponseBody
    public String isCorrectAnswer(@PathVariable("problemId") Long problemId,
                                  @RequestBody UserAnswerDto answer) {
        if(problemService.isCorrect(problemId, answer))
            return "OK";

        return "";
    }
}
