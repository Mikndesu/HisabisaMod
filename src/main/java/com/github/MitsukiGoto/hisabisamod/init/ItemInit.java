package com.github.MitsukiGoto.hisabisamod.init;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.item.HisabisaItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            HisabisaMod.MODID);
    public static final RegistryObject<Item> HISABISA_ITEM = ITEMS.register("hisabisa_item", HisabisaItem::new);
    public static final RegistryObject<Item> MODDED_OBSIDIAN = ITEMS.register("modded_obsidian", () -> new BlockItem(BlockInit.MODDED_OBSIDIAN.get(), new Properties().tab(ItemGroupInit.HISABISA_MOD)));
    public static final RegistryObject<Item> MODDED_CRYING_OBSIDIAN = ITEMS.register("modded_crying_obsidian", () -> new BlockItem(BlockInit.MODDED_CRYING_OBSIDIAN.get(), new Properties().tab(ItemGroupInit.HISABISA_MOD)));
}
