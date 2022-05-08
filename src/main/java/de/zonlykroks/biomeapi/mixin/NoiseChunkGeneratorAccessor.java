package de.zonlykroks.biomeapi.mixin;

import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseChunkGenerator.class)
public interface NoiseChunkGeneratorAccessor {

    @Accessor("noiseColumnSampler")
    void setNoiseColumnSampler(MultiNoiseUtil.MultiNoiseSampler sampler);

}
