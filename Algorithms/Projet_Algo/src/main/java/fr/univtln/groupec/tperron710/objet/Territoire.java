package fr.univtln.groupec.tperron710.objet;

import java.util.List;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public class Territoire {

       List<Portail> LP;

    public Territoire(List<Portail> LP) {
        this.LP = LP;
    }

    public List<Portail> getLP() {
        return LP;
    }

    public void setLP(List<Portail> LP) {
        this.LP = LP;
    }

    @Override
    public String toString() {
        return "Territoire{" +
                "LP=" + LP +
                '}';
    }
}
