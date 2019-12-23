package com.e.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.employee_api.API.EmployeeAPI;
import com.e.employee_api.Models.Employee;
import com.e.employee_api.Models.EmployeeCUD;
import com.e.employee_api.URL.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDeleteActivity extends AppCompatActivity {
    EditText etSearch, etName, etAge, etSalary;
    Button btnSearch, btnUpdate, btnDelete;
    EmployeeAPI employeeAPI;Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        etAge =findViewById(R.id.etAge);
        etName =findViewById(R.id.etName);
        etSearch=findViewById(R.id.etSearch);
        etSalary=findViewById(R.id.etSalary);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnSearch=findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateInstance();

                Call<Employee> listCall= employeeAPI.getEmployeeByID(Integer.parseInt(etSearch.getText().toString()));
                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        etName.setText(response.body().getEmployee_name());
                        etAge.setText(Integer.toString(response.body().getEmployee_age()));
                        etSalary.setText(Float.toString(response.body().getEmployee_salary()));

                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(UpdateDeleteActivity.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateInstance();
                int id = Integer.parseInt(etSearch.getText().toString());
               String name= etName.getText().toString();
               int age= Integer.parseInt(etName.getText().toString());
               float salary= Float.parseFloat(etName.getText().toString());
                EmployeeCUD employee = new EmployeeCUD(name,salary,age);

                Call<Void>voidCall=employeeAPI.UpdateEmployee(id,employee);
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UpdateDeleteActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateDeleteActivity.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateInstance();
                int id = Integer.parseInt(etSearch.getText().toString());
                Call<Void>voidCall=employeeAPI.deleteEmployee(id);
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UpdateDeleteActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateDeleteActivity.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    private void CreateInstance()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeAPI =retrofit.create(EmployeeAPI.class);
    }
}
