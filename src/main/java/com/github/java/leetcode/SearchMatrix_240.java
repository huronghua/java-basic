package com.github.java.leetcode;

/**
 * 搜索
 */
public class SearchMatrix_240 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while (j >= 0 && i < matrix.length) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 1}};
        System.out.println(searchMatrix(array, 0));

    }
}
