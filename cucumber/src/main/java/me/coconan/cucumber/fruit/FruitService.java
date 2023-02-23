/*
 * Copyright (c) 2010, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package me.coconan.cucumber.fruit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakub Podlesak
 */
@Path("/")
public class FruitService {
    @GET
    @Path("/fruits")
    @Produces(MediaType.APPLICATION_JSON)
    public Fruit[] getAllFruits() {
        List<Fruit> fruits = getFruitFromFile();

        return fruits.toArray(new Fruit[]{});
    }

    private List<Fruit> getFruitFromFile() {
        String fruitJson = readJsonFruitFile();

        return buildListFromJson(fruitJson);
    }

    private String readJsonFruitFile() {
        try {
            return new String(Files.readAllBytes(Paths.get("fruit.json")));
        } catch (IOException e) {
            return "[]";
        }
    }

    private List<Fruit> buildListFromJson(String fruitJson) {
        final TypeToken<List<Fruit>> token = new TypeToken<>(){};
        final Type type = token.getType();
        final Gson gson = new Gson();

        return gson.fromJson(fruitJson, type);
    }
}