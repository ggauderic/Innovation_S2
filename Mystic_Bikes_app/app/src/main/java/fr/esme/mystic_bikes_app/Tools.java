package fr.esme.mystic_bikes_app;

import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Tools {
   private static int currentTheme ;
   static String modeC;
   private Tools(){
       currentTheme = R.style.Theme_Mystic_Bikes_app_dark;
       modeC ="dark";
   }
    public static void waitPbar(long timeout, @Nullable ProgressBar pbar){
        Handler handler = new Handler();

// Create and start a new Thread
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(timeout);
                }
                catch (Exception e) { } // Just catch the InterruptedException

                // Now we use the Handler to post back to the main thread
               if(pbar != null) handler.post(new Runnable() {
                    public void run() {
                        // Set the View's visibility back on the main UI Thread
                        pbar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }
    public static void setTheme(int res){
        currentTheme = res;
    }

    public static int getTheme() {
        return currentTheme;
    }

    public static void setBackgroundColor(ArrayList<View> primV, ArrayList<View> secV,ArrayList<View> thirdV){
        primV.forEach(e -> e.setBackgroundResource(settingColor(1)));
        secV.forEach(e -> e.setBackgroundResource(settingColor(2)));
        thirdV.forEach(e -> e.setBackgroundResource(settingColor(3)));

    }
    public static void setTextColor(ArrayList<TextView> primV, ArrayList<TextView> secV, ArrayList<TextView> thirdV){
        primV.forEach(e -> e.setTextColor(settingColor(1)));
        secV.forEach(e -> e.setTextColor(settingColor(2)));
        thirdV.forEach(e -> e.setTextColor(settingColor(3)));
    }
    public static int settingColor( int nb){
        int id = R.color.colorPrimary;;
        if(modeC == "light") {
            switch (nb) {
                case 1:
                    id = R.color.colorPrimary;
                case 2:
                    id = R.color.colorSecondary;
                default:
                    id = R.color.colorTertiary;
            }
        } else {

            switch (nb) {
                case 1:
                    id = R.color.colorPrimaryDark;
                case 2:
                    id = R.color.colorSecondaryDark;
                default:
                    id = R.color.colorTertiaryDark;
            }
        }
        return id;



    }

public static String getMode(){
        return modeC;
}
    public static void setMode(String mode) {
        modeC = mode;
    }
}
