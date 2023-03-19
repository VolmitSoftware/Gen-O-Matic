package com.volmit.genomatic.generators;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import art.arcane.source.noise.provider.SimplexProvider;

import java.util.Random;

public class CustomBiomePopulator extends BlockPopulator {
    private final SimplexProvider biomeProvider;

    public CustomBiomePopulator(long seed) {
        biomeProvider = new SimplexProvider(seed + 1);
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        double biomeScale = 0.005;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunk.getX() * 16 + x;
                int worldZ = chunk.getZ() * 16 + z;
                double biomeValue = biomeProvider.noise(worldX * biomeScale, worldZ * biomeScale);
                Biome biome = biomeValue > 0 ? Biome.SWAMP : Biome.DESERT;

                for (int y = 0; y < world.getMaxHeight(); y++) {
                    world.setBiome(worldX, y, worldZ, biome);
                }
            }
        }
    }
}
