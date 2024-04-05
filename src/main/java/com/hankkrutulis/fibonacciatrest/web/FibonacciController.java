package com.hankkrutulis.fibonacciatrest.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hankkrutulis.fibonacciatrest.service.FibonacciService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping(value = "/")
    public void getRoot(HttpServletResponse response) throws IOException {
        response.sendRedirect("/get");
    }

    @GetMapping(value = "/get")
    public String getCurrent() {
        return this.fibonacciService.getCurrentItem().toString();
    }

    @GetMapping(value = "/next")
    public String getNext() {
        return this.fibonacciService.getNextItem().toString();
    }

    @GetMapping(value = "/previous")
    public String getPrevious() {
        return this.fibonacciService.getPreviousItem().toString();
    }
}