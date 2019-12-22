package com.e.employee_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.employee_api.Models.Employee;

import java.net.ContentHandler;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    Context context;
    List<Employee>employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //attaching layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.list_layout),parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        //attaching data one by one
        Employee employee = employees.get(position);
        holder.name.setText(employee.getEmployee_name());
        holder.age.setText(employee.getEmployee_age());
        holder.salary.setText((int) employee.getEmployee_salary());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, salary,age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            salary=itemView.findViewById(R.id.salary);
        }
    }
}
