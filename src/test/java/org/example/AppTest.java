package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


class AppTest {

    private App m;

    @BeforeEach
    public void init() {
        m = new App();
    }

    @AfterEach
    public void tearDown() {
        m = null;
    }

    @Test
    public void getMatrixFromFile() throws Exception {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertArrayEquals(matrix, m.readMatrix("./src/main/java/resources/matrix", 3, 3));
    }

    @Test
    public void transpose() {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] result = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        assertArrayEquals(result, m.transpose(matrix));
    }

    @Test
    public void sum() {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] m2 = {{1, 2}, {3, 4}};
        double[][] result = {{2, 4}, {6, 8}};
        assertArrayEquals(result, m.sum(m1, m2));
    }

    @Test
    public void sub() {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] m2 = {{1, 2}, {3, 4}};
        double[][] result = {{0, 0}, {0, 0}};
        assertArrayEquals(result, m.sub(m1, m2));
    }

    @Test
    public void multiplyByNumber() {
        double[][] m1 = {{1, 2}, {3, 4}};
        int num = 3;
        double[][] result = {{3, 6}, {9, 12}};
        assertArrayEquals(result, m.multiplyByNumber(m1, num));
    }

    @Test
    public void multiply() {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] m2 = {{1, 2}, {3, 4}};
        double[][] result = {{7, 10}, {15, 22}};
        assertArrayEquals(result, m.multiply(m1, m2));
    }

    @Test
    public void isEqual() {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] m2 = {{1, 2}, {3, 5}};
        assertFalse(m.isEqual(m1, m2));
    }

    @ParameterizedTest
    @CsvSource({
        "1",
        "2",
        "3"
    })
    public void multiplyByNumber(double num) {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] exp_result = new double[m1.length][m1[0].length];
        for (int i = 0; i < exp_result.length; i++) {
            for (int j = 0; j < exp_result[i].length; j++) {
                exp_result[i][j] = m1[i][j] * num;
            }
        }
        double[][] act_result = m.multiplyByNumber(m1, num);
        assertArrayEquals(exp_result, act_result);
    }

    @ParameterizedTest
    @CsvSource({
        "./src/main/java/resources/matrix1",
        "./src/main/java/resources/matrix2"
    })
    public void sumFromFile(String filename) throws FileNotFoundException {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] m2 = m.readMatrix(filename,2,2);
        double[][] exp_result = new double[m1.length][m1[0].length];
        for (int row = 0; row < exp_result.length; row++) {
            for (int col = 0; col < exp_result[row].length; col++) {
                exp_result[row][col] = m1[row][col] + m2[row][col];
            }
        }
        double[][] act_result = m.sum(m1, m2);
        assertArrayEquals(exp_result, act_result);
    }

    @ParameterizedTest
    @CsvSource({
        "./src/main/java/resources/matrix1",
        "./src/main/java/resources/matrix2"
    })
    public void isEqualFromFile(String filename) throws FileNotFoundException {
        double[][] m1 = {{0, 0}, {0, 0}};
        double[][] m2 = m.readMatrix(filename,2,2);
        boolean exp_result = true;
        if ((m1.length != m2.length) || (m1[0].length != m2[0].length)) exp_result = false;
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    exp_result = false;
                    break;
                }
            }
        }
        boolean act_result = m.isEqual(m1, m2);
        assertEquals(exp_result, act_result);
    }
}
