package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.EntityClass.UserModel;

public class MainActivity extends AppCompatActivity {

    EditText name, phoneno, address;
    Button save, getData, save2;
    public static double b=0;
    TextView dispamt;
    public static final String  SHARED_PREF = "shared";
    public static final String TEXT = "text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phoneno = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        save = findViewById(R.id.btn_save);
        getData = findViewById(R.id.btn_getData);
        save2 = findViewById(R.id.btn_save2);
        dispamt = findViewById(R.id.displayamount);

        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = Integer.parseInt(name.getText().toString());
                b =  b + amount;
                dispamt.setText(""+ String.format("%.2f", b ));

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEXT, dispamt.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity.this,"Amount Credited",Toast.LENGTH_SHORT).show();
                saveData();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amount2 = Integer.parseInt(name.getText().toString());
                b = b - amount2;
                dispamt.setText(""+ String.format("%.2f", b ));

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEXT, dispamt.getText().toString());
                editor.apply();

                Toast.makeText(MainActivity.this,"Amount Debited",Toast.LENGTH_SHORT).show();
                saveData();
            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GetData.class));
            }
        });

        update();

    }

    private void update() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String text = sharedPreferences.getString(TEXT, "");
        dispamt.setText(text);
    }

    private void saveData() {
        String name_txt = name.getText().toString().trim();
        String phone_txt = phoneno.getText().toString().trim();
        String address_txt = address.getText().toString().trim();


        UserModel model = new UserModel();
        model.setName(name_txt);
        model.setAddress(address_txt);
        model.setPhoneno(phone_txt);
        DatabaseClass.getDatabase(getApplicationContext()).getDao().insertAllData(model);

        name.setText("");
        phoneno.setText("");
        address.setText("");
        Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();


    }


}