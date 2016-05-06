package fr.nmartinez016;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by marti on 05/05/2016.
 */
public class CTest {

    public static void main(String[] args) {
        ObjectMapper lO = new ObjectMapper();
        CClassA lA = new CClassA(17, "ja");
        CClassB lB = new CClassB(18, "jb");
        lA.addB(lB);
        lB.setmClassA(lA);
        String lTest = null;
        CClassA nvxA = null;
        System.out.println("TEST SERIALIZE");
        try {
            lTest = lO.writeValueAsString(lA);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("SERIALIZE OK");
        System.out.println(lTest);

        System.out.println("TEST DESERIALIZE");
        try {
           nvxA  = lO.readValue(lTest, CClassA.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DESERIALIZE OK ");
        System.out.println(nvxA);
        System.out.println(nvxA.getmClassB());
    }
}
