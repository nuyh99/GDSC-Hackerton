package com.example.gdsc_hackerton.service;

import com.example.gdsc_hackerton.domain.Category;
import com.example.gdsc_hackerton.domain.Code;
import com.example.gdsc_hackerton.domain.Problem;
import com.example.gdsc_hackerton.dto.ProblemDto;
import com.example.gdsc_hackerton.dto.UserAnswerDto;
import com.example.gdsc_hackerton.repository.CodeRepository;
import com.example.gdsc_hackerton.repository.ProblemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProblemServiceTest {
    @Autowired ProblemService problemService;
    @Autowired ProblemRepository problemRepository;
    @Autowired CodeRepository codeRepository;

    String original = "apple\nbanana\ndog\napple";

    @Test
    void 문제_저장() throws Exception{
        //given
        problemService.save("제목", original,  Category.JAVA);

        //when
        Problem problem = problemRepository.findById(1L).get();
        List<Code> codes = codeRepository.findByProblemIdOrderByIndexAsc(problem.getId());
        ProblemDto problem1 = problemService.getProblem(problem.getId());

        //then
        System.out.println(problem.getTopic());
        codes.forEach(System.out::println);

        problemService.getProblemList().forEach(System.out::println);

        List<String> answer = codes.stream().map(Code::getCode).toList();
        UserAnswerDto dto = new UserAnswerDto();
        dto.setCodes(answer);
        Assertions.assertThat(problemService.isCorrect(problem.getId(), dto)).isTrue();

        System.out.println(problem1);
    }


    @Test
    void 앞에_공백() throws Exception{
        //given
        String answer = "apple";
        String userAnswer = " apple";

        //when

        //then
        assertThat(answer).isNotEqualTo(userAnswer);
    }
}