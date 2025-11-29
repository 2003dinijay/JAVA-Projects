
package com.demo.empdetails;

public class EmpDetails {

    public static void main(String[] args) {
      Employee emp1=new Employee();
      emp1.setName("Dinithi");
      emp1.setEmpId(123);
      emp1.setSalary(260000.00f);
      
        System.out.println(emp1.getName() + " " + emp1.getEmpId()+" "+emp1.getSalary());
        
      
    }
}
