import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class HvitRute extends Rute{
    ArrayList<ArrayList<Tuppel>> utgangCache;
    public HvitRute(int x, int y, Labyrint lab, JLabel info, JLabel paths){
        super(x, y, lab, info, paths);
        initGUI();
    }

    @Override
    public ArrayList<ArrayList<Tuppel>> gaa(){
        if(besoeket){
            return null;
        }
        besoeket = true;
        ArrayList<ArrayList<Tuppel>> ret = new ArrayList<ArrayList<Tuppel>>();
        for(Rute nabo: naboer){
            if(nabo != null){
                ArrayList<ArrayList<Tuppel>> veier = nabo.gaa();
                if(veier != null){
                    for(ArrayList<Tuppel> vei: veier){
                        vei.add(0, new Tuppel(hentRad(), hentKol()));
                        ret.add(vei);
                    }
                }
            }
        }

        besoeket = false;
        return ret;
    }

    public char tilTegn(){
        return '.';
    }

    @Override
    public void initGUI(){
        super.initGUI();
        setBackground(Color.WHITE);
    }

    @Override
    public void path(){
        setBackground(Color.BLUE);
    }

}
