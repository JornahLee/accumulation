package com.jornah.springmvcdemo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

@JsonComponent
public class InstantSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        System.out.println("--licg---     value : " + value + "    -----");
        int fractionalDigits = 0;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendInstant(fractionalDigits)
                .toFormatter();
        String format = formatter.format(value);
        gen.writeString(format);
    }
}
