package me.coconan;

import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.ExecutionException;


public class LettuceExample {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("key", "Hello, Redis!");
        System.out.println(syncCommands.get("key"));

        RedisAsyncCommands<String, String> asyncCommands = connection.async();
        RedisFuture<String> future = asyncCommands.get("key");
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        connection.close();
        redisClient.shutdown();
    }
}
