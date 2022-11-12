package com.example.gdsc_hackerton.repository;

import com.example.gdsc_hackerton.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findByProblemIdOrderByIndexAsc(Long problemID);
}
