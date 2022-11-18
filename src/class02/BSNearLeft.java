package class02;

/**
 * @Author: qinzhilong
 * @Date: 2022/11/18 21:11
 * 有序数组中，找>=某个数最左的位置
 */
public class BSNearLeft {
    public static int nearestLeft(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L < R) {
            int mid = L + (R - L) >> 1;
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid - 1;
            }
        }
        return index;
    }
}
