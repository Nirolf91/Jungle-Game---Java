package principal;

import dale.GestionatorDale;
import entitati.Jucator;

import javax.swing.*;
import java.awt.*;

public class Panou extends JPanel implements Runnable {
    //ecranul jocului
    final int marimeOriginalaDale = 10;
    final int scala = 5;

    public final int marimeaDale = marimeOriginalaDale * scala;
    public final int maximEcranColoane = 12;
    public final int maxEcranRanduri = 12;
    public final int latimeEcran = marimeaDale * maximEcranColoane;
    public final int inaltimeEcran = marimeaDale * maxEcranRanduri;

    //configurarea valorilor lumii jocului
    public final int maxLumeColoane = 50;
    public final int maxLumeRanduri = 50;
    public final int latimeaLumii = marimeaDale * maxLumeColoane;
    public final int inaltimeaLumii = marimeaDale * maxLumeRanduri;

    public GestionatorDale gestiuneDale = new GestionatorDale(this);
    GestionatorTaste gestiuneTaste = new GestionatorTaste();
    int FPS = 60; // frames per second
    Thread firulJocului;
    public VerificColiziune verificatorColiziune = new VerificColiziune(this);
    public Jucator jucatorul = new Jucator(this, gestiuneTaste);
    public boolean jocTerminat = false;

    public Panou() {
        this.setPreferredSize(new Dimension(latimeEcran, inaltimeEcran));
        this.setBackground(new Color(247, 173, 100));
        this.setDoubleBuffered(true);
        this.addKeyListener(gestiuneTaste);
        this.setFocusable(true);
    }

    public void pornesteFirulJocului() {
        firulJocului = new Thread(this);
        firulJocului.start();
    }

    @Override
    public void run() {
        double intervalDesenare = 1000000000 / FPS;
        double urmatorulMomentDesenare = System.nanoTime() + intervalDesenare;
        while (firulJocului != null) {
            update();
            repaint();
            try {
                double timpulRamas = urmatorulMomentDesenare - System.nanoTime();
                timpulRamas = timpulRamas / 1000000;
                if (timpulRamas < 0) {
                    timpulRamas = 0;
                }
                Thread.sleep((long) timpulRamas);
                urmatorulMomentDesenare = urmatorulMomentDesenare + intervalDesenare;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        gestiuneDale.deseneaza(g2);
        jucatorul.deseneaza(g2);
        if (jocTerminat) {
            desenareJocTerminat(g2);
        }
        g2.dispose();
    }

    public void desenareJocTerminat(Graphics2D g2) {
        String text = "Joc Terminat!";
        Font font = new Font("Arial", Font.BOLD, 40);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        int x = latimeEcran / 2 - g2.getFontMetrics().stringWidth(text) / 2;
        int y = inaltimeEcran / 2;
        g2.drawString(text, x, y);
    }

    public void update() {
        if (!jocTerminat) {
            jucatorul.update();
        }
    }
}
