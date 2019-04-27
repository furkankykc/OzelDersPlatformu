/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author furkankykc
 */
class X {

    public Y y;

}

class Y {

    public X x;

    public int a() {
        return 12;
    }
}

public class path {
public static int f(int a, int b) {
        b--;
        a *= b;
        return a - b;
}
    public static void main(String[] args) {
     
    
        int x = 3;
        int y = 4;
        int z = f(x, y);
        System.out.println(x + y);
        x = 10;
        System.out.println(z);
    }
}

