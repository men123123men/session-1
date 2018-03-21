package ru.sbt.jschool.session1;

import java.util.Arrays;
import java.util.Random;

public class Task7 {
    public static void main(String[] args){
        Random random = new Random();
        long[]  firstRandomArray = random.longs(20, 100, 130).toArray();
        long[] secondRandomArray = random.longs(25, 100, 130).toArray();
        long[] arraysIntersection = intersection(firstRandomArray, secondRandomArray);

        System.out.println(Arrays.toString(firstRandomArray));
        System.out.println(Arrays.toString(secondRandomArray));
        System.out.println(Arrays.toString(arraysIntersection));

    }
    public static long[] intersection(long[] arr1, long[] arr2){
        long[]  first = arr1.clone();
        long[] second = arr2.clone();

        Arrays.sort(first);
        Arrays.sort(second);

        long[] intermediateArray = new long[first.length+second.length];
        int i=0, j=0, k=0;

        while (i<first.length && j<second.length) {
            if (first[i] == second[j]) {
                intermediateArray[k] = first[i];
                i++;
                j++;
                k++;
            } else if (first[i] < second[j]){
                i++;
            } else
                j++;
        }
        return Arrays.copyOf(intermediateArray,k);
    }
}
