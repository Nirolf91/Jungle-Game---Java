package dale;

import principal.Panou;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class GestionatorDale {

    Panou panoulJocului;
    public Dale[] dale;
    public int hartaNumereDale[][];

    public GestionatorDale(Panou panoulJocului) {
        this.panoulJocului = panoulJocului;
        dale = new Dale[10];
        hartaNumereDale = new int[panoulJocului.maxLumeColoane][panoulJocului.maxLumeRanduri];
        obtineImagineDale();
        incarcaHarta("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\dale\\harti\\harta2.txt"); // cale absolută
    }

    private void incarcaHarta(String harta) {
        try {
            InputStream is = new FileInputStream(harta);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int coloana = 0;
            int randul = 0;

            while (coloana < panoulJocului.maxLumeColoane && randul < panoulJocului.maxLumeRanduri) {
                String linie = br.readLine();

                while (coloana < panoulJocului.maxLumeColoane) {
                    String[] numere = linie.split(" ");
                    int numar = Integer.parseInt(numere[coloana]);
                    hartaNumereDale[coloana][randul] = numar;
                    coloana++;
                }
                if (coloana == panoulJocului.maxLumeColoane) {
                    coloana = 0;
                    randul++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void obtineImagineDale() {
        try {
            dale[0] = new Dale();
            dale[0].image = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\dale\\arbore.png"));
            dale[0].coliziune = true;

            dale[1] = new Dale();
            dale[1].image = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\dale\\drum.png"));

            dale[2] = new Dale();
            dale[2].image = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\dale\\banana.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deseneaza(Graphics g2) {
        int lumeColoane = 0;
        int lumeRanduri = 0;

        while (lumeColoane < panoulJocului.maxLumeColoane && lumeRanduri < panoulJocului.maxLumeRanduri) {
            int numarDale = hartaNumereDale[lumeColoane][lumeRanduri];

            int lumeX = lumeColoane * panoulJocului.marimeaDale;
            int lumeY = lumeRanduri * panoulJocului.marimeaDale;

            int ecranX = lumeX - panoulJocului.jucatorul.worldX + panoulJocului.jucatorul.ecranX;
            int ecranY = lumeY - panoulJocului.jucatorul.worldY + panoulJocului.jucatorul.ecranY;

            g2.drawImage(dale[numarDale].image, ecranX, ecranY, panoulJocului.marimeaDale, panoulJocului.marimeaDale, null);
            lumeColoane++;

            if (lumeColoane == panoulJocului.maxLumeColoane) {
                lumeColoane = 0;
                lumeRanduri++;
            }
        }
    }
}
