package com.VisualClasses;

import com.GeneralInformationData;
import com.Run;
import com.SaveLoadSequrity.JsonClass;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Alex on 22.10.2016.
 * Описывает главное окно программы
 */
public class MainWindow extends Pane {


    private  int prefWidth = 45;
    private  int prefHeight = 40;

    private double xOffset;
    private double yOffset;

    private double xStartPosition;
    private double yStartPosition;

    public MainWindow(Stage primaryStage) {

        setPrefSize(prefWidth, prefHeight);
        getStylesheets().add("com/css/style.css");
        getStyleClass().add("main_window");

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();

                xStartPosition = event.getScreenX();
                yStartPosition = event.getScreenY();
            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                double coordinatesX = event.getScreenX() - xOffset;
                double coordinatesY = event.getScreenY() - yOffset;

                primaryStage.setX(coordinatesX);
                primaryStage.setY(coordinatesY);

                writingFile(coordinatesX, coordinatesY);

            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                double coordinatesX = event.getScreenX();
                double coordinatesY = event.getScreenY();

                // Если перемещения не было - показываем WorkingWindow и ToolsWindow
                if (!determineMove((coordinatesX - xStartPosition), (coordinatesY - yStartPosition))) {
                    boolean bool = determineFlag();
                    Run.workingWindow.setVisible(bool); // Определяет в isVisible для workingWindow и возвращает противоположное значение
                    Run.toolsWindow.setVisible(bool);
//                  Run.hint.setVisible(bool);
                }
            }
        });
    }

    //Поверяет было ли перемещения главного окна.
    private boolean determineMove(double x, double y){

        if(x==0 && y == 0) return false; // Если перемещения не было
        else return true; //Если перемещение было
    }

    // Определяет в isVisible для workingWindow и возвращает противопо
    private boolean determineFlag(){

        boolean bool = Run.workingWindow.isVisible();
        if(bool == true) bool =false;
        else if(bool == false) bool = true;
        return bool;
    }

    private void writingFile(double coordinatesX, double coordinatesY){

        JsonClass jsonClass = new JsonClass();

        jsonClass.setCoordinatesX(coordinatesX);
        jsonClass.setCoordinatesY(coordinatesY);
        jsonClass.createJson();

        // Для принятия решения какую подсказку показывать - Л или П.
        GeneralInformationData.currentXPosition = coordinatesX;
    }

}