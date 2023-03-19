package com.volmit.genomatic.generators;

import com.volmit.genomatic.generators.CustomBiomePopulator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import art.arcane.source.noise.provider.SimplexProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {
    private final long seed;

    public CustomChunkGenerator(long seed) {
        this.seed = seed;
    }

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        double scale = 0.05;
        int seaLevel = 63;

        SimplexProvider simplexProvider = new SimplexProvider(worldInfo.getSeed());

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;
                double noiseValue = simplexProvider.noise(worldX * scale, worldZ * scale);

                noiseValue = (noiseValue + 1) / 2; // Normalize
                int terrainHeight = (int) (noiseValue * 64) + seaLevel;

                for (int y = chunkData.getMinHeight(); y <= terrainHeight && y < chunkData.getMaxHeight(); y++) {
                    if (y == terrainHeight) {
                        chunkData.setBlock(x, y, z, Material.GRASS_BLOCK);
                    } else if (y > terrainHeight - 4) {
                        chunkData.setBlock(x, y, z, Material.DIRT);
                    } else {
                        if (y == chunkData.getMinHeight()) {
                            chunkData.setBlock(x, y, z, Material.BEDROCK);
                        }
                        chunkData.setBlock(x, y, z, Material.STONE);
                    }
                }
            }
        }
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(new CustomBiomePopulator(seed));
    }
}
