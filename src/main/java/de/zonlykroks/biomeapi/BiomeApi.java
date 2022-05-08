package de.zonlykroks.biomeapi;

import de.zonlykroks.biomeapi.api.BiomeParameters;
import de.zonlykroks.biomeapi.api.MaterialRuleInitializer;
import de.zonlykroks.biomeapi.util.NoiseUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.function.Consumer;

public class BiomeApi implements ModInitializer, MaterialRuleInitializer {

    public static final RegistryKey<Biome> key = RegistryKey.of(Registry.BIOME_KEY, new Identifier("test_mod", "biome2"));

    @Override
    public void onInitialize() {
        BiomeParameters.registerOverworld("testmod:single_biome", 1, (builder) -> {
            builder.addBiome(0.0f, 0f, 0.0f, 0f, 0f, 0.0f, key);
        });

        BiomeParameters.registerNether("testmod:single_biome_nether", 1, (builder) -> {
            builder.addBiome(0.0f, 0f, 0.0f, 0f, 0f, 0.0f, key);
        });

        Registry.register(BuiltinRegistries.BIOME, key, OverworldBiomeCreator.createFrozenOcean(true));
    }

    @Override
    public void addOverworldRules(boolean surface, boolean bedrockRoof, boolean bedrockFloor, Consumer<MaterialRules.MaterialRule> consumer) {
        consumer.accept(
                MaterialRules.condition(
                        MaterialRules.biome(key),
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR,
                                MaterialRules.condition(
                                        MaterialRules.surface(),
                                        MaterialRules.block(Blocks.CYAN_WOOL.getDefaultState())
                                )
                        )
                )
        );
        System.err.println("HALLO");
    }

    @Override
    public void addNetherRules(Consumer<MaterialRules.MaterialRule> consumer) {
        consumer.accept(
                MaterialRules.condition(
                        MaterialRules.biome(key),
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)),
                                        MaterialRules.block(Blocks.BEDROCK.getDefaultState())
                                ),
                                MaterialRules.condition(
                                        MaterialRules.not(
                                                MaterialRules.verticalGradient("bedrock_roof", YOffset.belowTop(5), YOffset.getTop())
                                        ), MaterialRules.block(Blocks.BEDROCK.getDefaultState())
                                ),
                                MaterialRules.block(Blocks.DIAMOND_BLOCK.getDefaultState())
                        )
                )
        );
        System.err.println("HALLO");
    }
}
