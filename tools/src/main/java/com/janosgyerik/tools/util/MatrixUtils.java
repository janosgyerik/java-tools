package com.janosgyerik.tools.util;

import java.util.Arrays;

/**
 * Utility class to work with matrices
 */
public class MatrixUtils {

    private MatrixUtils() {
        // utility class, forbidden constructor
    }

    /**
     * Format matrix as String, by joining Arrays.toString of each row
     * @param matrix the matrix to format
     * @return the matrix String
     */
    public static String toString(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (matrix.length > 0) {
            builder.append(Arrays.toString(matrix[0]));
            for (int i = 1; i < matrix.length; ++i) {
                builder.append(", ").append(Arrays.toString(matrix[i]));
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
