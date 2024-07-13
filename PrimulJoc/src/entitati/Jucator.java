package entitati;

import principal.GestionatorTaste;
import principal.Panou;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Jucator extends Entitate {
    Panou panouJoc;
    GestionatorTaste gestiuneTaste;
    public final int ecranX;
    public final int ecranY;

    public Jucator(Panou panou, GestionatorTaste gestiuneTaste) {
        this.panouJoc = panou;
        this.gestiuneTaste = gestiuneTaste;

        ecranX = panouJoc.latimeEcran / 2 - (panou.marimeaDale / 2);
        ecranY = panouJoc.inaltimeEcran / 2 - (panou.marimeaDale / 2);

        zonaSolida = new Rectangle();
        zonaSolida.x = 12;
        zonaSolida.y = 12;
        zonaSolida.width = 14;
        zonaSolida.height = 14;

        configuramValorileInitiale();
        obtinemImagineaJucatorului();
    }

    private void obtinemImagineaJucatorului() {
        try {
            jucatorSus1 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorSus1.png"));
            jucatorSus2 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorSus2.png"));
            jucatorJos1 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorJos1.png"));
            jucatorJos2 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorJos2.png"));
            jucatorStanga1 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorStanga1.png"));
            jucatorStanga2 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorStanga2.png"));
            jucatorDreapta1 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorDreapta1.png"));
            jucatorDreapta2 = ImageIO.read(new File("C:\\projects\\scout\\PrimulJoc\\src\\resurse\\jucator\\jucatorDreapta2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configuramValorileInitiale() {
        worldX = panouJoc.marimeaDale * 10;
        worldY = panouJoc.marimeaDale * 6;
        viteza = 4;
        directia = "dreapta";
    }

    public void deseneaza(Graphics2D g2) {
        BufferedImage image = null;
        switch (directia) {
            case "sus":
                if (numarImagine == 1) {
                    image = jucatorSus1;
                }
                if (numarImagine == 2) {
                    image = jucatorSus2;
                }
                break;
            case "jos":
                if (numarImagine == 1) {
                    image = jucatorJos1;
                }
                if (numarImagine == 2) {
                    image = jucatorJos2;
                }
                break;
            case "stanga":
                if (numarImagine == 1) {
                    image = jucatorStanga1;
                }
                if (numarImagine == 2) {
                    image = jucatorStanga2;
                }
                break;
            case "dreapta":
                if (numarImagine == 1) {
                    image = jucatorDreapta1;
                }
                if (numarImagine == 2) {
                    image = jucatorDreapta2;
                }
                break;
        }
        g2.drawImage(image, ecranX, ecranY, panouJoc.marimeaDale, panouJoc.marimeaDale, null);
    }

    public void update() {
        if (gestiuneTaste.susApasat || gestiuneTaste.josApasat ||
                gestiuneTaste.stangaApasat || gestiuneTaste.dreaptaApasat) {

            if (gestiuneTaste.susApasat) {
                directia = "sus";
            } else if (gestiuneTaste.josApasat) {
                directia = "jos";
            } else if (gestiuneTaste.stangaApasat) {
                directia = "stanga";
            } else if (gestiuneTaste.dreaptaApasat) {
                directia = "dreapta";
            }

            coliziunePorinita = false;
            panouJoc.verificatorColiziune.verificDale(this);

            if (!coliziunePorinita) {
                switch (directia) {
                    case "sus":
                        worldY -= viteza;
                        break;
                    case "jos":
                        worldY += viteza;
                        break;
                    case "stanga":
                        worldX -= viteza;
                        break;
                    case "dreapta":
                        worldX += viteza;
                        break;
                }
            }

            // Verificare finalizare joc
            int entitateRand = worldY / panouJoc.marimeaDale;
            int entitateColoana = worldX / panouJoc.marimeaDale;
            if (panouJoc.gestiuneDale.hartaNumereDale[entitateColoana][entitateRand] == 2) {
                panouJoc.jocTerminat = true;
            }

            numaratorImagine++;
            if (numaratorImagine > 10) {
                if (numarImagine == 1) {
                    numarImagine = 2;
                } else if (numarImagine == 2) {
                    numarImagine = 1;
                }
                numaratorImagine = 0;
            }
        }
    }
}
