package fr.univtln.groupec.tperron710;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        int value = 150000;
        double tab[] = new double[value];
        for (int i = 0 ; i < value ; i ++){
            tab[i] = (double) Math.random() * value + 1;
        }

        MathFunction.heronFunction(tab,value);

    }
}
