package de.zonlykroks.biomeapi.api;

import de.zonlykroks.biomeapi.impl.WorldInfoImpl;

import java.util.function.Consumer;

public class BiomeParameters {

    public BiomeParameters() {}

    public static void registerOverworld(String regionId, int weight, Consumer<BiomeNoiseBuilder> callback) {
        WorldInfoImpl.OVERWORLD.registerNoise(regionId, weight, callback);
    }

    public static void registerNether(String regionId, int weight, Consumer<BiomeNoiseBuilder> callback) {
        WorldInfoImpl.NETHER.registerNoise(regionId, weight, callback);
    }

}
