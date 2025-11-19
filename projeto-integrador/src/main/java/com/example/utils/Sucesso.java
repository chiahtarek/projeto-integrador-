package com.example.utils;

public class Sucesso<T> extends Resultado {
   private T obj;

   public Sucesso(String msg, T obj) {
      super(msg);
      this.obj = obj;
   }

   public T getObj() {
      return this.obj;
   }
}
