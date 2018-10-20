/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import java.awt.Image;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 *
 * @author Skynet
 */
public class Personaje extends JToggleButton {

    private String path;
    private boolean marked;

    public Personaje(boolean value, String path) {
        this.marked = marked;
        this.path = path;
        this.Init();
    }

    public void Init() {
        setBackground(null);
        setBorder(null);
        setForeground(null);
        setIcon(getImg());
        addActionListener(listener());
        setSelected(true);
    }

    private ImageIcon getImg() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (Exception e) {
        }
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }
    private ImageIcon marcadoIcon() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/images/marked.png"));
        } catch (Exception e) {
        }
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }
    private ActionListener listener() {
        ActionListener i = ((e) -> {
            if (this.isSelected()) {
                marked = false;
                System.out.println("marked: "+marked);
                setIcon(getImg());
                
                
            } else {
                marked = true;
                System.out.println("marked: "+marked);
                setIcon(marcadoIcon());
            }
        });
        return i;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isMarked() {
        return marked;
    }

    public void seMarked(boolean value) {
        this.marked = value;
    }
    
}
