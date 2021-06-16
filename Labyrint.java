import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//sidsel.snellingen@enebakk.kommune.no;

//JFileChooser
//ved trykking paa rute uten utveier / svart rute - gjør ruten rød
//Hvis informasjon om hvor mange utveier som finnes fra punktet


class Labyrint{
    private Rute[][] ruteArray;
    private int antX, antY;
    Labyrint lab;
    public ArrayList<ArrayList<Tuppel>> utveier = null;
    int index = -1;

    public static void main(String[]args) throws Exception{
        Labyrint lab = new Labyrint();
    }

    public Labyrint() throws FileNotFoundException{
        JFileChooser jfc = new JFileChooser("C:/Users/Henri/OneDrive - Universitetet i Oslo/2_semester/IN1010/Oblig7/labyrinter");
        jfc.showSaveDialog(null);
        File fil = jfc.getSelectedFile();



        String[] data;
        Scanner scanner = new Scanner(fil);
        String linje = scanner.nextLine();
        data = linje.split(" ");
        antY = Integer.parseInt(data[0]);
        antX = Integer.parseInt(data[1]);
        ruteArray = new Rute[antY][antX];

        JFrame vindu = new JFrame("Labyrint");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel oppsett = new JPanel();
        oppsett.setLayout(new BoxLayout(oppsett, BoxLayout.Y_AXIS));

        JPanel lab = new JPanel();
        lab.setLayout(new GridLayout(antY, antX));

        JPanel knapper = new JPanel();
        knapper.setLayout(new GridLayout(1, 4));

        JPanel infoPanel = new JPanel();
        JLabel info = new JLabel("Klikk paa en hvit rute");
        JLabel paths = new JLabel("");
        info.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        paths.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        //Exit-knapp
        JButton exit = new JButton("Exit");
        class Stopper implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
        exit.addActionListener(new Stopper());
        knapper.add(exit);


        JButton next = new JButton(" > ");
        class Neste implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                for(Rute[] rad : ruteArray){
                    for(Rute r : rad){
                        r.initGUI();
                    }
                }
                if(index == utveier.size()-1){
                    index = -1;
                }
                index++;
                ArrayList<Tuppel> sti = utveier.get(index);
                for(Tuppel t : sti){
                    Rute rute = hentRute(t.hentX(), t.hentY());
                    rute.path();
                }
            }
        }
        next.addActionListener(new Neste());

        JButton last = new JButton(" < ");
        class Forrige implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                for(Rute[] rad : ruteArray){
                    for (Rute r : rad){
                        r.initGUI();
                    }
                }
                if(index == 0){
                    index = utveier.size();
                }
                index--;
                ArrayList<Tuppel> sti = utveier.get(index);
                for(Tuppel t : sti){
                    Rute rute = hentRute(t.hentX(), t.hentY());
                    rute.path();
                }
            }
        }
        last.addActionListener(new Forrige());
        knapper.add(last);
        knapper.add(next);


        char kar;
        int i = 0;

        while(scanner.hasNextLine()){
            linje = scanner.nextLine();
            for(int e = 0; e<antX; e++){
                kar = linje.charAt(e);
                Rute rute;
                if(kar == '#'){
                    rute = new SvartRute(e, i, this, info, paths);
                }
                else if(e == 0 || i == 0 || e == antX-1 || i == antY-1){
                    rute = new Aapning(e, i, this, info, paths);
                }
                else{
                    rute = new HvitRute(e, i, this, info, paths);
                }
                ruteArray[i][e] = rute;
                lab.add(rute);
            }
            i++;
        }

        //Reset-knapp
        JButton reset = new JButton("Reset");
        class Reseter implements ActionListener{
            Rute[][] array;
            JLabel info;
            JLabel paths;
            public Reseter(Rute[][] array, JLabel info, JLabel paths){
                this.array = array;
                this.info = info;
                this.paths = paths;
            }
            @Override
            public void actionPerformed(ActionEvent e){
                info.setText("Klikk paa en hvit rute");
                paths.setText("");
                for(Rute[] y : array){
                    for(Rute rute: y){
                        rute.initGUI();
                    }
                }
            }
        }
        reset.addActionListener(new Reseter(ruteArray, info, paths));
        knapper.add(reset);


        for(Rute[] y : ruteArray){
            for(Rute x : y){
                x.finnNabo();
            }
        }
        infoPanel.add(info);
        infoPanel.add(paths);

        oppsett.add(lab);
        oppsett.add(knapper);
        oppsett.add(infoPanel);
        vindu.add(oppsett);

        vindu.pack();
        vindu.setVisible(true);
        //System.out.println(this);
    }

    public Rute hentRute(int x, int y){
        return ruteArray[y][x];
    }

    public int hentAntX(){
        return antX;
    }

    public int hentAntY(){
        return antY;
    }

    public Rute[][] hentArray(){
        return ruteArray;
    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int x, int y){
        Rute start = hentRute(x, y);
        ArrayList<ArrayList<Tuppel>> stier = start.finnUtvei();
        return stier;
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

    @Override
    public String toString(){
        String string = "";
        for(Rute[] y:ruteArray){
            for(Rute x:y){
                 string += x.tilTegn();
            }
            string += "\n";
        }
        return string;
    }

}
