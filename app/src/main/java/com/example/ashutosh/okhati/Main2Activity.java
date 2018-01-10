package com.example.ashutosh.okhati;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    public String a = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }


    public void Click(View view)


    {

        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);

        Intent intent = new Intent(getApplicationContext(), DataEntry.class);

        Bundle bundle = new Bundle();
        bundle.putString("1", editText.getText().toString());
        bundle.putString("5", editText5.getText().toString());
        bundle.putString("4", editText4.getText().toString());
        bundle.putString("6", editText6.getText().toString());
        bundle.putString("3", editText3.getText().toString());



        intent.putExtras(bundle);

        startActivity(intent);


    }

}