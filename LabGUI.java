import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LabGUI{
    public static void main(String[] args){
        JFrame vindu = new JFrame("Labyrint");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel ruter = new JPanel();
        ruter.setLayout(new GridLayout(2,2));



        vindu.add(ruter);

        vindu.pack();
        vindu.setVisible(true);
    }
}
