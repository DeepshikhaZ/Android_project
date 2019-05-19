package com.example.zutshidk.bmi_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Personal_details extends AppCompatActivity {
EditText etText1,etText2,etText3;
Button btnRegister;
RadioGroup radioGender;
RadioButton radioMale,radioFemale;
SharedPreferences sp1;
DatabaseHandler myDB;
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        etText1=(EditText)findViewById(R.id.etText1);
        etText2=(EditText)findViewById(R.id.etText2);
        etText3=(EditText)findViewById(R.id.etText3);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        radioGender=(RadioGroup)findViewById(R.id.radioGender);
        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);
        tv1=(TextView)findViewById(R.id.tv1);
        myDB=new DatabaseHandler(this);
        sp1= this.getSharedPreferences("Sharedprefs", Context.MODE_PRIVATE);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etText1.getText().toString();
                if(name.length()==0)
                {
                    etText1.setError("Name cannot be empty");
                    etText1.requestFocus();return;
                }
                String a=etText2.getText().toString();
                if(a.length()==0){
                    etText2.setError("Age cannot be empty");
                    etText2.requestFocus();return;
                }
                String p=etText3.getText().toString();
                if(p.length()==0)
                {
                    etText3.setError("Add phone number");
                    etText3.requestFocus();return;
                }
                if(p.length()>10)
                {
                    etText3.setError("Invalid phone number");
                    etText3.requestFocus();return;
                }

                int age=Integer.parseInt(etText2.getText().toString());

              SharedPreferences.Editor editor=sp1.edit();
//                editor.putString("username",etText1.getText().toString());
                editor.putString("username",name);
//                editor.putInt("age",Integer.parseInt(etText2.getText().toString()));
                editor.putInt("age",age);
                editor.putBoolean("Male",radioMale.isChecked());
                editor.putBoolean("Female",radioFemale.isChecked());
                editor.apply();

                Toast.makeText(Personal_details.this,"data saved",Toast.LENGTH_SHORT).show();

//                String msg="Marks" + marks + "Name" + m + "Grade" + grade;
                String msg="Welcome " + name;

                /*Intent intent= new Intent(Personal_details.this,Welcome.class);
                intent.putExtra("msg",msg);
                startActivity(intent);*/

                String gs= "name = " +name + " Age: " +age + " Phone = " +p;
                Intent k=new Intent(Personal_details.this,Welcome.class);
                Bundle extra=new Bundle();
                extra.putString("hmm",gs);
                extra.putString("msg",msg);
                k.putExtras(extra);
                startActivity(k);

            }
        });



    }
}

