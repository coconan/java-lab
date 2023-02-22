package me.coconan.cucumber.fruit.hooks;

import io.cucumber.java.Before;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResetHooks {
    @Before
    public void deleteFruitFile() throws IOException {
        Path path = FileSystems.getDefault().getPath("fruit.json");
        if (path.toFile().exists()) {
            Files.delete(path);
        }
    }
}
