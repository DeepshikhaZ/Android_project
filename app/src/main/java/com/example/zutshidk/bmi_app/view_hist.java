package com.example.zutshidk.bmi_app;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class view_hist extends AppCompatActivity {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hist);
        DatabaseHandler myDB=new DatabaseHandler(this);
        tv1=(TextView)findViewById(R.id.tv1);

        Cursor res = myDB.getAllData();

        if (res.getCount() == 0) {
            Toast.makeText(this, "No data ", Toast.LENGTH_SHORT).show();

        }
        // else{
        StringBuffer buffer=new StringBuffer();

        while (res.moveToNext()) {

            buffer.append("Results :"+res.getString(1)+"\n\n");
           // buffer.append("Marks: "+res.getString(2)+"\n");


            tv1.setText(buffer.toString());
        }


    }
}
