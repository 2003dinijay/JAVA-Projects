
package com.demo.persondetails;


public class Student  extends Person {
    private String studentId;
    public  void setInfo(String x){
        studentId=x;
    }
    
    public void displayInfo(){
        System.out.println("Student Id: "+studentId);
    }
    
}
   




