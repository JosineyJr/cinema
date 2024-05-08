package com.cinema.application.helpers;

public class Response<T> {
  private int statusCode;
  private T data;

  public Response(int statusCode, T data) {
    this.statusCode = statusCode;
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
