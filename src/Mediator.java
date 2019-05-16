import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class Mediator extends Application {

    private String fileName = "C:\\Users\\Денис\\Downloads\\Mediator\\Mediator\\src\\TestFile"; // поменять путь к файлу

    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> correctAnswers = new ArrayList<>();

    private HashMap<String, String> logPass = new HashMap() {{
        put("Student", "Student");
        put("Teacher", "Teacher");
        put("Visitor", "Visitor");
    }};

    private Colleague colleague;

    ArrayList<String> getQuestions() {
        return questions;
    }

    ArrayList<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public String getFileName() {
        return fileName;
    }

    private void openAuthorizationWindow(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Log in");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        Scene scene = new Scene(grid, 300, 250);
        stage.setResizable(false);
        stage.setTitle("Authentication");
        stage.setScene(scene);
        stage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    actionTarget.setFill(Color.LIME);

                    String login = userTextField.getText();
                    String pass = pwBox.getText();

                    parseTestFile();

                    if (login.equals(logPass.get("Student")) && pass.equals(logPass.get("Student"))) {
                        colleague = new Student(getMediator());
                        ((Student) colleague).displayWindow();
                        stage.close();
                    } else if (login.equals(logPass.get("Visitor")) && pass.equals(logPass.get("Visitor"))) {
                        colleague = new Visitor(getMediator());
                        ((Visitor) colleague).displayWindow();
                        stage.close();
                    } else if (login.equals(logPass.get("Teacher")) && pass.equals(logPass.get("Teacher"))) {
                        colleague = new Teacher(getMediator());
                        ((Teacher) colleague).displayWindow();
                        stage.close();
                    } else {
                        actionTarget.setText("Your login or password is incorrect.");
                    }

            }
        });

    }

    private void parseTestFile() {
        try {
            int index = 0;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.ready()) {
                String s = reader.readLine();
                if (index == 0 || index % 2 == 0) {
                    if (!s.equals(""))
                        questions.add(s);
                } else {
                    if (!s.equals(""))
                        correctAnswers.add(s);
                }
                index++;
            }

            if (questions.size() > correctAnswers.size())
                questions.remove(questions.size() - 1);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    private Mediator getMediator() {
        return this;
    }

    @Override
    public void start(Stage stage) throws Exception {
        openAuthorizationWindow(stage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
