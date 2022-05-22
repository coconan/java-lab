package me.coconan;

import redis.clients.jedis.Jedis;

public class JedisExample {

    public static void main(String[] args) {
        //Connecting to Redis on localhost
        Jedis jedis = new Jedis("localhost");
        //adding a new key
        jedis.set("key", "value");
        //getting the key value
        System.out.println(jedis.get("key"));
    }
}
