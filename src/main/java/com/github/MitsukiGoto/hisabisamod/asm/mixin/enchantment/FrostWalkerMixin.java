package com.github.MitsukiGoto.hisabisamod.asm.mixin.enchantment;

import com.github.MitsukiGoto.hisabisamod.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerMixin extends Enchantment {
    protected FrostWalkerMixin(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
    }
    @Inject(method = "checkCompatibility(Lnet/minecraft/enchantment/Enchantment;)Z", at=@At("RETURN"))
    private void inject(Enchantment p_77326_1_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.checkCompatibility(p_77326_1_) && p_77326_1_ != Enchantments.DEPTH_STRIDER && p_77326_1_ != EnchantmentInit.LAVA_WALKER.get());
    }
}
