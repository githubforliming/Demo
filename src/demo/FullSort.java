package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LM
 * @create 2019-09-10 21:11
 * 全排序
 */
public class FullSort {
    static int ii = 0;
    static List<Integer> all = new ArrayList<Integer>();

    public static void main(String[] args) {
        int sou = 12345;
        int[] data = getData(sou);
        full(data, 0, data.length - 1);
        System.out.println(all);
    }

    /**
     * 转换为 int数组
     * @param sou
     * @return
     */
    private static int[] getData(int sou) {
        String s = String.valueOf(sou);
        char[] cs = s.toCharArray();
        int[] res = new int[cs.length];
        for (int i = 0; i < cs.length; i++) {
            res[i] = Integer.valueOf(cs[i]);
        }
        return res;
    }

    // 递归获取所有排序
    public static void full(int[] data, int start, int end) {
        // 结束递归
        if (start == end) {
            int ii = 0;
            int bs = 1;
            for (int i = data.length - 1; i >= 0; i--) {
                ii = ii + bs * data[i];
                bs = bs * 10;
            }
            all.add(ii);
            return;
        }
        // 换位置
        // 1 2 3 4
        // -> 2
        // -> 3
        // -> 4
        for (int i = start; i <= end; i++) {
            ii++;
            // 换位置 进行后边递归 1 2 3 4 - > 2 1 3 4 / 3 2 1 4 / 4 2 3 1
            swap(data, start, i);
            full(data, start + 1, end);
            // 换回来
            swap(data, i, start);
        }

    }

    /**
     * 换位置
     * @param data
     * @param start
     * @param i
     */
    private static void swap(int[] data, int start, int i) {
        int tmp = data[i];
        data[i] = data[start];
        data[start] = tmp;
    }
}
