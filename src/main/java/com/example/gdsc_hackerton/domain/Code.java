package com.example.gdsc_hackerton.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "problem")
public class Code {

    @Id
    @GeneratedValue
    @Column(name = "code_id")
    private Long id;

    private int originalIndex;

    private String codeString;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public Code(int originalIndex, String codeString, Problem problem) {
        this.originalIndex = originalIndex;
        this.codeString = codeString;
        setProblem(problem);
    }

    /**
     * 연관 관계 매핑
     */
    private void setProblem(Problem problem) {
        this.problem = problem;
        problem.getCodes().add(this);
    }
}
