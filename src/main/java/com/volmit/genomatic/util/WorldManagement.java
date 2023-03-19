package com.volmit.genomatic.util;

import org.bukkit.Bukkit;

public class WorldManagement {
    public static void deleteWorld(String worldName) {
        Bukkit.getServer().unloadWorld(worldName, false);
        Bukkit.getServer().getWorld(worldName).getWorldFolder().delete();
    }
}
