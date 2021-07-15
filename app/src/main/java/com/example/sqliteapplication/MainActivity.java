package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,phone,Email;
    Button AddDB, SEED;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =findViewById(R.id.editTextTextPersonName);
        phone=findViewById(R.id.editTextPhone);
        Email=findViewById(R.id.editTextTextEmailAddress);

        AddDB =findViewById(R.id.button);
        SEED =findViewById(R.id.button2);
        DB=new DBHelper(this);

        AddDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt=name.getText().toString();
                String phoneTxt=phone.getText().toString();
                String emailTxt=Email.getText().toString();


                 Boolean checkpoints=DB.insertuserdata(nameTxt,phoneTxt,emailTxt);
                 if (checkpoints==true)
                     Toast.makeText(MainActivity.this,"New data inserted",Toast.LENGTH_SHORT).show();
                 else
                     Toast.makeText(MainActivity.this,"New data not inserted",Toast.LENGTH_SHORT).show();
            }
        });

        SEED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No entry exists ", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : "+res.getString(0)+"\n");
                    buffer.append("Phone : "+res.getString(1)+"\n");
                    buffer.append("Email : "+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setMessage(buffer.toString());
                builder.show();
            }



        });


    }
}