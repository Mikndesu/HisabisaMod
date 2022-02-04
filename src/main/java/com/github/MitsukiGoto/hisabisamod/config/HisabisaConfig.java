package com.github.MitsukiGoto.hisabisamod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HisabisaConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isAlways_frosted_walk;

    static {
        BUILDER.push("Config for HisabisaMod");
        isAlways_frosted_walk = BUILDER.comment("This defines whether you can frosted_walk without enchant or not.").define("isAlways_frosted_walk", true);
        BUILDER.pop();
        SPEC=BUILDER.build();
    }
}
