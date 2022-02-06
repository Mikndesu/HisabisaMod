package com.github.MitsukiGoto.hisabisamod.init;

import com.github.MitsukiGoto.hisabisamod.item.HisabisaItem;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupInit {

    public static final ItemGroup HISABISA_MOD = (new ItemGroup("hisabisa") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.HISABISA_ITEM.get());
        }
    });
    
}
