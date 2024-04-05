package com.hankkrutulis.fibonacciatrest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

public class FibonacciServiceTest {

    FibonacciService fibonacciService;

    @BeforeEach
    void recreateService() {
        fibonacciService = new FibonacciService();
    }

    // Check for prepopulation since the calculation depends on it
    @Test
    @Description("Base case values [0,1] (long) should be prepoluated")
    public void testDefaultValues() {
        List<Long> sequence = this.fibonacciService.getSequence();

        assertEquals(sequence.get(0), 0L, "Didn't initialize base case 0");
        assertEquals(sequence.get(1), 1L, "Didn't initialize base case 1");
    }

    // Fibonacci logic is straightforward enough we can recreate it here as a regression test
    @Test
    @Description("Fibonacci calculation is correct")
    public void testCalculation() {
        for (int i = 0; i < 100; i++) {
            this.fibonacciService.getNextItem();
        }

        List<Long> sequence = this.fibonacciService.getSequence();

        for (int i = 2; i < sequence.size(); i++) {
            assertEquals(sequence.get(i-2) + sequence.get(i-1), sequence.get(i));
        }
    }


    // List will stop increasing/growing as the overflow for long is met
    @Test
    @Description("Upperbound is not exceeded")
    public void testUpperbound() {
        for (int i = 0; i < 1000; i++) {
            this.fibonacciService.getNextItem();
        }
        long a = this.fibonacciService.getNextItem();
        long b = this.fibonacciService.getNextItem();

        assertEquals(a, b, "getNextItem() should return the same value once upperbound is met");

        List<Long> sequence = this.fibonacciService.getSequence();
        assertNotEquals(sequence.get(sequence.size() - 1), sequence.get(sequence.size() - 2), "computed sequence should not store redundant items");
    }


    // List will not cause issues for "prev" on base case
    @Test
    @Description("Lowerbound is not exceeded")
    public void testLowerbound() {
        long a = this.fibonacciService.getPreviousItem();
        assertEquals(0L, a, "getPreviousItem() should return zero when cursor is at zero");

        for (int i = 0; i < 25; i++) {
            this.fibonacciService.getNextItem();
        }
        for (int i = 0; i < 25; i--) {
            a = this.fibonacciService.getPreviousItem();
        }

        assertEquals(0L, a, "getPreviousItem() should return zero when cursor is at zero");
    }

    // Test the implementation of current relative to the previous and next calls happening around it
    @Test
    @Description("getCurrentItem() should reflect same value as last prev() or next()")
    public void testCurrent() {
        long a = -1L;
        for (int i = 0; i < 20; i++) {
            a = this.fibonacciService.getNextItem();
        }
        long b = this.fibonacciService.getCurrentItem();
        assertEquals(a, b);

        for (int i = 0; i < 10; i--) {
            a = this.fibonacciService.getPreviousItem();
        }
        b = this.fibonacciService.getCurrentItem();
        assertEquals(a, b);
    }
    
}
