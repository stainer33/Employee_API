package com.e.employee_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.e.employee_api.API.EmployeeAPI;
import com.e.employee_api.Adapter.EmployeeAdapter;
import com.e.employee_api.Models.Employee;
import com.e.employee_api.URL.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

       // tvOutput=findViewById(R.id.tvOutput);
        recyclerView=findViewById(R.id.recyclerView);

       Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getAllEmployee();
        //Asynchronous call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ListActivity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee>employeeList=response.body();



                        EmployeeAdapter employeeAdapter = new EmployeeAdapter(ListActivity.this, employeeList);
                        recyclerView.setAdapter(employeeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));



            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg","onFailure : "+t.getLocalizedMessage());
                Toast.makeText(ListActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
