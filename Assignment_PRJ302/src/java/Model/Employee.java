/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;


import java.lang.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Phanh
 */
public class Employee {
    private int id;
    private String name;
    private Employee manager;
    private Department dept;
    private ArrayList<Employee> staffs = new ArrayList<>();
    private ArrayList<Employee> directstaffs = new ArrayList<>();

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public ArrayList<Employee> getStaffs() {
        return staffs;
    }

    public void setStaffs(ArrayList<Employee> staffs) {
        this.staffs = staffs;
    }

    public ArrayList<Employee> getDirectstaffs() {
        return directstaffs;
    }

    public void setDirectstaffs(ArrayList<Employee> directstaffs) {
        this.directstaffs = directstaffs;
    }
    
    
}
