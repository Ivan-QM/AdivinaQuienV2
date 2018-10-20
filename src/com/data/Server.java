/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import static com.gui.Main.*;

/**
 *
 * @author Skynet
 */
public final class Server {

    Personaje[] btn = new Personaje[24];
    Personaje[] atn = new Personaje[24];
    int marked;
    public Server() {
        marked = 0;
        iniciar();
    }

    public void iniciar() {
        for (int b = 0; b < btn.length; b++) {
            btn[b] = new Personaje(true, "/images/image"+(b+1)+".jpeg");
            tab.add(btn[b]);
        }
    }
    
}
