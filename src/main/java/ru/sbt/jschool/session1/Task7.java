package ru.sbt.jschool.session1;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Task7 {
    public static void main(String[] args){
        SplittableRandom random = new SplittableRandom();
        long[]  firstRandomArray = random.longs(20, 100, 130).toArray();
        long[] secondRandomArray = random.longs(25, 100, 130).toArray();
        long[] arraysIntersection = intersection(firstRandomArray, secondRandomArray);

        System.out.println(Arrays.toString(firstRandomArray));
        System.out.println(Arrays.toString(secondRandomArray));
        System.out.println(Arrays.toString(arraysIntersection));

    }
    /*
    *
    * Нужно ли сортировать второй массив?
    * Да, нужно. Если мы боримся за вычислительную эффективность,
    * то целесобразнее сначала отсортировать оба, а потом один раз пройтись в поисках соответствий!
    *
    *
    * Какова сложность сортировки?
    * Arrays.sort() -> DualPivotQuicksort.sort()
    * А у быстрой сортировки сложность по времяни о(n*log(n)).
    * (эта часть будет самая "тяжелая" по времяни во всем методе)
    *
    *
    * Какова сложность поиска элемента в отсортированном массиве?
    * Линейная(один раз проходимся по обоим массивам) по времени т.е. о(n)
    *
    *
    * Что будет если 1 массив 1 000 000 элементов, а второй 2 элемента?
    * Что будет если наоборот?
    * В обоих случаях все отработает корректно за o(n*log(n)) по времяни.
    *
    */

    public static long[] intersection(long[] arr1, long[] arr2){
        // o(n)
        long[]  first = arr1.clone();
        // o(n)
        long[] second = arr2.clone();

        // о(n*log(n))
        Arrays.sort(first);
        // о(n*log(n))
        Arrays.sort(second);

        // o(n) (JVM вынуждена пройтись по всему массиву и "забить" его нулями при создании)
        long[] intermediateArray = new long[first.length+second.length];
        // o(1)
        int i=0, j=0, k=0;

        // o(n)
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

        // Arrays.copyOf() -> System.arraycopy() -> он "нативный"
        // предполагаю что o(n)
        return Arrays.copyOf(intermediateArray,k);
    }
}
