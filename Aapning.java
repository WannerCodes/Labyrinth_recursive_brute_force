import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Aapning extends HvitRute{
    public Aapning(int x, int y, Labyrint lab, JLabel info, JLabel paths){
        super(x, y, lab, info, paths);
    }

    @Override
    public ArrayList<ArrayList<Tuppel>> gaa(){
        besoeket = true;
        ArrayList<Tuppel> ret1 = new ArrayList<Tuppel>();
        ret1.add(new Tuppel(hentRad(), hentKol()));
        ArrayList<ArrayList<Tuppel>> ret2 = new ArrayList<ArrayList<Tuppel>>();
        ret2.add(ret1);
        return ret2;
    }

    @Override
    public char tilTegn(){
        return 'a';
    }
}
