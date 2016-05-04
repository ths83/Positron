package fr.univtln.groupec.tperron710.objet;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public class Portail {
        int x;
        int y;
        int id;

    public Portail(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Portail{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }
}
