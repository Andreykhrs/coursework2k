package pro.sky.course_work2k.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.course_work2k.exception.NotQuestionsException;
import pro.sky.course_work2k.exception.QuestionAlreadyAddedException;
import pro.sky.course_work2k.exception.QuestionNotFoundException;
import pro.sky.course_work2k.model.Question;
import pro.sky.course_work2k.service.Impl.JavaQuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    private final List<Question> questions = List.of(
            new Question("Как называется контейнер для хранения разных данных?",
                    "Переменная"),
            new Question("Что значит объявление переменной?",
                    "Это создание переменной (выделение ячейки памяти, присвоение ей имени)."),
            new Question("Как называется процесс присваивание какого-то значения переменной?",
                    "Инициализация"),
            new Question("Как называется стандартная функция, которая помогает нам вывести результат " +
                    "нашего кода в консоль и посмотреть, что получилось",
                    "System.out.println ();"),
            new Question("Чтобы узнать остаток от деления, нужно?",
                    "Большее число, поделить на меньшее")

    );

    @BeforeEach
    public void beforeEach() {
        questions.forEach(questionService::add);
    }

    @AfterEach
    public void afterEach() {
        new ArrayList<>(questionService.getAll()).forEach(questionService::remove);
    }


    @Test
    public void add1Test() {
        int was = questionService.getAll().size();
        Question expected = new Question("Какой символ используется в операции остаток от деления?",
                "delenie");
        assertThat(questionService.getAll()).doesNotContain(expected);


        Question actual = questionService.add(new Question("Какой символ используется в операции остаток от деления?",
                "delenie"));

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was + 1);
        assertThat(questionService.getAll()).contains(expected);

    }

    @Test
    public void add2Test() {
        int was = questionService.getAll().size();
        Question expected = new Question("Какой символ используется в операции остаток от деления?",
                "delenie");
        assertThat(questionService.getAll()).doesNotContain(expected);


        Question actual = questionService.add("Какой символ используется в операции остаток от деления?",
                "delenie");

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was + 1);
        assertThat(questionService.getAll()).contains(expected);

    }

    @Test
    public void add1NegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(new Question("Как называется контейнер для хранения разных данных?",
                        "Переменная")));

    }

    @Test
    public void add2NegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add("Как называется контейнер для хранения разных данных?",
                        "Переменная"));

    }

    @Test
    public void removeTest() {
        int was = questionService.getAll().size();
        Question expected = new Question("Как называется контейнер для хранения разных данных?",
                "Переменная");
        assertThat(questionService.getAll()).contains(expected);


        Question actual = questionService.remove(new Question("Как называется контейнер для хранения разных данных?",
                "Переменная"));

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was - 1);
        assertThat(questionService.getAll()).doesNotContain(expected);

    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Какой символ используется в операции остаток от деления?",
                        "delenie")));

    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {

        afterEach();
        assertThatExceptionOfType(NotQuestionsException.class)
                .isThrownBy(() -> questionService.getRandomQuestion());
    }


}
