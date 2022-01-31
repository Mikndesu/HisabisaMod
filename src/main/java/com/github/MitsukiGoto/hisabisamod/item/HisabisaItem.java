package com.github.MitsukiGoto.hisabisamod.item;

import com.github.MitsukiGoto.hisabisamod.init.ItemGroupInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HisabisaItem extends Item {

    public HisabisaItem() {
        super(new Properties().tab(ItemGroupInit.HISABISA_MOD));
    }

    @Override
    public void onUseTick(World world, LivingEntity livingEntity, ItemStack itemStack, int p_219972_4_) {
    }
}
