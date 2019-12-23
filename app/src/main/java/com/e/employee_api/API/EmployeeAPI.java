package com.e.employee_api.API;

import com.e.employee_api.Models.Employee;
import com.e.employee_api.Models.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {
    //get all the employess
@GET("employees")
    Call<List<Employee>> getAllEmployee();

//get employee on bases of ID
    @GET("employee/{id}")
    Call<Employee>getEmployeeByID(@Path("id")int id);

    //insert/register data
    @POST("create")
    Call<Void>registerEmployee(@Body EmployeeCUD emp);

    //delete on basis of ID
    @DELETE("delete/{id}")
    Call<Void>deleteEmployee(@Path("id") int id);

    //update on basis of ID
    @PUT("update/{id}")
    Call<Void>UpdateEmployee(@Path("id") int id,@Body EmployeeCUD emp);


}
