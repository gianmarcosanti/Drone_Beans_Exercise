/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dronesBean;

/**
 *
 * @author gianmarcosanti
 */
public class Coordinate{
    
    int x;
    int y;
    
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void sum(Coordinate coordinate){
        this.x += coordinate.getX();
        this.y += coordinate.getY();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}