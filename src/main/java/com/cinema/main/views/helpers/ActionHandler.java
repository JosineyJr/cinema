package com.cinema.main.views.helpers;

@FunctionalInterface
public interface ActionHandler<T> {
  void handle(T item);
}
