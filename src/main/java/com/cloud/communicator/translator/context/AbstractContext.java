package com.cloud.communicator.translator.context;

public interface AbstractContext<T> {
    T createFromContext();
}
