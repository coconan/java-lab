package me.coconan.io.inputstream;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class InputStreamTest {
    @Test
    public void test_string_to_input_stream() throws IOException {
        String source = "text";
        InputStream targetStream = new ByteArrayInputStream(source.getBytes());
        assertEquals(source, new String(targetStream.readAllBytes()));
    }

    @Test
    public void test_input_stream_to_string() {
        String source = "text";
        InputStream inputStream = new ByteArrayInputStream(source.getBytes());

        String text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        assertThat(text, equalTo(source));
    }
}
