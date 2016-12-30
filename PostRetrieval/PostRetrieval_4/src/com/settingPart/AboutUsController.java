package com.settingPart;

import com.controller.Browser;
import javafx.scene.input.MouseEvent;


/**
 * Created by Алексей on 26.11.2016.
 */
public class AboutUsController {

    // Переход на наш сайт в браузере
    public void goToSpacelore (MouseEvent mouseEvent){

        Browser browser = new Browser();
        browser.openBrowser("http://spacelore.dp.ua");

    }

    public void exit(javafx.scene.input.MouseEvent mouseEvent) {

        AboutUsMain.aboutUsStage.close();
    }


}
