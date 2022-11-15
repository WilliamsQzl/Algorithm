package class02;

import java.util.*;

/**
 * @Author: qinzhilong
 * @Date: 2022/11/13 21:34
 * 一个数组中有一种数出现了k次，其他数都出现了m次，m>1，k<m，找出现了k次的数，要求，额外空间复杂度O(1)，时间复杂度O(n)
 */
public class Kmtimes {

    public static Map<Integer, Integer> mapCreator(Map map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
        return map;
    }

    public static Map<Integer, Integer> map = new HashMap<>();

    /**
     * 第二种解法,求数组的方式略微复杂
     *
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int onlyTimes2(int[] arr, int k, int m) {
        int[] help = new int[32];
        if (map.size() == 0) {
            mapCreator(map);
        }
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                while (num != 0) {
                    int rightOne = num & -num;
                    help[map.get(rightOne)]++;
                    num ^= rightOne;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (help[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    /**
     * 主程序,简洁版
     *
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int onlyTimes(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    /**
     * 测试方法
     *
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int num : arr) {
            if (map1.containsKey(num)) {
                map1.put(num, map1.get(num) + 1);
            } else {
                map1.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map1.keySet()) {
            if (map1.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static int[] arrGen(int maxKinds, int maxNum, int k, int m) {
        int kNum = randomNum(maxNum);
        int kTimes = k;
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        int[] arr = new int[kTimes + (numKinds - 1) * m];
        int index = 0;
        for (; index < kTimes; index++) {
            arr[index] = kNum;
        }
        numKinds--;
        Set<Integer> set = new HashSet<>();
        set.add(kNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNum(maxNum);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
//            arr[i] = arr[i] ^ arr[j];
//            arr[j] = arr[i] ^ arr[j];
//            arr[i] = arr[i] ^ arr[j];
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static int randomNum(int maxNum) {
        return (int) (Math.random() * (maxNum + 1)) - (int) (Math.random() * (maxNum + 1));
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        int maxKinds = 5;
        int maxNum = 10;
        int testTimes = 1;
        int a = (int) (Math.random() * (maxNum) + 1);
        int b = (int) (Math.random() * (maxNum) + 1);
        int k = Math.min(a, b);
        int m = Math.max(a, b);
        if (k == m) {
            k = (int) (Math.random() * (m - 1));
        }

        while (testTimes > 0) {
            int[] arr = arrGen(maxKinds, maxNum, k, m);
            int test1 = onlyTimes(arr, k, m);
            int test2 = onlyTimes2(arr, k, m);
            int test3 = test(arr, k, m);
//
//            if (!(test1 == test2 && test2 == test3 && test1 == test3)) {
//                System.out.println("出错了");
//            }
            System.out.println(Arrays.toString(arr));
            System.out.println(test1);
            System.out.println(test2);
            System.out.println(test3);
            testTimes--;
        }

        System.out.println("测试结束");
    }


}
