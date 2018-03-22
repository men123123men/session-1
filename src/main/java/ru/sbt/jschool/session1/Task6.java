package ru.sbt.jschool.session1;

import java.util.Random;

public class Task6 {
    public static void main(String[] args) {
        Random random = new Random();

        long  firstNumber = random.nextInt(1_000);
        long sesondNumber = random.nextInt(1_000);

        String  firstStr = Long.toBinaryString(firstNumber);
        String sesondStr = Long.toBinaryString(sesondNumber);

        long sumOfNumbers = sumOfBinary(firstStr,sesondStr);

        System.out.printf("%d + %d = ", firstNumber,sesondNumber);
        System.out.println(firstNumber+sesondNumber+" (== "+ sumOfNumbers+")");

    }

    public static long sumOfBinary(String b1, String b2) {
        int maxLength = Math.max(b1.length(),b2.length());
        long result =0;
        for(int i=0,term =1; i<maxLength; i++,term*=2){
            if(i<b1.length())
                result+=term*(b1.charAt(b1.length()-i-1)-'0');
            if(i<b2.length())
                result+=term*(b2.charAt(b2.length()-i-1)-'0');
        }
        return result;
    }
}
