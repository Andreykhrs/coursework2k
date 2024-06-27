package pro.sky.course_work2k.service;

import org.springframework.stereotype.Service;
import pro.sky.course_work2k.model.Question;

import java.util.Collection;

public interface QuestionService {
    public Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

}
