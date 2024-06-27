package pro.sky.course_work2k.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.course_work2k.exception.NotQuestionsException;
import pro.sky.course_work2k.exception.QuestionAlreadyAddedException;
import pro.sky.course_work2k.exception.QuestionNotFoundException;
import pro.sky.course_work2k.model.Question;
import pro.sky.course_work2k.service.QuestionService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyAddedException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NotQuestionsException();
        }
        int randomQuestion = ThreadLocalRandom.current().nextInt(questions.size());
        return questions.stream()
                .skip(randomQuestion)
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }

}
