package fr.univtln.groupec.tperron710.objet;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public class Lien {
    Portail p1;
    Portail p2;

    public Lien(Portail p1, Portail p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Portail getP1() {
        return p1;
    }

    public void setP1(Portail p1) {
        this.p1 = p1;
    }

    public Portail getP2() {
        return p2;
    }

    public void setP2(Portail p2) {
        this.p2 = p2;
    }
}
