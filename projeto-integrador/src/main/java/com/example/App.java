package com.example;

import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App 
{
      public static void main(String[] args) {
        var app = Javalin.create().start(7000);

        app.get("/", ctx -> ctx.result("Hello Javalin!"));
    }
    
}
