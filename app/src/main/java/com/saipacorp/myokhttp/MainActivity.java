package com.saipacorp.myokhttp;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    public int count= 0;
    public void onClickMyBtn(View v)
    {
        TextView TxtName = findViewById(R.id.txtName);
        TextView TxtId = findViewById(R.id.txtid);
        TextView MyTxt = findViewById(R.id.myTxt2);
        TextView MyTxtErr = findViewById(R.id.myTxtErr);
        MyTxt.setText("Clicked on Button"+ ++count);
        //--- ok http get sample
        GetExample example = new GetExample();
        String response = null;
        try {
            response = example.run("http://172.20.9.57:8080/api/users/1");
            //MyTxt.setText(response.toString());
            //--
            JSONObject Jobject = new JSONObject(response);
            User u = new User();
            u.id=Jobject.getInt("id");
            u.name=Jobject.getString("name");
            TxtId.setText(u.id+"");
            TxtName.setText(u.name);
            MyTxt.setText("Succeeded Connected");
            //--
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            MyTxtErr.setText("Error on Run"+ e.getMessage()+ ++count);
        }
        System.out.println(response);

    }

    public void onClickMyBtnP(View v)
    {
        TextView MyTxt = findViewById(R.id.myTxt2);
        MyTxt.setText("Clicked on Button"+ ++count);
        //--- ok http get sample
        PostExample exampleP = new PostExample();
        String json = exampleP.bowlingJson("Jesse", "Jake");

        String response = null;
        try {
            response = exampleP.post("http://www.roundsapp.com/post", json);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
            MyTxt.setText("Error on Run"+ ++count);
        }
        System.out.println(response);

    }



}
