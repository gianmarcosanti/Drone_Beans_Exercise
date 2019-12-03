/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dronesBean;

import java.beans.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gianmarcosanti
 */
public class Drone implements Serializable {
    
    private Coordinate LOC;
    
    private Boolean FLYING;
    
    Timer timer = new Timer();

    
    private PropertyChangeSupport propertySupport;
    
    public Drone() {
        propertySupport = new PropertyChangeSupport(this); 
    }

    public Coordinate getLocProperty() {
        return LOC;
    }

    public void setLocProperty(Coordinate coordinate) {
        propertySupport.firePropertyChange("location", this.getLocProperty(), coordinate);
        LOC.sum(coordinate);
    }
    
    public void setFlyingProperty(Boolean prop){
        propertySupport.firePropertyChange("flying", getFlyingProperty(), prop);
        FLYING = prop;
        //System.out.print(getFlyingProperty().getX() + " " + getFlyingProperty().getY());
    }
    
    public Coordinate getFlyingProperty() {
        return LOC;
    }
    
    public void takeOff(Coordinate initLoc){
        this.FLYING = true;
        LOC = initLoc;
        timer.schedule(new SetLocationTimerTask(this), 100, 100);
        
    }
    
    public void land() {
        this.setFlyingProperty(false);
        timer.cancel();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
       
}

class SetLocationTimerTask extends TimerTask{
    private final Drone d;
    SetLocationTimerTask(Drone drone) {
        super();
        d = drone;
    }

    @Override
    public void run() {
        Coordinate c = new Coordinate((int)(Math.random()*((20)+1))-10,
                (int)(Math.random()*((20)+1))-10);
        
        d.setLocProperty(c);
    }
}