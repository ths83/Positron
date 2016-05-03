package fr.univtln.groupec.tperron710.algo;

/**
 * Created by toms on 5/2/16.
 */
public class MathFunction {

    public static double[] heronFunction(double tab[], int valueToCalc){

        double result[] = new double[valueToCalc];
        double perimeterByTwo;

        long init = System.currentTimeMillis();
        for (int i = 2; i < valueToCalc; i ++ ){
            perimeterByTwo = (tab[i-2] + tab[i-1] + tab[i]) / 2;
            result[i] =Math.abs(Math.sqrt(perimeterByTwo * (perimeterByTwo - tab[i-2])
                                * (perimeterByTwo - tab[i-1]) * (perimeterByTwo - tab[i])));
        }
        System.out.println(System.currentTimeMillis()- init);
        return result;
    }

    public static double[] sinusLaw(double tab[], int valueToCalc){

        double[] result = new double[valueToCalc];
        return result;
    }
}
