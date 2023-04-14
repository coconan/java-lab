package me.coconan.reflection;

import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandKeyword;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        CommandArgs commandArgs = new CommandArgs<>(new StringCodec())
                .addKey("test_key")
                .addValue("test_value")
                .add(CommandKeyword.FORCE);
        Field field = commandArgs.getClass().getDeclaredField("singularArguments");
        field.setAccessible(true);
        ArrayList<Object> argList = (ArrayList<Object>) field.get(commandArgs);
        argList.stream().peek(System.out::println).collect(Collectors.toList());
    }
}
