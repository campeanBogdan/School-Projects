package com.example.Assignment3.Controller;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController {
    private static final String PATH = "/error";
    private ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }
}
