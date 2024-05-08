package com.cinema.application.helpers;

import com.cinema.application.errors.ServerError;

public class ResponseFactory {
    public static <T> Response<T> ok(T data) {
        return new Response<>(200, data);
    }

    public static <T> Response<T> noContent() {
        return new Response<>(204, null);
    }

    public static Response<Exception> badRequest(Exception error) {
        return new Response<>(400, error);
    }

    public static Response<Exception> unauthorized(Exception error) {
        return new Response<>(401, error);
    }

    public static Response<ServerError> serverError(Exception error) {
        return new Response<>(500, new ServerError(error));
    }
}
