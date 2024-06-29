import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private Question[] questions;
    private int currentQuestionIndex;
    private int score;
    private Scanner scanner;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the quiz
    public void start() {
        askQuestion();
    }

    private void askQuestion() {
        if (currentQuestionIndex >= questions.length) {
            displayResults();
            scanner.close();
            return;
        }

        Question question = questions[currentQuestionIndex];
        System.out.println(question.getQuestionText());
        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                currentQuestionIndex++;
                askQuestion();
                cancel();
            }
        }, 15000);

        System.out.print("Choose an option: ");
        int userAnswer = scanner.nextInt();
        timer.cancel(); // Cancel the timer if user answers in time

        if (question.isCorrectAnswer(userAnswer - 1)) {
            score++;
        }
        currentQuestionIndex++;
        askQuestion();
    }

    // Method to display results
    private void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.length);
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ". " + questions[i].getQuestionText());
            System.out.println("Correct answer: " + (questions[i].getCorrectAnswer() + 1) + ". " + questions[i].getOptions()[questions[i].getCorrectAnswer()]);
            System.out.println("Your answer: " + (questions[i].getUserAnswer() + 1) + ". " + (questions[i].getUserAnswer() != -1 ? questions[i].getOptions()[questions[i].getUserAnswer()] : "No answer"));
            System.out.println();
        }
    }
}
// Question class 
class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;
    private int userAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.userAnswer = -1;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isCorrectAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
        return userAnswer == correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Question[] questions = new Question[] {
            new Question("What is the capital of France?", new String[] {"Paris", "London", "Rome", "Berlin"}, 0),
            new Question("What is 2 + 2?", new String[] {"3", "4", "5", "6"}, 1),
            new Question("What is the largest planet in our solar system?", new String[] {"Earth", "Mars", "Jupiter", "Saturn"}, 2),
            new Question("What is the boiling point of water?", new String[] {"90°C", "100°C", "110°C", "120°C"}, 1),
            new Question("Who wrote 'Hamlet'?", new String[] {"Charles Dickens", "Mark Twain", "William Shakespeare", "Leo Tolstoy"}, 2)
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
