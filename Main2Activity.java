package com.rakesh.arfirstaid;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    ListView numbersList;

    LocationManager locationManager;

    LocationListener locationListener;

    ArrayList<String> arrayList;

    String address = "";

    ArrayAdapter<String> arrayAdapter;

    EditText addMobileNo;

    TextView textView;

    Button addMobileButton;

    SharedPreferences sharedPreferences;

    Location location;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }

        }

    }

    public void setMobileNo(View view) {

        String mobile = addMobileNo.getText().toString();

        arrayList.add(mobile);

        try {

            sharedPreferences.edit().putString("mobiles", ObjectSerializer.serialize(arrayList)).apply();

        } catch (Exception e) {

            e.printStackTrace();
        }

        arrayAdapter.notifyDataSetChanged();

        addMobileNo.setVisibility(View.INVISIBLE);

        addMobileButton.setVisibility(View.INVISIBLE);

        numbersList.setVisibility(View.VISIBLE);

        addMobileNo.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        numbersList = findViewById(R.id.mobileList);

        textView = findViewById(R.id.textView);

        addMobileNo = findViewById(R.id.addMobile);

        addMobileButton = findViewById(R.id.addButton);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.rakesh.arfirstaid", MODE_PRIVATE);

        try {

            arrayList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("mobiles", ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (Exception e) {

            e.printStackTrace();
        }

        if (arrayList == null) {

            arrayList = new ArrayList<>();

            arrayList.add("Add a mobile number...");

        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        numbersList.setAdapter(arrayAdapter);

        numbersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    addMobileNo.setVisibility(View.VISIBLE);

                    numbersList.setVisibility(View.INVISIBLE);

                    addMobileButton.setVisibility(View.VISIBLE);

                }

            }
        });

        numbersList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if (position != 0) {

                    new AlertDialog.Builder(Main2Activity.this)
                            .setIcon(android.R.drawable.ic_delete)
                            .setTitle("Are you sure??")
                            .setMessage("Do you really want to delete?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    arrayList.remove(position);

                                    arrayAdapter.notifyDataSetChanged();

                                    try {

                                        sharedPreferences.edit().putString("mobiles", ObjectSerializer.serialize(arrayList)).apply();

                                    } catch (Exception e) {

                                        e.printStackTrace();
                                    }

                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();

                }
                return true;
            }
        });
    }

    public void helpMe(View view) {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Main2Activity.this.location = location;

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.emergency_alert);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,maxVol,0);

        mediaPlayer.start();

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},2);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }

        String numbers = "";

        try {
            for (int i = 1 ; i < arrayList.size();i++) {

                numbers+=(arrayList.get(i)+ ";");
            }
        } catch (Exception e) {

            e.printStackTrace();

        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + numbers));
        intent.putExtra("sms_body", "Help Me!!!! I am at " +location.getLatitude() + " : " +  location.getLongitude() + "\n" + "https://www.google.co.in/maps/@" + location.getLatitude() +  "," + location.getLongitude() +",15z" );
        startActivity(intent);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        }
    }
}

