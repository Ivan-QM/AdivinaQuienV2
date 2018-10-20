/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import java.util.Random;

/**
 *
 * @author Iván
 */
public class RandomImage {
    public static int getRandom(int min,int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
    public static String getRandomImagePath() {
        String path="/images/image"+getRandom(1,24)+".jpeg";
        return path;
    }
}
