package com.VisualClasses;

/**
 * Created by Alex on 19.07.2016.
 */
public  class ClassForSynhronizationTumbler {
    private static boolean bool = false;

    public static void setBool(boolean b){
        bool = b;
    }

    public static boolean getBool(){
        return bool;
    }
}
