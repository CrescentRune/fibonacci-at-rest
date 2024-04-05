package com.hankkrutulis.fibonacciatrest.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import com.hankkrutulis.fibonacciatrest.service.FibonacciService;


/*
 * Since I implemented a service layer for the main logic, the endpoints are
 * just pass-through. I added these simple tests for coverage and extensibility
 * nonetheless.
 */
@ExtendWith(MockitoExtension.class)
public class TestFibonacciController {
 
  @InjectMocks
  FibonacciController fibonacciController;

  @Mock
  FibonacciService fibonacciService;

  @Test
  @Description("/get -- value should pass through")
  void getReturnsResult() {
    Mockito.when(fibonacciService.getCurrentItem()).thenReturn(13L);
    String response = fibonacciController.getCurrent();

    assertEquals("13", response);
  }

  @Test
  @Description("/prev -- value should pass through")
  void prevReturnsResult() {
    Mockito.when(fibonacciService.getPreviousItem()).thenReturn(8L);
    String response = fibonacciController.getPrevious();

    assertEquals("8", response);
  }


  @Test
  @Description("/next -- value should pass through")
  void nextReturnsResult() {
    Mockito.when(fibonacciService.getNextItem()).thenReturn(13L);
    String response = fibonacciController.getNext();

    assertEquals("13", response);
  }

  
}