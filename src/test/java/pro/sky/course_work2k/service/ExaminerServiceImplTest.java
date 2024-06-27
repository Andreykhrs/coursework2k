package pro.sky.course_work2k.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course_work2k.exception.IncorrectAmountException;
import pro.sky.course_work2k.model.Question;
import pro.sky.course_work2k.service.Impl.ExaminerServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

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
                    "Большее число, поделить на меньшее"));

    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    public void getQuestionsNegativeTest() {
        Assertions.assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(()->examinerService.getQuestions(4));

        Assertions.assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(()->examinerService.getQuestions(-1));
    }

    @Test
    public void getQuestionsTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Что значит объявление переменной?",
                        "Это создание переменной (выделение ячейки памяти, присвоение ей имени)."),
                new Question("Как называется процесс присваивание какого-то значения переменной?",
                        "Инициализация"),
                new Question("Чтобы узнать остаток от деления, нужно?",
                        "Большее число, поделить на меньшее"),
                new Question("Как называется стандартная функция, которая помогает нам вывести результат " +
                        "нашего кода в консоль и посмотреть, что получилось",
                        "System.out.println ();"),
                new Question("Чтобы узнать остаток от деления, нужно?",
                        "Большее число, поделить на меньшее"),
                new Question("Чтобы узнать остаток от деления, нужно?",
                        "Большее число, поделить на меньшее")
        );

        Assertions.assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(
                new Question("Что значит объявление переменной?",
                        "Это создание переменной (выделение ячейки памяти, присвоение ей имени)."),
                new Question("Как называется процесс присваивание какого-то значения переменной?",
                        "Инициализация"),
                new Question("Чтобы узнать остаток от деления, нужно?",
                        "Большее число, поделить на меньшее"),
                new Question("Как называется стандартная функция, которая помогает нам вывести результат " +
                        "нашего кода в консоль и посмотреть, что получилось",
                        "System.out.println ();")

        );

    }
    }




