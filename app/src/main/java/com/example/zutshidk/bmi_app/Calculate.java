package com.example.zutshidk.bmi_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculate extends AppCompatActivity {
Button btnSave,btnShare;
TextView tvInfo,tvColor;
    DatabaseHandler myDB;
    SharedPreferences sp11;
//DatabaseHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
         myDB=new DatabaseHandler(this);
         sp11=this.getSharedPreferences("yoooo", Context.MODE_PRIVATE);


        btnSave=(Button)findViewById(R.id.btnSave);
        btnShare=(Button)findViewById(R.id.btnShare);
        tvInfo=(TextView)findViewById(R.id.tvInfo);
        tvColor=(TextView)findViewById(R.id.tvColor);
       //final String yoo="hiii";

        /*Intent i=getIntent();
       final String msg=i.getStringExtra("details");
       final String rr=i.getStringExtra("yooo");
       //float bmivall=i.getFloatExtra("bmival",0);
       double bmivall=i.getDoubleExtra("bmival",0);*/

        Intent i=getIntent();
        Bundle extra=i.getExtras();
        final String msg=extra.getString("details");
        final String rr=extra.getString("yooo");
        final String genshare=extra.getString("hmm");
        double bmivall=extra.getDouble("bmival");

       tvInfo.setText(msg);
        Toast.makeText(this, Double.toString(bmivall), Toast.LENGTH_SHORT).show();


        String text="Below 18.5 is Underweight\nBetween 18.5 to 25 is Normal\nBetween 25 to 30 is Overweight\nMore than 30 is Obese";
        SpannableString s1=new SpannableString(text);
        ForegroundColorSpan red=new ForegroundColorSpan(Color.RED);
        if(bmivall<18.5)
        {
            s1.setSpan(red,0,25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s1);
        }
        else if(bmivall<=25)
        {
            s1.setSpan(red,26,53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s1);

        }
        else if(bmivall<30){
            s1.setSpan(red,54,83, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s1);
        }
        else{
            s1.setSpan(red,84,104, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s1);
        }



       /*String text[]=new String[4];
       text[0]= new String();
       text[0]="Below 18.5 is Underweight\n";
        text[1]= new String();
        text[1]="Between 18.5 to 25 is Normal\n";
        text[2]= new String();
        text[2]="Between 25 to 30 is Overweight\n";
        text[3]= new String();
        text[3]="More than 30 is Obese\n";
        SpannableString s1=new SpannableString(text[0]);
        SpannableString s2=new SpannableString(text[1]);
        SpannableString s3=new SpannableString(text[2]);
        SpannableString s4=new SpannableString(text[3]);

        ForegroundColorSpan red=new ForegroundColorSpan(Color.RED);
        //Toast.makeText(this,Float.toString(bmivall) , Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Double.toString(bmivall), Toast.LENGTH_SHORT).show();
        ForegroundColorSpan green=new ForegroundColorSpan(Color.GREEN);
        if(bmivall<18.5)
        {
            s1.setSpan(red,0,text[0].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s1);
        }
        else
        {
            s1.setSpan(green,0,text[0].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s1);
        }
        if(bmivall<=25 && bmivall>=18.5)
        {
            s2.setSpan(red,0,text[1].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s2);

        }
        else
        {
            s2.setSpan(green,0,text[1].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s2);
        }
        if(bmivall<30 && bmivall>25){
            s3.setSpan(red,0,text[2].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s3);
        }
        else
        {
            s3.setSpan(green,0,text[2].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s3);
        }
        if(bmivall>=30){
            s4.setSpan(red,0,text[3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s4);
        }
        else
        {
            s4.setSpan(green,0,text[3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvColor.setText(s4);
        }*/








       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //String mssg=sp11.getString("results","");
             //  Bundle b=getIntent().getExtras();
               //String d1=b.getString("data");

               boolean isinserted=myDB.isinsert(rr);
               if(isinserted==true)
                   Toast.makeText(Calculate.this, "saved!", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(Calculate.this, "not inserted", Toast.LENGTH_SHORT).show();


           }
       });

       /*Intent k=getIntent();
       final String genshare=k.getStringExtra("hmm");*/

       btnShare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent share=new Intent(Intent.ACTION_SEND);
               share.setType("text/plain");
               String shr=genshare + " " + msg ;
               String shrsub="your sub here";
               share.putExtra(Intent.EXTRA_SUBJECT,shrsub);
               share.putExtra(Intent.EXTRA_TEXT,shr);
               startActivity(Intent.createChooser(share,"Select option"));
           }
       });




    }
}
