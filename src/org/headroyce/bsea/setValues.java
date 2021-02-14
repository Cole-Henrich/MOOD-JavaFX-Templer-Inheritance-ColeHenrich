package org.headroyce.bsea;

import java.util.Scanner;

public class setValues {
    public setValues(Object varToAlter, double defaultVal, boolean run, String prompt) {
        if (run) {
            System.out.println(prompt);
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String percent = userInput.replace("%", "");
            double Percent = Double.parseDouble(percent);
            double getMultiplier = Percent / 100.0;
            varToAlter = defaultVal * getMultiplier;
            System.out.println(varToAlter);
        } else {
            varToAlter = defaultVal;
        }
    }
}
