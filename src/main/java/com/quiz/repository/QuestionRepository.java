package com.quiz.repository;

import com.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value="Select * from question q where q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery=true)
    List<Question> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);
}
