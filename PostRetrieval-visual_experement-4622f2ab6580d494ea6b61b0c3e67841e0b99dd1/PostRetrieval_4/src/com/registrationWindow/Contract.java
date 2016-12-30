package com.registrationWindow;

/**
 * Created by Алексей on 18.11.2016.
 */
public @interface Contract {


    String value() default "";

    boolean pure() default false;
}
