package com.trey;

import com.Run;
import com.sound.NotificationSound;
import javafx.application.Platform;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import static com.Run.primaryStage;

/**
 * Created by Sergei on 20.07.2016.
 */
public class AppToTray {

    private static final String ICON_IMAGE = "/com/img/tray_icon.png";
    // экземпляр класса для показа информационного сообщения в трее.
    private NotificationSound showInfoMessage = new NotificationSound();

    public AppToTray(){
        addAppToTray();
        // показывает информационное сообщение в трее при запуске.
        showInfoMessage.showInfoMessage("Post Retrieval запущена!");
    }

    /********************************************************************************/
    //Устанавливаем значок в трее для приложения.
    private void addAppToTray() {
        try {
            // ensure awt toolkit is initialized.
            Toolkit.getDefaultToolkit();

            // требование поддержки системного трея. Если не поддерживается - выход из приложения.
            if (!SystemTray.isSupported()) {
                System.out.println("No system tray support, application exiting.");
                Platform.exit();
            }

            // установка системной иконки.
            java.awt.SystemTray tray = SystemTray.getSystemTray();
            URL imageLoc = Run.class.getResource(ICON_IMAGE);

            java.awt.Image  image = Toolkit.getDefaultToolkit().getImage(imageLoc);
            NotificationSound.trayIcon = new TrayIcon(image);

            // двойной клик выводит окно поверх остальных окон.
//            NotificationSound.trayIcon.addActionListener(event -> Platform.runLater(this::showStage));


            // if the user selects the default menu item (which includes the app name),
            // show the main app stage.
//            MenuItem openItem = new MenuItem("Show main window");
//            openItem.addActionListener(event -> Platform.runLater(this::showStage));
//
//            // the convention for tray icons seems to be to set the default icon for opening
//            // the application stage in a bold font.
//            Font defaultFont = Font.decode(null);
//            Font boldFont = defaultFont.deriveFont(Font.BOLD);
//            openItem.setFont(boldFont);

            // чтобы действительно выйти из приложения,перейдите на иконку в системном трее
            // и выберите опцию "Exit", это завершит работу JavaFX и удалит иконку в трее
//            MenuItem exitItem = new MenuItem("Exit");
//            exitItem.addActionListener(event -> {
//                Platform.exit();
//                tray.remove(NotificationSound.trayIcon);
//            });
//
//            // настройка контекстного меню приложения в трее.
//            final PopupMenu popup = new PopupMenu();
//            popup.add(openItem);
//            popup.addSeparator();
//            popup.add(exitItem);
//            NotificationSound.trayIcon.setPopupMenu(popup);

            // добавляем иконку приложения в систкмный трей.
            tray.add(NotificationSound.trayIcon);
            // прячем гдавное окно по нажатию на иконку в трее.
            NotificationSound.trayIcon.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            if (primaryStage.isShowing())
                                primaryStage.hide();
                            else
                                primaryStage.show();
                        }
                    });
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    /* TODO nothing */
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    /* TODO nothing */
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    /* TODO nothing */
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    /* TODO nothing */
                }
            });
        } catch (AWTException e) {
            System.out.println("Unable to init system tray");
            e.printStackTrace();
        }
    }

    /********************************************************************************/
    //Выставляеь окно приложения поверх остальных окон.
    private void showStage() {

        if (Run.getPrimaryStage() != null) {
            Run.getPrimaryStage().show();
            Run.getPrimaryStage().toFront();
        }
    }
}