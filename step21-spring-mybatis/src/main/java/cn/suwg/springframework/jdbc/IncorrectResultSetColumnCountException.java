package cn.suwg.springframework.jdbc;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class IncorrectResultSetColumnCountException extends RuntimeException {

    private final int expectedCount;

    private final int actualCount;

    public IncorrectResultSetColumnCountException(int expectedCount, int actualCount) {
        super("Incorrect column count: expected " + expectedCount + ", actual " + actualCount);
        this.expectedCount = expectedCount;
        this.actualCount = actualCount;
    }
}
