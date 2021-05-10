package fr.esme.mystic_bikes_app;

import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

public class Tools {
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
}
