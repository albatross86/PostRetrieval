package com.VisualClasses;

import javafx.scene.layout.VBox;

/**
 * Created by Alex on 25.10.2016.
 */
public class ToolsWindow extends VBox {

    public ToolsWindow(){

        getStylesheets().add("com/css/style.css");
        getStyleClass().add("tools_window");

        GeneralPane closePane = new GeneralPane(25,25, "closePane", "close");

        GeneralPane playPause = new GeneralPane("play_state", "pause_state", 25, 25);

        getChildren().add(closePane);
        getChildren().add(playPause);

    }
}
