package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.AnswerQ;
import model.Question;
import model.QuizQ;


public class QuizController implements Initializable {

    protected static int numberOfQuestions = 10;
    protected static ArrayList<Question> questionList = new ArrayList<>();
    protected static ArrayList<Question> questionsQ = new ArrayList<>();

    private static String[] choices = new String[10];
    private ToggleGroup TGroup;

    @FXML
    private JFXRadioButton optionA;

    @FXML
    private JFXRadioButton optionB;

    @FXML
    private JFXRadioButton optionC;

    @FXML
    private JFXRadioButton optionD;

    @FXML
    private JFXButton finish_button;

    @FXML
    private Text question;

    String[][] qQ;
    String[][] aQ;

    public static int qQid;

    HashMap<Integer, String> map;

    public static String choice;
    private static QuizController instance;

    public QuizController()
    {
        instance = this;
    }

    public static QuizController getInstance()
    {
        return instance;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        TGroup = new ToggleGroup();
        optionA.setToggleGroup(TGroup);
        optionB.setToggleGroup(TGroup);
        optionC.setToggleGroup(TGroup);
        optionD.setToggleGroup(TGroup);

        resetStatus();

        displayQuiz();

        map = new HashMap<Integer, String>();

        renderQuestion(qQid);
    }

    public void renderQuestion(int i){
        try {
            if (i >= 0 && i < questionsQ.size()) {
                question.setText("Question " + (i + 1) + ": " + questionsQ.get(i).getQuestion());
                optionA.setText(questionsQ.get(i).getOptionA());
                optionB.setText(questionsQ.get(i).getOptionB());
                optionC.setText(questionsQ.get(i).getOptionC());
                optionD.setText(questionsQ.get(i).getOptionD());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String checkB = selectedToggle(i);

        if (checkB != null) {
            if(String.valueOf(optionA.getText().charAt(0)).equals(checkB)) {
                optionA.setSelected(true);
            } else if (String.valueOf(optionB.getText().charAt(0)).equals(checkB)) {
                optionB.setSelected(true);
            } else if (String.valueOf(optionC.getText().charAt(0)).equals(checkB)) {
                optionC.setSelected(true);
            } else if (String.valueOf(optionD.getText().charAt(0)).equals(checkB)) {
                optionD.setSelected(true);
            }
        }
        else {
            optionA.setSelected(false);
            optionB.setSelected(false);
            optionC.setSelected(false);
            optionD.setSelected(false);
        }
    }

    public void displayQuiz() {
        getQuizData();
        chooseRandomQuestions();
    }

    public void setQid(int i)
    {
        qQid =  i;

    }

    public int getQid()
    {
        return qQid;
    }

    public String selectedToggle(int questionQ) {
        return map.get(questionQ);
    }

    public String getSelection()
    {
        return choice;
    }

    @FXML
    public void groupAction(ActionEvent event) {

        if(optionA.isSelected())
        {
            choice = String.valueOf(optionA.getText().charAt(0));
        }
        else if(optionB.isSelected())
        {
            choice = String.valueOf(optionB.getText().charAt(0));
        }
        else if(optionC.isSelected())
        {
            choice = String.valueOf(optionC.getText().charAt(0));
        }
        else if(optionD.isSelected())
        {
            choice = String.valueOf(optionD.getText().charAt(0));
        }

    }

    @FXML
    public void nextAction(ActionEvent e) throws IOException
    {
        if(qQid<10)
        {
            map.put(qQid,getSelection());
            if(Objects.equals(getSelection(), null))
            {
                TickBoxController.getInstance().setColorRed(qQid, true);
            }
            else
            {
                TickBoxController.getInstance().setColorGreen(qQid, true);
            }

            if(Objects.equals(qQid, 9))
            {
                renderQuestion(qQid);
                qQid++;
            }
            else
            {
                qQid++;
                choice = null;
                renderQuestion(qQid);
            }


        }

        else {

            this.setDialogBox();
        }

    }

    @FXML
    public void previousAction(ActionEvent e) throws IOException {
        if (qQid > 0) {
            map.put(qQid, getSelection()); // Save the selection for the current question

            qQid--; // Decrement after saving the selection


            renderQuestion(qQid); // Render the previous question
        }
    }

    public void setDialogBox() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Good job!");
        String s = "Do you want to finish your Quiz Test?";
        alert.setContentText(s);

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/image/i18.png")));
        icon.setFitHeight(40);
        icon.setFitWidth(40);
        alert.setGraphic(icon);

        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/StyleCSS/alert.css").toExternalForm()
        );

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            finishFeatures();
            System.gc();
        } else {
            qQid--;
        }
    }


    public int countCorrectAnswer() {
        int count = 0;

        for (int qid = 0; qid < map.size(); qid++) {
            String[] listA = questionsQ.get(qid).getAnswer().split(" ");
            if (map.get(qid).equals(listA[2])) {
                count++;
            }
        }
        return count;
    }


    @FXML
    public void finishQuiz(ActionEvent event) throws IOException {
        this.setDialogBox();
    }

    public void finishFeatures() {
        try {
            qQid = 0;
            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.2), MainController.getInstance().getAPane());
            slideOut.setToX(-MainController.getInstance().getAPane().getWidth());
            slideOut.setOnFinished(event -> {
                try {
                    Stage stage = (Stage) finish_button.getScene().getWindow();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXMLViews/PointView.fxml")));
                    stage.getScene().setRoot(root);
                    stage.show();

                    TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.2), MainController.getInstance().getAPane());
                    slideIn.setFromX(MainController.getInstance().getAPane().getWidth());
                    slideIn.setToX(0);
                    slideIn.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            slideOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getQuizData() {
        if (!questionList.isEmpty()) return;
        File file = new File("C:\\Java\\QuizGameDictionary\\src\\main\\resources\\data\\questions.txt");
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String question;
                if (fileScanner.hasNextLine()) {
                    question = fileScanner.nextLine();
                } else {
                    question = "";
                }
                String optionA;
                if (fileScanner.hasNextLine()) {
                    optionA = fileScanner.nextLine();
                } else {
                    optionA = "";
                }
                String optionB;
                if (fileScanner.hasNextLine()) {
                    optionB = fileScanner.nextLine();
                } else {
                    optionB = "";
                }
                String optionC;
                if (fileScanner.hasNextLine()) {
                    optionC = fileScanner.nextLine();
                } else {
                    optionC = "";
                }
                String optionD;
                if (fileScanner.hasNextLine()) {
                    optionD = fileScanner.nextLine();
                } else {
                    optionD = "";
                }
                String answer;
                if (fileScanner.hasNextLine()) {
                    answer = fileScanner.nextLine();
                } else {
                    answer = "";
                }
                questionList.add(new Question(question, optionA, optionB, optionC, optionD, answer));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void chooseRandomQuestions() {
        Random random = new Random();
        int curNumOfQues = 0;
        while (curNumOfQues != numberOfQuestions) {
            int randomNum = random.nextInt(0, questionList.size());
            if (questionList.get(randomNum) != null) {
                questionsQ.add(questionList.get(randomNum));
                questionList.remove(randomNum);
                curNumOfQues++;
            }
        }
    }

    public void resetStatus() {
        questionList.clear();
        questionsQ.clear();
        qQid = 0;
    }

}


