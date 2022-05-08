package de.zonlykroks.biomeapi.api;

import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import java.util.function.Consumer;

public interface MaterialRuleInitializer {

    default void addOverworldRules(boolean surface, boolean bedrockRoof, boolean bedrockFloor, Consumer<MaterialRules.MaterialRule> consumer) {};
    default void addNetherRules(Consumer<MaterialRules.MaterialRule> consumer) {};
    default void addEndRules(Consumer<MaterialRules.MaterialRule> consumer) {};

}
