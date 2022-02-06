package com.github.MitsukiGoto.hisabisamod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HisabisaConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isAlways_frosted_walk;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isAlways_lava_walk;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isLava_walk_exclusive_with_frosted_walk;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isSpawnedCreeper_should_be_charged;

    static {
        BUILDER.push("Config for HisabisaMod");
        isAlways_frosted_walk = BUILDER.comment("This defines whether you can frosted_walk without enchant or not.").define("isAlways_frosted_walk", false);
        isAlways_lava_walk = BUILDER.comment("This defines whether you can frosted_walk without enchant or not.").define("isAlways_lava_walk", false);
        isLava_walk_exclusive_with_frosted_walk =  BUILDER.comment("This defines whether Lava walker exclusive with frosted walker").define("isLava_walk_exclusive_with_frosted_walk", true);
        isSpawnedCreeper_should_be_charged = BUILDER.comment("This defines whether Creeper is charged as default").define("isLava_walk_exclusive_with_frosted_walk", true);
        BUILDER.pop();
        SPEC=BUILDER.build();
    }
}
