package de.zonlykroks.biomeapi.impl.duck;

import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public interface ExtendedNoiseValue {

    long biomeApi_getModRegion();
    long biomeApi_getRegionSide();
    MultiNoiseUtil.NoiseValuePoint biomeApi_setModNoise(long modRegion, long regionSide);

}
