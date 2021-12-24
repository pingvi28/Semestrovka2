package ru.kpfu.itis.testpackage;

import java.io.PrintWriter;
import java.util.*;

public class FillArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] arr = new int[n][m];
        fillArray(arr, n, m);
        printArray(arr, n, m);

    }

    private static void printArray(int[][] arr, int n, int m) {
        PrintWriter pw = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pw.print(arr[i][j] + " ");
            }
            pw.println();
        }
        pw.close();
    }

    private static Random random;
    private static void fillArray(int[][] arr, int n, int m) {
        random = new Random();
        List<Integer> list = new LinkedList();
        for (int i = 0; i < n * m; i++) {
            list.add(i);
        }
        if (arrayHaveOddElements(n, m)) {
//            !!!!!!!!!!!!!!!!!
            int index = getRandomIndex(list.size());
            setValue(list, index, arr, n, m, -2); //-2 значение без пары
            list.remove(index);
        }
        for (int i = 0; i < (n*m)/2; i++) {
            int index = getRandomIndex(list.size());
            setValue(list, index, arr, n, m, i);
            list.remove(index);

            index = getRandomIndex(list.size());
            setValue(list, index, arr, n, m, i);
            list.remove(index);
        }
    }

    private static void setValue(List<Integer> list, int index, int[][] arr, int n, int m, int value) {
        int t = list.get(index);
        int x = t / m;
        int y = t % m;
        arr[x][y] = value;
    }

    private static int getRandomIndex(int range) {
        return random.nextInt(range);
    }

    private static boolean arrayHaveOddElements(int n, int m) {
        return (n % 2 == 1) && (m % 2 == 1);
    }
}
