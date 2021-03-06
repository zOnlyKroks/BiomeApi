package de.zonlykroks.biomeapi.api;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public interface BiomeNoiseBuilder {

    void addBiome(MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange depth, MultiNoiseUtil.ParameterRange weirdness, MultiNoiseUtil.ParameterRange regionSide, float offset, RegistryKey<Biome> biome);

    default void addBiome(MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange depth, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome) {
        addBiome(temperature, humidity, continentalness, erosion, depth, weirdness, MultiNoiseUtil.ParameterRange.of(0), offset, biome);
    }

    default void addBiome(MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange depth, MultiNoiseUtil.ParameterRange weirdness, RegistryKey<Biome> biome) {
        addBiome(temperature, humidity, continentalness, erosion, depth, weirdness, 0f, biome);
    }

    void addBiome(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, float regionSide, float offset, RegistryKey<Biome> biome);

    default void addBiome(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, float offset, RegistryKey<Biome> biome) {
        addBiome(temperature, humidity, continentalness, erosion, depth, weirdness, 0f, offset, biome);
    }

    default void addBiome(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, RegistryKey<Biome> biome) {
        addBiome(temperature, humidity, continentalness, erosion, depth, weirdness, 0, biome);
    }

    /**
     * Clones generation settings from vanilla biome
     *
     * @param vanillaBiome
     * @param biome
     */
    void addSimilar(RegistryKey<Biome> vanillaBiome, RegistryKey<Biome> biome);

    void fillVanilla();

}
