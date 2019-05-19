package com.example.zutshidk.bmi_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Welcome extends AppCompatActivity {
    TextView tvHeight,tvFeet,tvWeight,tvInch,tvWelcome;
    Spinner spnFeet,spnInch;
    Button btnCalc,btnHistory;
    EditText etWeight;
    DatabaseHandler myDB;
    SharedPreferences sp2,sp11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        myDB=new DatabaseHandler(this);
        android.support.v7.app.ActionBar ab= getSupportActionBar();
        if (getSupportActionBar()!=null)
        {ab.setTitle("Bmi_Data");}


        tvHeight=(TextView)findViewById(R.id.tvHeight);
        tvFeet=(TextView)findViewById(R.id.tvFeet);
        tvWeight=(TextView)findViewById(R.id.tvWeight);
        tvInch=(TextView)findViewById(R.id.tvInch);
        tvWelcome=(TextView)findViewById(R.id.tvWelcome);

        spnFeet=(Spinner)findViewById(R.id.spnFeet);
        spnInch=(Spinner)findViewById(R.id.spnInch);
        sp2=this.getSharedPreferences("name", Context.MODE_PRIVATE);
        sp11=this.getSharedPreferences("yooo",Context.MODE_PRIVATE);

        ArrayList<Float> feet=new ArrayList<Float>();
        for(float i=1;i<11;i++){
            feet.add(i);
        }
        ArrayAdapter<Float> adapter = new ArrayAdapter<Float>(this,android.R.layout.simple_spinner_item, feet);
        spnFeet.setAdapter(adapter);

        ArrayList<Float> inch=new ArrayList<Float>();
        for(float i=1;i<11;i++){
            inch.add(i);
        }
        ArrayAdapter<Float> adapter1=new ArrayAdapter<Float>(this,android.R.layout.simple_spinner_item,inch);
        spnInch.setAdapter(adapter1);


        btnCalc=(Button)findViewById(R.id.btnCalc);
        btnHistory=(Button)findViewById(R.id.btnHistory);

        etWeight=(EditText)findViewById(R.id.etWeight);

        /*Intent intent=getIntent();
        final String msg=intent.getStringExtra("msg");*/

        Intent k=getIntent();
        Bundle extra=k.getExtras();
        final String gs=extra.getString("hmm");
        String msg=extra.getString("msg");

        tvWelcome.setText(msg);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wwt=etWeight.getText().toString();
                if(wwt.length()==0){
                    etWeight.setError("please enter weight");
                    etWeight.requestFocus();return;
                }
                float htFeet=Float.parseFloat(spnFeet.getSelectedItem().toString());
                float htInch=Float.parseFloat(spnInch.getSelectedItem().toString());
                float Wt=Float.parseFloat(etWeight.getText().toString());
                float in=htFeet*12+htInch;
                double cm=2.540*in;
                double resHti=(cm)/(100);

                double resBmii=(Wt)/ (resHti*resHti);
                String s = String.format("%.2f", resBmii);
                double resBmi=Double.parseDouble(s);

                String sht=String.format("%.2f",resHti);
                double resHt=Double.parseDouble(sht);

                String mssg;
                if(resBmi<18.5){
                    mssg="Underweight";
                }
                else if(resBmi<=25){
                    mssg="Normal";
                }
                else if(resBmi<30){
                    mssg="Overweight";
                }
                else{
                    mssg="Obese";
                }

                String ResofBmi="Your Bmi is "+ resBmi+ " and you are "+ mssg;

                String viewhist="Your height is: "+resHt+ " , your weight is: "+Wt+ " and your bmi is "+resBmi;
                /*SharedPreferences.Editor editor=sp2.edit();
                editor.putString("resofbmi",ResofBmi);
                editor.apply();*/



                Intent i=new Intent(Welcome.this,Calculate.class);
                Bundle ex=new Bundle();
                ex.putString("details",ResofBmi);
                ex.putString("yooo",viewhist);
                ex.putString("hmm",gs);
                ex.putDouble("bmival",resBmi);
                i.putExtras(ex);
                startActivity(i);

            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Welcome.this,view_hist.class);
                startActivity(i);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome,menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.about){
            String abtmsg="This is about Page!!";
            Intent yo=new Intent(Welcome.this,Aboutpage.class);
            yo.putExtra("abtmsg",abtmsg);
            startActivity(yo);


        }
        if(item.getItemId()==R.id.Contact){
            try {
               // Intent c = new Intent(Intent.ACTION_CALL);
                //c.setData(Uri.parse("tel:" + "9082408504"));
                //startActivity(c);
                   //Intent hii=new Intent(Intent.ACTION_VIEW,Uri.parse("https//www.google.com"));
                    // startActivity(hii);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://findnerd.com/list/view/How-to-open-a-Url-using-Intent/4617/"));
                startActivity(intent);
            }catch (SecurityException e){
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
