package pro.sky.course_work2k.service;

import pro.sky.course_work2k.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
