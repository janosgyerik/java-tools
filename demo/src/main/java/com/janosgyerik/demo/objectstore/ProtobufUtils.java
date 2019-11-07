package com.janosgyerik.demo.objectstore;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.janosgyerik.tools.objectstore.api.StoreReadException;
import com.janosgyerik.tools.objectstore.api.StoreWriteException;
import com.janosgyerik.tools.objectstore.impl.Reader;
import com.janosgyerik.tools.objectstore.impl.Writer;

import java.io.IOException;

public class ProtobufUtils {
    private ProtobufUtils() {
        // utility class, forbidden constructor
    }

    public static <T extends Message> Reader<T> reader(Parser<T> parser) {
        return inputStream -> {
            try {
                return parser.parseFrom(inputStream);
            } catch (InvalidProtocolBufferException e) {
                throw new StoreReadException(e);
            }
        };
    }

    public static <T extends Message> Writer<T> writer() {
        return (out, value) -> {
            try {
                value.writeTo(out);
            } catch (IOException e) {
                throw new StoreWriteException(e);
            }
        };
    }
}
