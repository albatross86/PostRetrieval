package com;

import java.awt.*;

/**
 * Created by Sergei on 22.11.2016.
 */
public class MouseDowntime {
    private static int x = 0;
    private static int y = 0;
    private static long totaltime = 0L;
    private static final long DELAY = 100L;
//      Пытаюсь сделать по javafx.
//    static void mouseMoved(MouseEvent mouseEvent){
//        double x = mouseEvent.getScreenX();
//        double y = mouseEvent.getScreenY();
//
//        while (true){
//            double newx = mouseEvent.getScreenX();
//            double newy = mouseEvent.getSceneY();
//            if ((y != newy) || (x != newx)) {
//                System.out.println(totaltime);
//                totaltime = 0;
//                x = newx;
//                y = newy;
//            } else {
//                totaltime +=DELAY;
//            }
//
//            try{
//                Thread.sleep(DELAY);
//            } catch (InterruptedException ignored){
//            }
//        }
//    }

    // Работает только на awt.
//    public static void main(String[] args) {
    public static void getMouseTime(){
        // Получаем текущие координаты
        x = MouseInfo.getPointerInfo().getLocation().x;
        y = MouseInfo.getPointerInfo().getLocation().y;
        //цикл
        while (true) {
            // получаем текущие координаты
            int newx = MouseInfo.getPointerInfo().getLocation().x;
            int newy = MouseInfo.getPointerInfo().getLocation().y;
            // если координаты отличаются от ранее записанных(предидущих)
            if ((y != newy || x != newx)) {
                //выводим время
                System.out.println(totaltime);
                // обнуляем время простоя
                totaltime = 0L;
                // запоминаем текщие координаты
                y = newy;
                x = newx;
            } else {
                // иначе увеличиваем время на наш интервал
                totaltime += DELAY;
            }
            // задержка для проверки(т.е. проверяем не изменились ли координаты DELAY мс)
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ignored) {
            }
        }

    }
}
