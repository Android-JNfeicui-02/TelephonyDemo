package com.roy.gpsdemo;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    LocationManager  lm;
    LocationListener listener;

    TextView mTvlatitude, mTvlongitude, mTvaccuracy, mTvtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvlatitude = (TextView) findViewById(R.id.tv_latitude);
        mTvlongitude = (TextView) findViewById(R.id.tv_longitude);
        mTvaccuracy = (TextView) findViewById(R.id.tv_accuracy);
        mTvtime = (TextView) findViewById(R.id.tv_time);


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        List<String> allProviders = lm.getAllProviders();
        for (String allProvider : allProviders) {
            System.out.println(allProvider);
        }

        listener = new MyListener();

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestProvider = lm.getBestProvider(criteria, true);

        lm.requestLocationUpdates(bestProvider, 0, 5000, listener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(listener);
        listener = null;
    }

    class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged: ");

            double latitude = location.getLatitude();

            mTvlatitude.setText(String.valueOf(latitude));
            double longitude = location.getLongitude();
            mTvlongitude.setText(String.valueOf(longitude));

            long time = location.getTime();
            mTvtime.setText(String.valueOf(time));

            float accuracy = location.getAccuracy();
            mTvaccuracy.setText(String.valueOf(accuracy));


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "onStatusChanged: " + status);

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
