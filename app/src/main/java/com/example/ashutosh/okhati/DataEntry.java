package com.example.ashutosh.okhati;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class DataEntry extends AppCompatActivity {

    String a="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);


        SharedPreferences sharedPreferences=this.getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("default",0);









    }

    public void getString(final VolleyCallBack callback){














        final String value = getIntent().getExtras().getString("1");
        final String value1 = getIntent().getExtras().getString("5");
        final String value2=getIntent().getExtras().getString("4");
        final String value3 = getIntent().getExtras().getString("6");
        final String value4 = getIntent().getExtras().getString("3");

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://okhati.000webhostapp.com/createUsr.php";

        StringRequest post = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Hello", response);
                        callback.onSuccess(response);



                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Error.Response", "Errorrrrr");

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<>();

                params.put("Name", value);

                params.put("Email", value1);

                params.put("Password", value2);

                params.put("CPassword", value3);

                params.put("Age", value4);
                return params;


            }
        };

        queue.add(post);


    }

    public boolean InternetAvailable()
    {
        try{
            InetAddress inetAddress=InetAddress.getByName("google.com");
            return !inetAddress.equals("");
        }catch (Exception e)
        {
            return false;
        }

    }



    @Override
    protected void onResume() {

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Key",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();


        super.onResume();



                getString(new VolleyCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        String a = result;


                        if (a.equals("HelloSuccess")) {
                            editor.putInt("success",1);
                            editor.commit();
                            Context context = getApplicationContext();
                            CharSequence text = "Signed Up!!";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent);
                            toast.show();
                        }

                        if (a.equals("Hellolol")) {
                            Context context = getApplicationContext();
                            CharSequence text = "Incorrect password";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                            startActivity(intent);
                            toast.show();
                        }

                        


                    }
                });











        }
             }


