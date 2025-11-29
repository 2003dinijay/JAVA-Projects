
package com.demo.persondetails;

public class Person {
    private String name;
    private int age;
    public void setData(String a,int b){
        name=a;
        age=b;
          
    }
    
    public void displayData(){
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
    }
    
    
    
}
