package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView tx = findViewById(R.id.fp_tv);

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpense();
            }
        });

    }

    private void openExpense() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}