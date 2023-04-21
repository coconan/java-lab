package me.coconan.reflection;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpResponse;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    @Test
    public void test_invoke_static_method() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String responseBody = "{\"data\":[\"127.0.0.1\"],\"msg\":\"success\",\"ret\":0}";
        ClassicHttpResponse response = new BasicClassicHttpResponse(200, "");
        response.setEntity(new ByteArrayEntity(responseBody.getBytes(), ContentType.APPLICATION_JSON));
        Method method = CloseableHttpResponse.class.getDeclaredMethod("adapt", ClassicHttpResponse.class);
        method.setAccessible(true);
        Object result = method.invoke(null, response);
        System.out.println(result);
    }
}
