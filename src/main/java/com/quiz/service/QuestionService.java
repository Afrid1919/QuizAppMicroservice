package com.quiz.service;

import com.quiz.entity.Question;
import com.quiz.exception.QuestionNotFoundException;
import com.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public List<Question> getQuestionByCategory(String category){
        return questionRepository.findByCategory(category);
    }

    public String addQuestion(Question question){
        questionRepository.save(question);
        return "Question added successfully";
    }

    public String updateQuestion(Integer id, Question question) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));

        existingQuestion.setQuestionTitle(question.getQuestionTitle());
        existingQuestion.setCategory(question.getCategory());
        existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
        existingQuestion.setOption1(question.getOption1());
        existingQuestion.setOption2(question.getOption2());
        existingQuestion.setOption3(question.getOption3());
        existingQuestion.setOption4(question.getOption4());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());

        questionRepository.save(existingQuestion);
        return "update successful";
    }

    public void deleteQuestion(Integer id) {
        questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id));

        questionRepository.deleteById(id);
    }
}
