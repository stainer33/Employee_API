package com.e.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.employee_api.API.EmployeeAPI;
import com.e.employee_api.Models.Employee;
import com.e.employee_api.URL.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
EditText etSearch;
Button btnSearch;
TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch=findViewById(R.id.etSearch);
        btnSearch=findViewById(R.id.BtnSearch);
        tvOutput=findViewById(R.id.tvOutput);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                                    .baseUrl(URL.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
                Call<Employee> listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etSearch.getText().toString()));

                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SearchActivity.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Employee employee = response.body();
                        String Content ="";
                        Content += "ID : "+ employee.getId()+"\n";
                        Content += "Name : "+ employee.getEmployee_name()+"\n";
                        Content += "Salary : "+ employee.getEmployee_salary()+"\n";
                        Content += "Age : "+ employee.getEmployee_age()+"\n";

                        tvOutput.setText(Content);
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {

                    }
                });
            }
        });
    }
}
