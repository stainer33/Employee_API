package com.e.employee_api.API;

import com.e.employee_api.Models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmployeeAPI {
    //get all the employess
@GET("employees")
    Call<List<Employee>> getAllEmployee();

//get employee on bases of ID
    @GET("employee/{id}")
    Call<Employee>getEmployeeByID(@Path("id")int id);
}
