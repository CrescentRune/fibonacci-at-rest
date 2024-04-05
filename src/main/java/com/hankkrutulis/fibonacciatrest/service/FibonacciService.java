package com.hankkrutulis.fibonacciatrest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

    private int currentIndex = 0;
    private List<Integer> fibonacciSequence;

    public FibonacciService() {
        this.fibonacciSequence = new ArrayList<Integer>(Arrays.asList(0, 1));
    }

    public Integer getCurrentItem() {
        return this.calculateItem(this.currentIndex);
    }

    public Integer getNextItem() {
        this.currentIndex++;
        return this.calculateItem(this.currentIndex);
    }

    public Integer getPreviousItem() {
        if (currentIndex > 0) {
            this.currentIndex--;
        }
        return this.calculateItem(this.currentIndex);
    }


    private int calculateItem(int index) {
        if (index >= this.fibonacciSequence.size()) {
            return this.populateToIndex(index);
        }
        return this.fibonacciSequence.get(index);
    }
 
    private int populateToIndex(int index) {
        if (this.fibonacciSequence.size() < 2) {
            //throw new WhatHappenedToMyValuesException();
            this.fibonacciSequence = new ArrayList<Integer>(Arrays.asList(0, 1));
        }

        for (int i = this.fibonacciSequence.size(); i <= index; i++) {
           this.fibonacciSequence.add(
            this.fibonacciSequence.get(i - 2) + this.fibonacciSequence.get(i-1)
           ); 
        }
        return this.fibonacciSequence.get(index);
    }


}
