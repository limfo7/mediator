import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Teacher extends Colleague {

    Teacher(Mediator mediator) {
        super(mediator);
    }


    public void displayWindow() {
        Stage mainStage = new Stage();

        VBox box = new VBox();

        ArrayList<String> answers = new ArrayList<>();
        answers = mediator.getCorrectAnswers();

        List<String> questions = new ArrayList<>();
        questions = mediator.getQuestions();


        TextArea area = new TextArea();
        area.autosize();
        for (int i = 0; i < answers.size(); i++) {
            area.setText(area.getText() + questions.get(i) + "\n");
            area.setText(area.getText() + answers.get(i) + "\n");
        }

        box.getChildren().add(area);

        Button btn = new Button("Save all changes");
        box.getChildren().add(btn);

        Scene scene = new Scene(box, 600, 400);
        mainStage.setScene(scene);
        mainStage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String fileName = mediator.getFileName();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    String text = area.getText();
                    writer.write(text);
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Something has gone wrong...");
                }
            }
        });

    }
}
