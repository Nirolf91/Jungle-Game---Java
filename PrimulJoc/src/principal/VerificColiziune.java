package principal;

import entitati.Entitate;

public class VerificColiziune {
    Panou panoulJocului;

    public VerificColiziune(Panou panouJoc) {
        this.panoulJocului = panouJoc;
    }

    public void verificDale(Entitate entitate) {
        int entitateStangaWorldX = entitate.worldX + entitate.zonaSolida.x;
        int entitateDreaptaWorldX = entitate.worldX + entitate.zonaSolida.x + entitate.zonaSolida.width;
        int entitateSusWorldY = entitate.worldY + entitate.zonaSolida.y;
        int entitateJosWorldY = entitate.worldY + entitate.zonaSolida.y + entitate.zonaSolida.height;

        int entitateColoanaStanga = entitateStangaWorldX / panoulJocului.marimeaDale;
        int entitateDreaptaColoana = entitateDreaptaWorldX / panoulJocului.marimeaDale;
        int entitateRandSus = entitateSusWorldY / panoulJocului.marimeaDale;
        int entitateRandJos = entitateJosWorldY / panoulJocului.marimeaDale;

        int dalaNum1 = 0, dalaNum2 = 0;

        switch (entitate.directia) {
            case "sus":
                entitateRandSus = (entitateSusWorldY - entitate.viteza) / panoulJocului.marimeaDale;
                if (entitateRandSus >= 0 && entitateRandSus < panoulJocului.gestiuneDale.hartaNumereDale[0].length) {
                    dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandSus];
                    dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandSus];
                    if (panoulJocului.gestiuneDale.dale[dalaNum1].coliziune) {
                        entitate.coliziunePorinita = true;
                    } else if (panoulJocului.gestiuneDale.dale[dalaNum2].coliziune) {
                        entitate.coliziunePorinita = true;
                    }
                }
                break;
            case "jos":
                entitateRandJos = (entitateJosWorldY + entitate.viteza) / panoulJocului.marimeaDale;
                if (entitateRandJos >= 0 && entitateRandJos < panoulJocului.gestiuneDale.hartaNumereDale[0].length) {
                    dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandJos];
                    dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandJos];
                    if (panoulJocului.gestiuneDale.dale[dalaNum1].coliziune || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune) {
                        entitate.coliziunePorinita = true;
                    }
                }
                break;
            case "stanga":
                entitateColoanaStanga = (entitateStangaWorldX - entitate.viteza) / panoulJocului.marimeaDale;
                if (entitateColoanaStanga >= 0 && entitateColoanaStanga < panoulJocului.gestiuneDale.hartaNumereDale.length) {
                    dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandSus];
                    dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateColoanaStanga][entitateRandJos];
                    if (panoulJocului.gestiuneDale.dale[dalaNum1].coliziune || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune) {
                        entitate.coliziunePorinita = true;
                    }
                }
                break;
            case "dreapta":
                entitateDreaptaColoana = (entitateDreaptaWorldX + entitate.viteza) / panoulJocului.marimeaDale;
                if (entitateDreaptaColoana >= 0 && entitateDreaptaColoana < panoulJocului.gestiuneDale.hartaNumereDale.length) {
                    dalaNum1 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandSus];
                    dalaNum2 = panoulJocului.gestiuneDale.hartaNumereDale[entitateDreaptaColoana][entitateRandJos];
                    if (panoulJocului.gestiuneDale.dale[dalaNum1].coliziune || panoulJocului.gestiuneDale.dale[dalaNum2].coliziune) {
                        entitate.coliziunePorinita = true;
                    }
                }
                break;
        }
    }

    }

