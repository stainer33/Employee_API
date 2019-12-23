package com.e.employee_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etAge, etSalary;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName =findViewById(R.id.etName);
        etAge=findViewById(R.id.etAge);
        etSalary=findViewById(R.id.etSalary);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =etName.getText().toString();
                Integer age = Integer.valueOf(etAge.getText().toString());
                float salary = Float.parseFloat(etSalary.getText().toString());

                EmployeeCUD employee = new EmployeeCUD(name,salary,age);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
                Call<Void>voidCall=employeeAPI.registerEmployee(employee);


                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error: "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
}
