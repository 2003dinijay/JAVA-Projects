/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.demo.mavenproject2;

import java.util.Scanner;

/**
 *
 * @author dinithinanayakkara
 */
public class Mavenproject2 {

    public static void main(String[] args) {
 
    
        int no;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your number: ");
        no=sc.nextInt();
        if (no>0)
            System.out.println(no+" is positive.");
        else
             System.out.println(no+" is negative.");
                
    }
    
    
}
    

