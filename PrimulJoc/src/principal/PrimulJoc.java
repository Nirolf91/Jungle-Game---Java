package principal;

import javax.swing.*;

public class PrimulJoc {
    public static void main(String[] args) {
        JFrame fereastra = new JFrame();
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.setResizable(true);
        fereastra.setTitle("Profesor joc");

        Panou panoulJocului = new Panou();
        fereastra.add(panoulJocului);
        fereastra.pack();
        fereastra.setLocationRelativeTo(null);
        fereastra.setVisible(true);

        panoulJocului.pornesteFirulJocului();
    }
}
