package fr.univtln.groupc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arouani277 on 30/05/16.
 */
public class test {
    public static void main(String[] args) {
        String s = "15.156414, 1561.154984";
        String match = null;
        Pattern pattern1 = Pattern.compile("\\b,.*\\b");
        Pattern pattern2 = Pattern.compile("\\b.*,");

        Matcher matcher1 = pattern1.matcher(s) ;
        while (matcher1.find()) {
            match = matcher1.group().replace(", ","");
            System.out.println(match) ;
        }

        Matcher matcher2 = pattern2.matcher(s) ;
        while (matcher2.find()) {
            match = matcher2.group().replace(",","");
            System.out.println(match) ;
        }

    }
}
