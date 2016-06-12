package fr.univtln.groupc;

import java.util.ArrayList;

/**
 * Created by boblinux on 12/06/16.
 */
public class test {
    public static void main(String[] args) {
        // Create an ArrayList of strings.
        ArrayList<String> list = new ArrayList<>();
        list.add("cat");
        list.add("box");
        list.add("elephant");

        // Use toArray to copy ArrayList to string array.
        String[] array = new String[list.size()];
        array = list.toArray(array);

        // Loop over the string elements in the array.
        for (String item : array) {
            System.out.println(item);
        }
    }

}
