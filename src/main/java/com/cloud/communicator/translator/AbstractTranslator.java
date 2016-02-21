package com.cloud.communicator.translator;

import com.cloud.communicator.translator.context.AbstractContext;

public interface AbstractTranslator<T> {
    T translate(AbstractContext<T> context);
}
