package com.e.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnListActivity, btnRegisterActivity, btnSearchActivity,btnUpdateDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListActivity=findViewById(R.id.btnListActivity);
        btnRegisterActivity=findViewById(R.id.btnRegisterActivity);
        btnSearchActivity=findViewById(R.id.btnSearchActivity);
        btnUpdateDelete=findViewById(R.id.btnUpdateDelete);

        btnListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btnRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnSearchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, UpdateDeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
