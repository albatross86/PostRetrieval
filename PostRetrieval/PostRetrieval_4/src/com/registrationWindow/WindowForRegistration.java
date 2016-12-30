package com.registrationWindow;


import com.GeneralInformationData;
import com.SaveLoadSequrity.JsonClass;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sergei on 03.10.2016.
 */
public class WindowForRegistration extends Application {
    private GridPane grid = new GridPane();
    private final Button getKeybtn = new Button("Получить ключ");
    private final Button IhaveKeybtn = new Button("У меня есть ключ");
    private final Button checkKeybtn = new Button("Проверить ключ");
    private static Text actiontarget;
    private Text sceneTitle;
    private TextField keyTextField;
    private TextField emailTextField;
    private Label key;


    /********************************************************************************/
    @Override
    public void start(Stage primaryStage) throws Exception {
        // создаем объект для запроса.
        HttpRequest doPost = new HttpRequest();

        primaryStage.setTitle("Регистрация PostRetrieval");
        primaryStage.setScene(createScene());
        primaryStage.show();


        getKeybtn.setOnAction(e ->{

            if (emailTextField.getText().contains("@") && emailTextField.getText().contains(".")) {

                IhaveKeybtn.setVisible(false); // делаем невидимой кнопку "У меня есть ключ"
                getKeybtn.setVisible(false); // делаем невидимой кнопку "Получить ключ"

                checkKeybtn.setVisible(true); // делаем видимой кнопку "Проверить ключ"
                keyTextField.setVisible(true); // делаем видимыми поле для ввода ключа.

                actiontarget.setTextAlignment(TextAlignment.CENTER); // настройки выводимого текста.
                actiontarget.setFill(Color.BLACK);
                actiontarget.setText("Вам на почту выслано письмо с ключом! " +
                            "\n Скопируйте его в соотвествующее поле \n " +
                            "и нажмите 'Проверить ключ' \n " +

                            "Если в течении 5 минут \n" +
                            "Вы не получите ключ, проверьте \n" +
                            "папку 'Спам' ");

                String params = createParamsForRequest();   // строка с полученными параметрами для POST запроса.


                doPost.excutePost(params, GeneralInformationData.urlToGetKey ); // Запрос ключа

            }

        });

        IhaveKeybtn.setOnAction(e ->{

            if (emailTextField.getText().contains("@") && emailTextField.getText().contains(".")) {

                IhaveKeybtn.setVisible(false); // делаем невидимой кнопку "У меня есть ключ"
                getKeybtn.setVisible(false); // делаем невидимой кнопку "Получить ключ"

                checkKeybtn.setVisible(true); // делаем видимой кнопку "Проверить ключ"
                keyTextField.setVisible(true); // делаем видимыми поле для ввода ключа.

                actiontarget.setTextAlignment(TextAlignment.CENTER); // настройки выводимого текста.
                actiontarget.setFill(Color.BLACK);
                actiontarget.setText(" Введите ключ соотвествующее поле \n " +
                        "и нажмите кнопку 'Проверить ключ'");

            }

        });

        checkKeybtn.setOnAction(event -> {

            if (!keyTextField.getText().equals("")) {       // поле с ключом не должно быть пустым.
                String params = createParamsForRequest();   // строка с полученными параметрами для POST запроса.

                String answer = doPost.excutePost(params, GeneralInformationData.urlToCheckKey);

                                   actiontarget.setText("Сопоставление e-mail и key\n" +
                            "Ждите...");
                    if (answer.contains("true")){   // если ответ сервера содержит true.
                        actiontarget.setText("");
                        sceneTitle.setText("Поздравляем!\nВаша копия программы зарегестрирована!");
                        save_license_flag();//Сохраним главный флаг

                        checkKeybtn.setText("Закрыть");     // меняем текст на кнопке
                        checkKeybtn.setOnAction(e1 -> {
                            // Запускает главное окно программы
//                            Platform.runLater(() -> {
//                                try {
//                                    new Run().start(new Stage());
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                            primaryStage.close();   // по нажатии на нее - закрываем окно регистрации.
                            try {
                                Runtime.getRuntime().exec("java -jar "+ new File(".").getAbsolutePath() + "\\PostRetrieval_4.jar");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                        });
                    }
                    else {      // если сопоставление потерпело неудачу - віводим сообщение.
                        actiontarget.setText("Введен неправильный ключ\n" +
                                "или неправильно указан почтовый ящик.");
                        emailTextField.setEditable(true);   // и делаем поле с емэйлом изменяемым.
                    }
                }


        });

    }

    //Сохраняем раз и навсегда файл проверки лицензии
    private void save_license_flag() {

        JsonClass jsonClass = new JsonClass();
        jsonClass.setLicense_bool(true);
        jsonClass.createJson();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

    /********************************************************************************/
    // метод создает все элементы в главном окне и возвращаеи готовую сцену.
    private Scene createScene(){
        actiontarget = new Text();
        actiontarget.setTextAlignment(TextAlignment.CENTER);

        sceneTitle = new Text("Введите e-mail для регистрации\nБЕСПЛАТНО");
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(grid, 450, 350);

        Label userName = new Label("E-mail:");
        grid.add(userName, 0, 1);

        emailTextField = new TextField();
        emailTextField.setPrefWidth(250);
        emailTextField.setAlignment(Pos.CENTER);
        emailTextField.setPromptText("Enter your e-mail");
        grid.add(emailTextField, 1, 1);
        // создает поле для ввода ключа.
        createKeyField();

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(IhaveKeybtn);
        hbBtn.getChildren().add(getKeybtn);
        hbBtn.getChildren().add(checkKeybtn);

        checkKeybtn.setVisible(false); // Изначально эта кнопка невидима

        grid.add(hbBtn, 1, 4);

        grid.add(actiontarget, 1, 6);

        return scene;
    }


    /********************************************************************************/
    // метод создает поле для ввода ключа, который приходит на указанную почту.
    // сначала эелементы неактивны. После отправки email - активируются.
    // ипользуется в createScene().
    private void createKeyField(){
        key = new Label("Key:");
        grid.add(key, 0, 2);
        key.setVisible(false);

        keyTextField = new TextField();
        keyTextField.setAlignment(Pos.CENTER);
        keyTextField.setPromptText("Enter your key");
        keyTextField.setPrefWidth(250);
        keyTextField.setVisible(false);

        grid.add(keyTextField, 1, 2);
    }

    /********************************************************************************/
    // метод берет введнный в TextField ключ и email
    // и возвращает строку с параметрами для POST запроса.
    @Contract(pure = true)
    private String createParamsForRequest(){
        String parameters;
        String readKey = keyTextField.getText();
        String readEmail = emailTextField.getText();

        if (readKey.equals("")){
            parameters = "email=" + readEmail;
        }
        else{
            parameters = "email=" + readEmail + '&' +
                            "key=" + readKey;
        }
        return parameters;
    }

}
