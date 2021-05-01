package fr.esme.mystic_bikes_app;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineResult;

import java.lang.ref.WeakReference;

class LocationCallback
        implements LocationEngineCallback<LocationEngineResult> {

    private final WeakReference<MapActivity> activityWeakReference;

    LocationCallback(MapActivity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
    }

    LocationCallback(WeakReference<MapActivity> activityWeakReference) {
        this.activityWeakReference = activityWeakReference;
    }

    /**
     * The LocationEngineCallback interface's method which fires when the device's location has changed.
     *
     * @param result the LocationEngineResult object which has the last known location within it.
     */
    @Override
    public void onSuccess(LocationEngineResult result) {
        MapActivity activity = activityWeakReference.get();

        if (activity != null) {
            Location location = result.getLastLocation();

            if (location == null) {
                return;
            }

// Create a Toast which displays the new location's coordinates
            Toast.makeText(activity, String.format(activity.getString(R.string.new_location),
                    String.valueOf(result.getLastLocation().getLatitude()), String.valueOf(result.getLastLocation().getLongitude())),
                    Toast.LENGTH_SHORT).show();

// Pass the new location to the Maps SDK's LocationComponent
            if (activity.getMapBoxMap() != null && result.getLastLocation() != null) {
                activity.getMapBoxMap().getLocationComponent().forceLocationUpdate(result.getLastLocation());
            }
        }
    }

    /**
     * The LocationEngineCallback interface's method which fires when the device's location can not be captured
     *
     * @param exception the exception message
     */
    @Override
    public void onFailure(@NonNull Exception exception) {
        Log.d("LocationChangeActivity", exception.getLocalizedMessage());
        MapActivity activity = activityWeakReference.get();
        if (activity != null) {
            Toast.makeText(activity, exception.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
