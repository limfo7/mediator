import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


class Student extends Colleague {

    Student(Mediator mediator) {
        super(mediator);
    }

    public void displayWindow() {
        Stage mainStage = new Stage();

        VBox box = new VBox();

        List<String> questions = new ArrayList<>();
        questions = mediator.getQuestions();

        List<String> correctAnswers = mediator.getCorrectAnswers();

        for (int i = 0; i < questions.size(); i++) {
            TextField question = new TextField();
            question.setEditable(false);
            question.setText(questions.get(i));

            TextField answer = new TextField();
            answer.setEditable(true);

            box.getChildren().addAll(question, answer);
        }

        Button btn = new Button("Check your answers");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = 0;

                for (int i = 1; i < box.getChildren().size(); i += 2) {
                    if (!((TextField)box.getChildren().get(i)).getText().equals(correctAnswers.get(index))) {
                        ((TextField)box.getChildren().get(i)).setStyle("-fx-text-fill: red;");
                    }
                    else {
                        ((TextField)box.getChildren().get(i)).setStyle("-fx-text-fill: green;");
                    }
                    index++;
                }
            }
        });

        box.getChildren().add(btn);

        Scene scene = new Scene(box, 600, 400);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
