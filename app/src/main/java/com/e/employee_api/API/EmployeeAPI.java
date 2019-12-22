package com.e.employee_api.API;

import com.e.employee_api.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {
@GET("employees")
    Call<List<Employee>> getAllEmployee();
}
