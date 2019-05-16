import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

class Visitor extends Colleague {

    Visitor(Mediator mediator) {
        super(mediator);
    }

    public void displayWindow() {
        Stage mainStage = new Stage();

        VBox box = new VBox();

        List<String> questions = new ArrayList<>();
        questions = mediator.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            TextField question = new TextField();
            question.setEditable(false);
            question.setText(questions.get(i));

            TextField answer = new TextField();
            answer.setEditable(false);

            box.getChildren().addAll(question, answer);
        }
        Scene scene = new Scene(box, 600, 400);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
