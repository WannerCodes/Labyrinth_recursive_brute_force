import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

abstract class Rute extends JButton{
    int kol, rad;
    Labyrint lab;
    boolean besoeket = false;
    String utveiInfo;
    JLabel info;

    static ArrayList<ArrayList<Rute>> stier = new ArrayList<ArrayList<Rute>>(1);

    Rute nord, soer, oest, vest;
    Rute[] naboer = new Rute[4];

    public Rute(int x, int y, Labyrint lab, JLabel info, JLabel paths){
        rad = x;
        kol = y;
        this.lab = lab;
        this.info = info;

        class Utvei implements ActionListener{
            int x, y;
            Labyrint lab;
            JLabel info;

            public Utvei(int x, int y, Labyrint lab, JLabel info){
                this.x = x;
                this.y = y;
                this.lab = lab;
                this.info = info;
            }
            @Override
            public void actionPerformed(ActionEvent e){
                for(Rute[] y : lab.hentArray()){
                    for(Rute rute: y){
                        rute.initGUI();
                    }
                }
                ArrayList<ArrayList<Tuppel>> stier = lab.hentRute(x, y).finnUtvei();
                lab.utveier = stier;

                if(stier != null){
                  info.setText("Det finnes " + stier.size() + " utveier fra valgt rute.");
                  ArrayList<Tuppel> kortest = finnKortestVei(stier);
                  paths.setText("Korteste utvei: " + kortest.size() + " ruter.");
                  if(kortest != null){
                    for(Tuppel tuppel : kortest){
                      Rute rute = lab.hentRute(tuppel.hentX(), tuppel.hentY());
                      rute.path();
                    }
                  }
                  else{
                      Rute rute = lab.hentRute(x, y);
                      rute.noPath();
                      paths.setText("");
                      info.setText("Ingen utveier fra valgt rute");
                  }
                }
                else{
                    Rute rute = lab.hentRute(x, y);
                    rute.noPath();
                    paths.setText("");
                    info.setText("Kan ikke bruke svarte ruter");
                }
            }
        }
        this.addActionListener(new Utvei(rad, kol, lab, info));
    }

    public void finnNabo(){
        if(kol != lab.hentAntY()-1){
            nord = lab.hentRute(rad, kol+1);
            naboer[0] = nord;
        }
        if(kol != 0){
            soer = lab.hentRute(rad, kol-1);
            naboer[1] = soer;
        }
        if(rad != lab.hentAntX()-1){
            oest = lab.hentRute(rad+1, kol);
            naboer[2] = oest;
        }
        if(rad != 0){
            vest = lab.hentRute(rad-1, kol);
            naboer[3] = vest;
        }
    }

    public boolean hentBesoekt(){return besoeket;}

    public ArrayList<ArrayList<Tuppel>> gaa(){return null;}

    public ArrayList<ArrayList<Tuppel>> finnUtvei(){
        ArrayList<ArrayList<Tuppel>> stier = gaa();
        return stier;
    }

    public void path(){}

    public void noPath(){
        setBackground(Color.RED);
    }

    public ArrayList<Tuppel> finnKortestVei(ArrayList<ArrayList<Tuppel>> stier){
        ArrayList<Tuppel> kortest = null;
        for(ArrayList<Tuppel> sti : stier){
            if(kortest == null || sti.size() < kortest.size()){
                kortest = sti;
            }
        }
        return kortest;
    }

    public int hentKol(){return kol;}
    public int hentRad(){return rad;}

    public abstract char tilTegn();

    public void initGUI(){
        setPreferredSize(new Dimension(30,30));
        setOpaque(true);
    }


}
