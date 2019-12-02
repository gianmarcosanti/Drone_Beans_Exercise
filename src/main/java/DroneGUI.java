/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gianmarcosanti
 */


import com.mycompany.dronesBean.Drone;
import com.mycompany.dronesBean.Coordinate;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DroneGUI extends JLabel implements PropertyChangeListener {
    
    private final Drone drone;
    private JPanel containingPanel;

    public DroneGUI(Drone d) {
        super();
        this.drone = d;
        this.setForeground(randomColor());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                landDrone();
            }
        });
    }

    public DroneGUI(Drone d, JPanel containingPanel) {
        super();
        this.drone = d;
        this.containingPanel = containingPanel;
        this.setForeground(randomColor());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                landDrone();
            }
        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        
        System.out.print("Property changed: " + propertyName);
        if (null != propertyName) 
            switch (propertyName) {
            case "location":
                locationChanged(evt);
                break;
            case "flying":
                flyingChanged(evt);
                break;
        }
    }

    private void flyingChanged(PropertyChangeEvent evt) {
        if (!(boolean) evt.getNewValue()) {
            this.setText("<"
                    + drone.getLocProperty().getX()
                    + ","
                    + drone.getLocProperty().getY()
                    + ">");
        }
    }

    private void locationChanged(PropertyChangeEvent evt) {
        if (droneIsInPanelBounds()) {
            this.setBounds(drone.getLocProperty().getX(), drone.getLocProperty().getY(), 100, 20);
        }
        this.setText(">"
                    + drone.getLocProperty().getX()
                    + ","
                    + drone.getLocProperty().getY()
                + "<");
    }
    
    public void landDrone() {
        drone.land();
    }

    private Color randomColor() {
        Random rGen = new Random();
        float r = rGen.nextFloat();
        float g = rGen.nextFloat();
        float b = rGen.nextFloat();
        return new Color(r, g, b);
    }

    private boolean droneIsInPanelBounds() {
        return containingPanel.contains(new Point(this.drone.getLocProperty().getX(), this.drone.getLocProperty().getY()));
    }
}