package ru.sbt.jschool.session1;

//    На вход подаётся строка, содержащее число в бинарной системе счичсления.
//    Нужно вернуть десятичное число(long)
public class Task5 {
    public static void main(String[] args) {

        long startNumber = 123_456_789L;
        String binaryNumberStr = Long.toBinaryString(startNumber);
        long resultNumder = binaryToDec(binaryNumberStr);
        long resursivResultNumder = resursivBinaryToDec(binaryNumberStr);

        System.out.println(startNumber);
        System.out.println(binaryNumberStr);
        System.out.println(resultNumder);
        System.out.println(resursivResultNumder);

    }

    public static long resursivBinaryToDec(String binary){
        return resursivCalculat(binary, 1);
    }
    public static long resursivCalculat(String binary, long term) {
        if (binary.equals("")) return 0;
        return resursivCalculat(binary.substring(0,binary.length()-1), term*2)
                + (binary.charAt(binary.length()-1)-'0')*term;
    }

    // Можно и так)
    public static long binaryToDec(String binary){
        long result = 0;
        for(int i = binary.length()-1,j=1;i>=0;i--,j*=2)
            if (binary.charAt(i)=='1')
                result+=j;
        return result;
    }
}
