package com.djcps.flink.common.schema;

import com.djcps.flink.common.model.UserEvent;
import com.google.gson.Gson;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * User Schema ，支持序列化和反序列化
 */
public class UserSchema implements DeserializationSchema<UserEvent>, SerializationSchema<UserEvent> {

    private static final Gson gson = new Gson();

    @Override
    public UserEvent deserialize(byte[] bytes) throws IOException {
        return gson.fromJson(new String(bytes), UserEvent.class);
    }

    @Override
    public boolean isEndOfStream(UserEvent userEvent) {
        return false;
    }

    @Override
    public byte[] serialize(UserEvent userEvent) {
        return gson.toJson(userEvent).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public TypeInformation<UserEvent> getProducedType() {
        return TypeInformation.of(UserEvent.class);
    }
}
