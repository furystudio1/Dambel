package com.android.fury.Dambel;

import android.app.Activity;


/**
 * Created by fury on 9/5/2016.
 */
public class App_food {
    ////////////////////////////////////////////////////////////////
    // INSTANTIATED ACTIVITY VARIABLES
    ////////////////////////////////////////////////////////////////

    public static Activity activity_food_1;

    ////////////////////////////////////////////////////////////////
    // CLOSE APP METHOD
    ////////////////////////////////////////////////////////////////

    public static void close() {
        try {

            if (App_food.activity_food_1 != null) {
                App_food.activity_food_1.finish();
            }

            if (App_food.activity_food_1 != null) {
                App_food.activity_food_1.finish();
            }

            if (App_food.activity_food_1 != null) {
                App_food.activity_food_1.finish();
            }

        } catch (Exception e) {
        }

    }
}
