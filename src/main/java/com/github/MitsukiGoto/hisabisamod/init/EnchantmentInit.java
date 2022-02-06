package com.github.MitsukiGoto.hisabisamod.init;

import com.github.MitsukiGoto.hisabisamod.HisabisaMod;
import com.github.MitsukiGoto.hisabisamod.enchant.LavaWalkerEnchantment;
import com.github.MitsukiGoto.hisabisamod.item.HisabisaItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
            HisabisaMod.MODID);
    public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker", ()-> new LavaWalkerEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.FEET));
}
