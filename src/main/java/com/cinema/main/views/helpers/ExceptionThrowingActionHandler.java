package com.cinema.main.views.helpers;

@FunctionalInterface
public interface ExceptionThrowingActionHandler<T> {
  void handle(T item) throws Exception;
}