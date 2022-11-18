package class02;

/**
 * @Author: qinzhilong
 * @Date: 2022/11/18 21:01
 * 二分法找某数是否存在
 */
public class BSExist {
    public static boolean isExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length;
        int mid = 0;
        while (L < R) {
            mid = L + (R - L) >> 1;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else if (arr[mid] < num) {
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }
}
