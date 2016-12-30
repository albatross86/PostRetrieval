package com.VisualClasses;
import com.GeneralInformationData;
import com.Run;
import com.SaveLoadSequrity.DataLoader;
import com.controller.Controller;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Alex on 25.10.2016.
 * Используеться для отображения иконок программы.
 * В конструкторе принимает размер, имя в css файле и тд...
 */
public class GeneralPane extends Pane {



    //Используеться для создания иконки без всплывающей подсказки
    public GeneralPane(int height, int width, String styleClassName, String id) {

        Controller controller = new Controller();

        setId(id);
        setPrefSize(height, width);
        setMaxSize(height, width);
        getStylesheets().add("com/css/style.css");
        getStyleClass().add(styleClassName);

        setOnMouseClicked(e ->{

            System.out.println("Clicked");

            System.out.println(e.getSceneX());

            System.out.println(e.getSceneY());

        });

        setOnMouseEntered(e->{

                setStyle("-fx-background-color: rgb(57,116,185,.5)");
            //  setStyle("-fx-background-color: rgb(220,216,82,.5)");

        });

        setOnMouseExited(e ->{
            Run.hintLeft.setVisible(false);
            Run.hintRight.setVisible(false);
               setStyle("-fx-background-color: rgb(150,150,150,.0)");

        });



        setOnMouseClicked(e ->{

            Object button = e.getButton(); // определяем нажатую кнопку на мышке
            controller.mouseClickedEvent(button.toString(), getId()); // Передаём кнопку, и Имя компонента
        });

    }

    //Используеться для создания иконки со всплывающей подсказкой // String может быть любой строкой
    public GeneralPane(int height, int width, String styleClassName, String id, String hint) {

        Controller controller = new Controller();

        setId(id);
        setPrefSize(height, width);
        setMaxSize(height, width);
        getStylesheets().add("com/css/style.css");
        getStyleClass().add(styleClassName);

        setOnMouseClicked(e ->{

            System.out.println("Clicked");

            System.out.println(e.getSceneX());

            System.out.println(e.getSceneY());

        });

        setOnMouseEntered(e->{

            setStyle("-fx-background-color: rgb(57,116,185,.5)");
            //  setStyle("-fx-background-color: rgb(220,216,82,.5)");

        });

        setOnMouseExited(e ->{
            Run.hintLeft.setVisible(false);
            Run.hintRight.setVisible(false);
            setStyle("-fx-background-color: rgb(150,150,150,.0)");

        });



        setOnMouseClicked(e ->{

            Object button = e.getButton(); // определяем нажатую кнопку на мышке
            controller.mouseClickedEvent(button.toString(), getId()); // Передаём кнопку, и Имя компонента
        });

        // Метод определяет зависание мыши над иконкой
        setOnMouseMoved(e ->{
            int i = 0;
            int n = 0;

            double x = MouseInfo.getPointerInfo().getLocation().getX();
            double y = MouseInfo.getPointerInfo().getLocation().getY();

            Timer timer = new Timer();
            System.out.println("Start Timer"  + i);
            i++;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    if(x == MouseInfo.getPointerInfo().getLocation().getX() && y == MouseInfo.getPointerInfo().getLocation().getY()) {


                        openeHint();
                    }
                }
            }, 2000 );

        });

    }

    //Определяет левую или правую подсказку показывать.
    private void openeHint() {

        //Узнаем сторону Л или П

        switch (getSide()){
            case "hintRight" : Run.hintRight.setVisible(true); break;
            case "hintLeft" : Run.hintLeft.setVisible(true); break;
        }

    }

    //Конструктор без реакции на мышку
    public GeneralPane(int height, int width, String styleClassName) {

        setPrefSize(height, width);
        setMaxSize(height, width);
        getStylesheets().add("com/css/style.css");
        getStyleClass().add(styleClassName);


    }

    //Конструктор с поддержкой нажатия на елемент, отличаеться от первого конструктора только порядко елементов в самом конструкторе
    // Using for start-stop retrieve
    public GeneralPane(String firstStyle, String secondStyle, int height, int width ) {

        setPrefSize(height, width);
        setMaxSize(height, width);
        getStylesheets().add("com/css/style.css");
        getStyleClass().add(firstStyle);

        setOnMouseClicked(e ->{

            if (!(ClassForSynhronizationTumbler.getBool())) {

                getStyleClass().remove(firstStyle);
                getStyleClass().add(secondStyle);
                ClassForSynhronizationTumbler.setBool(true);
                Run.allRetrievalPull("start");
//              allRetrievalPull.startRetrieve();

            } else if (ClassForSynhronizationTumbler.getBool()) {

                getStyleClass().remove(secondStyle);
                getStyleClass().add(firstStyle);
                ClassForSynhronizationTumbler.setBool(false);
                Run.allRetrievalPull("stop");
//              allRetrievalPull.stopRetrieve();

            }

        });

        setOnMouseEntered(e->{
            setStyle("-fx-background-color: rgb(57,116,185,.5)");
            //  setStyle("-fx-background-color: rgb(220,216,82,.5)");
        });

        setOnMouseExited(e ->{
            setStyle("-fx-background-color: rgb(150,150,150,.0)");

        });

    }

    // Определяем какую подсказку показывать - Л или П
    private  String getSide(){

       Double x = GeneralInformationData.currentXPosition;

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        if ( x < (size.getWidth()/2)){
            return "hintRight";
        }else return "hintLeft";
    }


}