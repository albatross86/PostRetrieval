package UpdateProject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Sergei on 16.06.2016.
 *
 */
public class UpdaterGUI extends Application {

    /********************************************************************************/
    private Parent createContent(){
        VBox root = new VBox(5);
        root.setPrefSize(400, 120);
        root.setAlignment(Pos.CENTER);

        Task<Void> task = new TaskClass();

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefSize(390,20);
        progressBar.progressProperty().bind(task.progressProperty());

        Button btnCancel = new Button("Cancel");
        Label label = new Label("Please, wait...");
        Label labelProgressText = new Label("");

        /********************************************************************************/
        // обработка события нажатия кнопки Cancel
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });

        root.getChildren().addAll(progressBar,labelProgressText, btnCancel, label);
        labelProgressText.textProperty().bind(task.messageProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        return root;
    }


    /********************************************************************************/
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Updater");
        Scene scene = new Scene(createContent());

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }


    /********************************************************************************/
    public static void main(String[] args){
        launch(args);
    }
}