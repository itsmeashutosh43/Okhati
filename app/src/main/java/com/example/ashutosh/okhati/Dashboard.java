package com.example.ashutosh.okhati;

import android.*;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Dashboard extends AppCompatActivity implements m.GetData, View.OnClickListener {

    public String Title;
    public String  Message;

    public Double longitude;
    public Double latitude;
    private String postId;


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button3:
                Intent intent= new Intent(this,MapsActivity.class);
                Location myLocation= mCurrentLocation;

                if (mCurrentLocation!=null)
                {
                    intent.putExtra("location",myLocation);
                    startActivity(intent);
                }
                break;
            // ...
            case R.id.button4:
                FragmentManager fm=getFragmentManager();
                Fragment frag=fm.findFragmentById(R.layout.form);
                if (frag!=null)
                {
                    fm.beginTransaction().remove(frag).commit();
                }

                m dialog=new m();
                dialog.show(fm,"form");
                break;

        }



    }

    @Override
    public void onFinishUserDialog(Bundle bundle) {

        Title=bundle.getString("title");
        Message=bundle.getString("message");
        longitude=Double.valueOf(String.valueOf(mCurrentLocation.getLongitude()));
        latitude=Double.valueOf(String.valueOf(mCurrentLocation.getLatitude()));


        Data data=new Data(Title,Message,longitude,latitude);

        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference newPostRef = myRef.push();
        postId = newPostRef.getKey();

        myRef.child("users").child(postId).setValue(data);


    }

    int REQ=1;
    private FusedLocationProviderClient mFusedLocationClient;
    Location mCurrentLocation;
    String currentUser;









    public void buildAlert()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);

        dialog.setTitle("Please enable GPS setting");
        dialog.setMessage("Go to setting");
        dialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);

        findViewById(R.id.button3).setOnClickListener(this);

        findViewById(R.id.button4).setOnClickListener(this);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int check= ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
                    mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location!=null)
                            {
                                Log.i("longitude",location.getLongitude()+"");
                                mCurrentLocation=location;
                            }

                            Log.i("ahha","lol");



                        }
                    });




                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }

            }

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        int check= ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (check== PackageManager.PERMISSION_GRANTED)
        {

            Log.i("ahha","lol");
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location!=null)
                    {
                        CharSequence aa=(CharSequence)(location.getLongitude()+"");

                        //Toast.makeText(Dashboard.this,aa,Toast.LENGTH_LONG).show();

                        mCurrentLocation=location;



                        Log.i("longitude",location.getLongitude()+"");
                    }

                    Log.i("ahha","lol");



                }
            });

        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ);
        }
    }

}
