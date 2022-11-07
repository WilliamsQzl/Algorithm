package class01;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * @Author: qinzhilong
 * @Date: 2022/11/6 22:03
 *  找数组中任意局部最小值返回
 */
public class Jbzx {

    /**
     * 主程序
     * @param arr
     * @return
     */
    public static int getLessIndex(int[] arr) {
        //不存在的情况
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //先处理边界条件
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 2;
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }

    /**
     * 验证是否正确
     * @param index
     * @param arr
     * @return
     */
    public static boolean isRight(int index, int arr[]) {
        if (arr.length <= 1) {
            return true;
        }
        if (index == 0) {
            return arr[index] < arr[index + 1];
        }
        if (index == arr.length - 1) {
            return arr[index] < arr[index - 1];
        }
        return arr[index] < arr[index + 1] && arr[index] < arr[index - 1];
    }

    /**
     * 生成随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        arr[0] = (int)(Math.random() * maxValue) - (int)(Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int)(Math.random() * maxValue) - (int)(Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 9000;
        int maxSize = 30;
        int maxValue = 1000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int ans = getLessIndex(arr);
            if (!isRight(ans, arr)) {
                System.out.println("出错了！");
                System.out.println("arr:" + Arrays.toString(arr));
                System.out.println("ans:" + ans);
                break;
            }
        }
        System.out.println("测试结束！");
    }

}
