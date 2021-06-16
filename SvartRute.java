import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class SvartRute extends Rute{
    public SvartRute(int x, int y, Labyrint lab, JLabel info, JLabel paths){
        super(x, y, lab, info, paths);
        besoeket = true;
        initGUI();
    }

    @Override
    public ArrayList<ArrayList<Tuppel>> gaa(){
       return null;
    }

    public char tilTegn(){
        return '#';
    }

    @Override
    public void initGUI(){
        super.initGUI();
        setBackground(Color.BLACK);
    }
}
