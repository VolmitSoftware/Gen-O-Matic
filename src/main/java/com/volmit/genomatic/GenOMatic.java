package com.volmit.genomatic;

import com.volmit.genomatic.generators.CustomChunkGenerator;
import com.volmit.genomatic.util.WorldManagement;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class GenOMatic extends JavaPlugin {

    @Override
    public void onEnable() {
        info("GenOMatic is starting up!");

    }


    @Override
    public void onDisable() {
        info("GenOMatic is shutting down!");
        WorldManagement.deleteWorld("world");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        info("GenOMatic is generating a world with the name: " + worldName + " and the id: " + id + " using the CustomChunkGenerator!");
        return new CustomChunkGenerator(69420); // Return an instance of the chunk generator we want to use.
    }


    private static void log(String tag, Object t) {
        System.out.println("[" + "GenOMatic-" + tag + "]-> " + t);
    }
    public static void warn(Object message) {
        log("Warning", message);
    }
    public static void info(Object message) {
        log("Info", message);
    }
    public static void error(Object message) {
        log("ERROR", message);
    }
    public static void debug(Object message) {
        log("Debug", message);
    }
}
