package com.settingPart;

import com.inputpart.InputMain;
import javafx.stage.Stage;

public class SettingController {



    public void inputData(){

        SettingMain.settingStage.close();

        InputMain inputMain =  new InputMain();
        try {
            inputMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void aboutProgramm(){

        AboutMain aboutMain = new AboutMain();
        try {
            aboutMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void aboutUs(){

        AboutUsMain aboutUsMain = new AboutUsMain();
        try {
            aboutUsMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
