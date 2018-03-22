package ru.sbt.jschool.session1;

import java.util.Arrays;
import java.util.SplittableRandom;

public class Task7 {
    public static void main(String[] args){
        SplittableRandom random = new SplittableRandom();
        long[]  firstRandomArray = random.longs(20, 100, 130).toArray();
        long[] secondRandomArray = random.longs(25, 100, 130).toArray();

//        для бОльшей "читабельности" результатов
//        Arrays.sort(firstRandomArray);
//        Arrays.sort(secondRandomArray);

        System.out.println(Arrays.toString(firstRandomArray));
        System.out.println(Arrays.toString(secondRandomArray));

        long[] arraysIntersection = intersection(firstRandomArray, secondRandomArray);
        long[] arraysIntersectionSecondVersion = intersectionSecondVersion(firstRandomArray, secondRandomArray);

        System.out.println(Arrays.toString(arraysIntersection));
        System.out.println(Arrays.toString(arraysIntersectionSecondVersion));


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


    public static long[] intersectionSecondVersion(long[] arr1, long[] arr2){

        // o(n)
        long[] large = arr1.length>arr2.length? arr1.clone(): arr2.clone();
        // o(1)
        long[] small = arr1.length>arr2.length? arr2: arr1;

        // о(n*log(n))
        Arrays.sort(large);

        // o(n) (JVM вынуждена пройтись по всему массиву и "забить" его нулями при создании)
        long[] intermediateArray = new long[large.length];
        int k = 0;

        // o(m*log(n)),
        // где n - кол-во элементов в большем массиве,
        // а   m - кол-во элементов в меньшем массиве
        for (long current:small){
            int head=0, tail=large.length, position;
            while (tail-head>1){
                position = (tail+head)/2;
                if (large[position]==current) {
                    intermediateArray[k++] = current;
                    break;
                }
                else if (large[position]>current)
                    tail = position;

                else
                    head = position;
            }
        }

        // Arrays.copyOf() -> System.arraycopy() -> он "нативный"
        // предполагаю что o(n)
        return Arrays.copyOf(intermediateArray,k);
    }

    /*
    * Один из возможных выводов:
    *
    * [101, 101, 102, 103, 106, 106, 108, 111, 113, 113, 115, 120, 121, 121, 125, 126, 127, 128, 128, 128]
    * [100, 102, 103, 104, 107, 108, 110, 111, 112, 112, 113, 113, 114, 115, 118, 120, 120, 121, 124, 124, 124, 124, 125, 128, 128]
    * [102, 103, 108, 111, 113, 113, 115, 120, 121, 125, 128, 128]             <- старый метод
    * [102, 103, 108, 111, 113, 113, 115, 120, 121, 121, 125, 128, 128, 128]   <-  новый метод
    *
    * Т.е. этот новый метод некорректно обрабатывет повторяющиеся элементы,
    * хотя в нем сортировка используется всего лишь раз.
    *
    * Например если в меньшем массиве несколько чисел 15, а в большем это число встречется лишь раз,
    * то в пезультат попадут несколько чисел 15(а должен попасть лишь один)
    *
    * В принципе можно завести какую-нибудь мапу для помечания уже попавших элементов...
    *
    */

}
