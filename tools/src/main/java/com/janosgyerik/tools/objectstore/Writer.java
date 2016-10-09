package com.janosgyerik.tools.objectstore;

import java.io.IOException;
import java.io.OutputStream;

public interface Writer<V> {
    void writeTo(OutputStream out, V value) throws IOException;
}
