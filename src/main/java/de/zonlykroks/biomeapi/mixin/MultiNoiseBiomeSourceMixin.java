package de.zonlykroks.biomeapi.mixin;

import de.zonlykroks.biomeapi.impl.WorldInfoImpl;
import de.zonlykroks.biomeapi.impl.duck.ExtendedMultiNoiseBiomeSource;
import de.zonlykroks.biomeapi.impl.duck.ExtendedNoiseValue;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Mixin(MultiNoiseBiomeSource.class)
public abstract class MultiNoiseBiomeSourceMixin implements ExtendedMultiNoiseBiomeSource {

    @Unique
    private WorldInfoImpl biomeApi_worldInfo = null;

    @Inject(method = "<init>(Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$Entries;Ljava/util/Optional;)V", at = @At("TAIL"))
    private void biomeApi_setType(MultiNoiseUtil.Entries biomeEntries, Optional instance, CallbackInfo ci) {
        if (instance.isPresent()) {
            var preset = instance.get();
            if (preset == MultiNoiseBiomeSource.Preset.OVERWORLD) {
                this.biomeApi_worldInfo = WorldInfoImpl.OVERWORLD;
            } else if (preset == MultiNoiseBiomeSource.Preset.NETHER) {
                this.biomeApi_worldInfo = WorldInfoImpl.NETHER;
            }
        }
    }

    @Inject(method = "addDebugInfo", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void biomeApi_catchSeed(List<String> info, BlockPos pos, MultiNoiseUtil.MultiNoiseSampler noiseSampler, CallbackInfo ci, int x, int y, int z, MultiNoiseUtil.NoiseValuePoint noiseValuePoint) {
        var decimalFormat = new DecimalFormat("##0.000");

        var id = (int) MultiNoiseUtil.method_38666(((ExtendedNoiseValue) (Object) noiseValuePoint).biomeApi_getModRegion());
        var side = MultiNoiseUtil.method_38666(((ExtendedNoiseValue) (Object) noiseValuePoint).biomeApi_getRegionSide());
        info.add("[Biome API] Reg: "
                + (this.biomeApi_worldInfo != null ? this.biomeApi_worldInfo.getById(id) : "<none>")
                + " ("
                + id
                + ") | N: "
                + decimalFormat.format(noiseSampler.sample(x, y, z))
                + " | RS: "
                + decimalFormat.format(side)
        );
    }

    @Inject(method = "withSeed", at = @At("RETURN"), cancellable = true)
    private void biomeApi_setInfo(long seed, CallbackInfoReturnable<BiomeSource> cir) {
        ((ExtendedMultiNoiseBiomeSource) cir.getReturnValue()).biomeApi_setType(this.biomeApi_worldInfo);
    }

    @Override
    public void biomeApi_setType(WorldInfoImpl dimension) {
        this.biomeApi_worldInfo = dimension;
    }

    @Override
    public WorldInfoImpl biomeApi_getType() {
        return this.biomeApi_worldInfo;
    }
}
