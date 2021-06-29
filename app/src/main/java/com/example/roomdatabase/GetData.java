
package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.roomdatabase.Adapter.UserAdapter;
import com.example.roomdatabase.EntityClass.UserModel;

import java.util.ArrayList;
import java.util.List;

public class GetData extends AppCompatActivity {


    RecyclerView recyclerview;
    TextView back;

    private List<UserModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        recyclerview = findViewById(R.id.recyclerview);
        back = findViewById(R.id.backtxt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });

    }

    private void backActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();

    }

    private void getData() {
        list = new ArrayList<>();
        list = DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
        recyclerview.setAdapter(new UserAdapter(getApplicationContext(), list, new UserAdapter.DeleteItemClicklistner() {
            @Override
            public void onItemDelete(int position, int id) {
                DatabaseClass.getDatabase(getApplicationContext()).getDao().deleteData(id);
                getData();
            }
        }));
    }
}