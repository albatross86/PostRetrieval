package com;

import com.SaveLoadSequrity.DataLoader;
import com.VisualClasses.GeneralPane;
import com.VisualClasses.MainWindow;
import com.VisualClasses.ToolsWindow;
import com.VisualClasses.WorkingWindow;
import com.checkForUpdate.CheckForUpdates;
import com.trey.AppToTray;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

/**
 * Created by Alex on 22.10.2016.
 */
public class Run extends Application {

    public static Stage primaryStage;
    public static GridPane ROOT ;
    public static MainWindow mainWindow;
    public static WorkingWindow workingWindow;
    public static ToolsWindow toolsWindow;
    public static GeneralPane namePane;
    private static List<String> list;

    public static GeneralPane hintLeft;
    public static GeneralPane hintRight;

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println(getParameters());


        DataLoader dataLoader = new DataLoader();

        try {
            list = dataLoader.getReadyData();
        } catch (Exception e){
            e.getMessage();
        }

        CheckForUpdates checkForUpdates = new CheckForUpdates();
        checkForUpdates.checkUpdate();

            primaryStage.setX(getBoundsX());
            primaryStage.setY(getBoundsY());


            this.primaryStage = primaryStage;
            ROOT = new GridPane();
            mainWindow = new MainWindow(primaryStage);
            workingWindow = new WorkingWindow(list);
            toolsWindow = new ToolsWindow();
            namePane = new GeneralPane(45, 12, "namePane", "name");


            hintLeft = new GeneralPane(250, 40, "tooltip_window");
            hintLeft.setVisible(false);
            hintRight= new GeneralPane(250, 40, "tooltip_window");
            hintRight.setVisible(false);

            ROOT.getStylesheets().add("com/css/style.css");
            ROOT.getStyleClass().add("ROOT");

            ROOT.add(hintLeft, 1, 3 );
            ROOT.add(hintRight, 3, 3);

            ROOT.add(mainWindow, 2, 1);
            ROOT.add(namePane, 2, 2);
            ROOT.add(workingWindow, 2, 3);
            ROOT.add(toolsWindow, 3, 1);

            ROOT.setRowSpan(toolsWindow, 2); // Растягивает на два рядка

            Scene scene = new Scene(ROOT, Color.TRANSPARENT);

            primaryStage.getIcons().add(new Image("com/img/for_panel_icon.png"));
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);

            Platform.setImplicitExit (false); // не дает приложению полностью закрыться. Нужно, чтобы потом показать
                                            // галвное окно по нажатию на иконку в трее.
            primaryStage.show();

         new AppToTray();

   }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primStage) {
        primaryStage = primStage;
    }

    // Тактих себе 2 Китайских способа вызова проверки почты, пометки всех сообщений ПРОЧИТАНЫМИ
    public static void allRetrievalPull(String state){

        if (state.equals("start")) {
            workingWindow.startRetrieve();
        }else if (state.equals("stop")){
            workingWindow.stopRetrieve();
        }
    }

    public static void makeAllMessageAsReader(String string){

        workingWindow.readMessage(string);
    }

    public static List<String> getList(){
        return  list;
    }

    private double getBoundsX(){


        return Double.parseDouble(list.get(12));
    }

    private double getBoundsY(){

        return Double.parseDouble(list.get(13));
    }

}
