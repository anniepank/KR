package com.anna.point;

public class EmptyCoordinatesException extends RuntimeException {

    public EmptyCoordinatesException(Throwable e) {
        initCause(e);
    }

    public EmptyCoordinatesException() {}

}
