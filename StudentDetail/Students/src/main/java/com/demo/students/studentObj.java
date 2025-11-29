
package com.demo.students;


public class studentObj {
    int admNo;
    String firstName;
    static String uniName;
     
    //constructor
    public studentObj(int a,String f){
            
    admNo=a;
    firstName=f;}
    
    
    //methods
    public void display(){
        System.out.println("admissionNo:  " +admNo+ " Fist Name: "+firstName+" University Name: "+uniName);
    }
    static void changeUni(){
       uniName="NSBM GREEN UNIVERSITY";
    }
    
}
