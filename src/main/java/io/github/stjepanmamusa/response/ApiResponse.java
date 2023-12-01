package io.github.stjepanmamusa.response;

public class ApiResponse<T> {
    private T data;
    private Throwable error;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    public boolean isSuccess() {
        return data != null && error == null;
    }
}
