package com.example.ashutosh.okhati;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ashutosh on 1/10/2018.
 */

public class m extends DialogFragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();


        bundle.putString("title",title.getText().toString());
        bundle.putString("message",message.getText().toString());


        GetData activity=(GetData) getActivity();

        activity.onFinishUserDialog(bundle);
        this.dismiss();

    }

    private EditText title;
    private EditText message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.form,container);

        Button b=view.findViewById(R.id.button5);

        b.setOnClickListener(this);

        return  view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        title= view.findViewById(R.id.title);
        message=view.findViewById(R.id.message);


        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    public interface GetData {
        void onFinishUserDialog(Bundle bundle);
    }

    GetData getData;

}
