package com.hankkrutulis.fibonacciatrest.exception;

public class UpperLimitException extends RuntimeException {
   public UpperLimitException(String errorMessage) {
        super(errorMessage);
   }
}
