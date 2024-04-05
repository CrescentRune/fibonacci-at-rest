package com.hankkrutulis.fibonacciatrest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hankkrutulis.fibonacciatrest.exception.UpperLimitException;

@Service
public class FibonacciService {

    private int currentIndex = 0;
    private List<Long> fibonacciSequence;

    public FibonacciService() {
        this.fibonacciSequence = new ArrayList<Long>(Arrays.asList(0L, 1L));
    }

    public Long getCurrentItem() {
        return this.calculateItem(this.currentIndex);
    }

    public Long getNextItem() {
        this.currentIndex++;
        try {
            return this.calculateItem(this.currentIndex);
        } catch (UpperLimitException e) {
            this.currentIndex--;
            return this.getCurrentItem();
        }
    }

    public Long getPreviousItem() {
        if (currentIndex > 0) {
            this.currentIndex--;
        }
        return this.calculateItem(this.currentIndex);
    }

    public List<Long> getSequence() {
        return this.fibonacciSequence;
    }


    private long calculateItem(int index) throws UpperLimitException {
        if (index >= this.fibonacciSequence.size()) {
            return this.populateToIndex(index);
        }
        return this.fibonacciSequence.get(index);
    }
 
    private long populateToIndex(int index) throws UpperLimitException {
        if (this.fibonacciSequence.size() < 2) {
            //throw new WhatHappenedToMyValuesException();
            this.fibonacciSequence = new ArrayList<Long>(Arrays.asList(0L, 1L));
        }

        for (int i = this.fibonacciSequence.size(); i <= index; i++) {
            long next = this.fibonacciSequence.get(i-2) + this.fibonacciSequence.get(i-1);
            if (next < this.fibonacciSequence.get(i-1)) {
                throw new UpperLimitException("You've gone too far!");
            }

           this.fibonacciSequence.add(next); 
        }
        return this.fibonacciSequence.get(index);
    }


}
