package com.example.gdsc_hackerton.service;

import com.example.gdsc_hackerton.domain.Category;
import com.example.gdsc_hackerton.domain.Code;
import com.example.gdsc_hackerton.domain.Problem;
import com.example.gdsc_hackerton.dto.ProblemDto;
import com.example.gdsc_hackerton.dto.ProblemListDto;
import com.example.gdsc_hackerton.dto.UserAnswerDto;
import com.example.gdsc_hackerton.repository.CodeRepository;
import com.example.gdsc_hackerton.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProblemService {
    private final CodeRepository codeRepository;
    private final ProblemRepository problemRepository;

    private final String INCLUDE = "include";
    private final String IMPORT = "import";
    private final String PACKAGE = "package";

    @Transactional
    public void save(String topic, String original, Category category) {
        Problem problem = new Problem(topic, category);
        problemRepository.save(problem);

        List<String> codes = codeToList(original);
        for (int i = 0; i < codes.size(); i++)
            codeRepository.save(new Code(i + 1, codes.get(i), problem));
    }

    public List<ProblemListDto> getProblemList() {
        List<Problem> problems = problemRepository.findAll();

        return problems.stream()
                .map(o -> new ProblemListDto(o.getId(), o.getCategory(), o.getTopic()))
                .collect(Collectors.toList());
    }

    public ProblemDto getProblem(Long problemId) {
        Problem problem = problemRepository.findById(problemId).get();

        List<String> codes = new ArrayList<>(codeRepository.findByProblemIdOrderByOriginalIndexAsc(problemId)
                .stream()
                .map(Code::getCodeString)
                .toList());
        Collections.shuffle(codes);

        return new ProblemDto(problemId, problem.getTopic(), codes);
    }

    public boolean isCorrect(Long problemId, UserAnswerDto userAnswer) {
        List<String> answer = codeRepository.findByProblemIdOrderByOriginalIndexAsc(problemId)
                .stream()
                .map(Code::getCodeString)
                .map(String::trim)
                .toList();

        List<String> user = userAnswer.getCodes().stream()
                .map(String::trim)
                .toList();

        for (int i = 0; i < answer.size(); i++)
            if(!answer.get(i).equals(user.get(i)))
                return false;

        return true;
    }

    private List<String> codeToList(String code) {
        StringTokenizer st = new StringTokenizer(code, "\n");

        List<String> codes = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String s = st.nextToken();

            if (s.isBlank() || s.contains(INCLUDE) || s.contains(IMPORT) || s.contains(PACKAGE))
                continue;

            codes.add(s);
        }

        return codes;
    }
}
