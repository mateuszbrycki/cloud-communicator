package com.cloud.communicator.translator;


import com.cloud.communicator.translator.context.AbstractContext;

public class Translator<T> implements AbstractTranslator<T> {

    @Override
    public T translate(AbstractContext<T> context) {
       return context.createFromContext();
    }
}
