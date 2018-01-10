package com.example.ashutosh.okhati;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sign_in_button:
                Intent signInIntent=mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,RC_SIGN_IN);
                Toast.makeText(this,"lol",Toast.LENGTH_LONG).show();
                break;
            // ...
        }


    }

    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        SignInButton signInButton=findViewById(R.id.sign_in_button);

        findViewById(R.id.sign_in_button).setOnClickListener(this);





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignIn(task);
        }
    }

    private void handleSignIn(Task<GoogleSignInAccount> completed){

        try{
            GoogleSignInAccount account=completed.getResult(ApiException.class);
            //Successfully signed in
            updateUI(account);


        }catch (ApiException e){

            Log.i("Sign In:","failed");
            updateUI(null);
        }
    }

    @Override
    protected void onStart() {
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);


        super.onStart();
    }



    private void updateUI(GoogleSignInAccount currentUser){


        if (currentUser!=null){

            String name= currentUser.getDisplayName();
            String email=currentUser.getEmail();


            Intent intent=new Intent(this,Dashboard.class);
            Bundle bundle= new Bundle();
            bundle.putString("name",name);
            bundle.putString("email",email);

            intent.putExtras(bundle);
            startActivity(intent);

        }

        else {

        }





    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Key",Context.MODE_PRIVATE);
        int value=sharedPreferences.getInt("success",2);

        Toast.makeText(this,value+"",Toast.LENGTH_LONG).show();

        if (value==1)
        {
            Intent intent= new Intent(this, Dashboard.class);
            startActivity(intent);
        }
    }

    public void Clicked(View view){

        Intent intent=new Intent(this,Main2Activity.class);

        startActivity(intent);

    }
}
