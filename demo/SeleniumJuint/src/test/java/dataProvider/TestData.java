package dataProvider;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import testClass_Package.VerifyLoginPage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class TestData {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "C:\\Users\\u729097\\OneDrive - Finastra\\Desktop\\suite.txt";
        String line = "";
        List<String> temps = new ArrayList<String>();
        String[] tempsArray = new String[temps.size()];
        Scanner inFile1 = new Scanner(new File(path)).useDelimiter("\\s\\n");
            while (inFile1.hasNext()) {
                line = inFile1.next();
                temps.add(line);
            }
            inFile1.close();
            System.out.println(temps);
        tempsArray = temps.toArray(tempsArray);
        int size =tempsArray.length;
        System.out.println(size);
        System.out.println(Arrays.toString(tempsArray));

        for(int k=0;k<tempsArray.length;k++)
        {
//            System.out.println(tempsArray[k]);
//            org.junit.runner.JUnitCore.main(tempsArray[k]);
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(Class.forName(tempsArray[k]));
        }

        }
    }


