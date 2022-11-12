package com.example.gdsc_hackerton.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Problem {

    @Id @GeneratedValue
    @Column(name = "problem_id")
    private Long id;

    private String topic;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Code> codes=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    public Problem(String topic, Category category) {
        this.category = category;
        this.topic = topic;
    }
}
