package arithmetic.solution73;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author imythu
 * @date 2019/7/30 13:48
 */
public class Solution73 implements ISolution73 {

    private static Pattern pattern = Pattern.compile("[0-9*]+");

    public static void main(String[] args) {

        String testData = "[\n" +
                "  [0,1,2,0],\n" +
                "  [3,4,5,2],\n" +
                "  [1,3,1,5]\n" +
                "]";

        String[] rows = testData.split("\\[([0-9*]+,*)+\\]");

        int rowNumber = rows.length - 1;
        int colNumber = (testData.split("([0-9*]+),+").length - 1) / 2;

        int[][] matrix = new int[rowNumber][colNumber];

        Matcher matcher = pattern.matcher(testData);
        int count = 0;
        int row = 0;
        while (matcher.find()) {
            if (count < colNumber) {
                matrix[row][count++] = Integer.valueOf(matcher.group(0));
            } else {
                count = 0;
                row++;
                matrix[row][count++] = Integer.valueOf(matcher.group(0));
            }
        }

        ISolution73 solution73 = (ISolution73) Proxy.newProxyInstance(ISolution73.class.getClassLoader(),
                Solution73.class.getInterfaces(), new Solution73Proxy(new Solution73()));
        solution73.setZeroes(matrix);
    }

    static class Solution73Proxy implements InvocationHandler {
        Object object;
        Solution73Proxy(Object object) {
            this.object = object;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("置零前");
            int[][] beforeMatrix = (int[][]) args[0];
            for (int[] ints : beforeMatrix) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }
            System.out.println("置零后");
            method.invoke(object, args);
            int[][] afterMatrix = (int[][]) args[0];
            for (int[] ints : afterMatrix) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }
            return null;
        }
    }

    @Override
    public void setZeroes(int[][] matrix) {

        boolean firstRowHaveZero = false;
        boolean firstColumnHaveZero = false;

        // 第一行是否有 0
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstRowHaveZero = true;
                break;
            }
        }
        // 第一列是否有 0
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnHaveZero = true;
                break;
            }
        }

        // 标记
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // i 行第一个置 0
                    matrix[i][0] = 0;
                    // j 列第一个置 0
                    matrix[0][j] = 0;
                }
            }
        }

        // 将标记位置 0
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    // i 行 j 列
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    // i 列 j 行
                    matrix[j][i] = 0;
                }
            }
        }
        if (firstRowHaveZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstColumnHaveZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
