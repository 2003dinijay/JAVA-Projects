
package com.demo.rectangleareacalculator;

public class RectangleAreaCalculator {
    // Variable declaration with  data types
    int length;
    int width;
    
    //Method to calculate the area
    public int calculateArea() {
        return length * width;
        
    }
    public static void main(String[] args){
        RectangleAreaCalculator  R1=  new RectangleAreaCalculator();
        
        R1.length=10;
        R1.width=20;
        
        //call the calculateArea
        int area=R1.calculateArea();
        System.out.println("Area in m is: "+area);
        
        
    }
}
