package de.zonlykroks.biomeapi.mixin;

import de.zonlykroks.biomeapi.impl.duck.ExtendedNoiseValue;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spongepowered.asm.mixin.*;

@Mixin(MultiNoiseUtil.NoiseValuePoint.class)
public class NoiseValuePointMixin implements ExtendedNoiseValue {

    @Shadow
    public long temperatureNoise;

    @Shadow
    public long humidityNoise;

    @Shadow
    public long continentalnessNoise;

    @Shadow
    public long erosionNoise;

    @Shadow
    public long depth;

    @Shadow
    public long weirdnessNoise;

    @Unique
    private long biomeApi_modRegion;

    @Unique
    private long biomeApi_regionSide;

    @Override
    public long biomeApi_getModRegion() {
        return this.biomeApi_modRegion;
    }

    @Override
    public long biomeApi_getRegionSide() {
        return this.biomeApi_regionSide;
    }

    @Override
    public MultiNoiseUtil.NoiseValuePoint biomeApi_setModNoise(long modRegion, long regionSide) {
        this.biomeApi_modRegion = modRegion;
        this.biomeApi_regionSide = regionSide;
        return (MultiNoiseUtil.NoiseValuePoint) (Object) this;
    }

    /**
     * @author Patbox
     * @reason Performance and allowing to add more biomes
     */
    @Overwrite
    public long[] getNoiseValueList() {
        return new long[]{this.temperatureNoise, this.humidityNoise, this.continentalnessNoise, this.erosionNoise, this.depth, this.weirdnessNoise, 0L, this.biomeApi_modRegion, this.biomeApi_regionSide};
    }
}
