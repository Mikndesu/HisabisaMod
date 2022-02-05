package com.github.MitsukiGoto.hisabisamod;

import com.github.MitsukiGoto.hisabisamod.config.HisabisaConfig;
import com.github.MitsukiGoto.hisabisamod.init.BiomeInit;
import com.github.MitsukiGoto.hisabisamod.init.BlockInit;
import com.github.MitsukiGoto.hisabisamod.init.ItemInit;
import com.github.MitsukiGoto.hisabisamod.init.StructureInit;
import com.github.MitsukiGoto.hisabisamod.world.structure.HisabisaConfiguredStructure;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HisabisaMod.MODID)
public class HisabisaMod {

    public static final String MODID = "hisabisamod";
    public static final String HISABISA_STRUCTURE_NAME = "hisabisa_structure";
    public static final String HISABISA_BIOME_NAME = "hisabisa_biome";
    public static final Logger LOGGER = LogManager.getLogger("Hisabisa Mod/Main");

    public HisabisaMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HisabisaConfig.SPEC, "hisabisamod-common.toml");
        BlockInit.BLOCKS.register(bus);
        StructureInit.STRUCTURES.register(bus);
        BiomeInit.BIOMES.register(bus);
        ItemInit.ITEMS.register(bus);
        bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::biomeModification);
    }

    @SubscribeEvent
    public void onCreeperSpawns(LivingSpawnEvent.SpecialSpawn evt) {
        if (evt.getEntity() instanceof CreeperEntity) {
            CreeperEntity creeperEntity = (CreeperEntity) evt.getEntity();
            creeperEntity.getEntityData().set(CreeperEntity.DATA_IS_POWERED, true);
        }
    }

    @SubscribeEvent
    public void onEntityMoved(LivingEvent.LivingUpdateEvent evt) {
        LivingEntity entity = evt.getEntityLiving();
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        ItemStack boots = entity.getItemBySlot(EquipmentSlotType.FEET);
        int isEnchantedFrostWalker = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FROST_WALKER, boots);
        if (entity.isOnGround() && isEnchantedFrostWalker == 0 && HisabisaConfig.isAlways_frosted_walk.get()) {
            BlockPos blockPos = evt.getEntity().blockPosition();
            World world = evt.getEntity().getCommandSenderWorld();
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            float f = (float) Math.min(16, 2);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            for (BlockPos blockpos : BlockPos.betweenClosed(blockPos.offset((double) (-f), -1.0D, (double) (-f)), blockPos.offset((double) f, -1.0D, (double) f))) {
                if (blockpos.closerThan(entity.position(), (double) f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = world.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir(world, blockpos$mutable)) {
                        BlockState blockstate2 = world.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(FlowingFluidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(world, blockpos) && world.isUnobstructed(blockstate, blockpos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(entity, net.minecraftforge.common.util.BlockSnapshot.create(world.dimension(), world, blockpos), net.minecraft.util.Direction.UP)) {
                            world.setBlockAndUpdate(blockpos, blockstate);
                            world.getBlockTicks().scheduleTick(blockpos, Blocks.FROSTED_ICE, MathHelper.nextInt(entity.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
        if (entity.isOnGround() && isEnchantedFrostWalker == 0 && HisabisaConfig.isAlways_frosted_walk.get()) {
            BlockPos blockPos = evt.getEntity().blockPosition();
            World world = evt.getEntity().getCommandSenderWorld();
            BlockState blockstate = BlockInit.MODDED_OBSIDIAN.get().defaultBlockState();
            float f = (float) Math.min(16, 2);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            for (BlockPos blockpos : BlockPos.betweenClosed(blockPos.offset((double) (-f), -1.0D, (double) (-f)), blockPos.offset((double) f, -1.0D, (double) f))) {
                if (blockpos.closerThan(entity.position(), (double) f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = world.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir(world, blockpos$mutable)) {
                        BlockState blockstate2 = world.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(FlowingFluidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(world, blockpos) && world.isUnobstructed(blockstate, blockpos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(entity, net.minecraftforge.common.util.BlockSnapshot.create(world.dimension(), world, blockpos), net.minecraft.util.Direction.UP)) {
                            world.setBlockAndUpdate(blockpos, blockstate);
                            world.getBlockTicks().scheduleTick(blockpos, BlockInit.MODDED_OBSIDIAN.get(), MathHelper.nextInt(entity.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent evt) {
        evt.enqueueWork(() -> {
            StructureInit.setupStructures();
            HisabisaConfiguredStructure.registerConfiguredStructures();
            BiomeInit.setupBiome();
        });
    }

    public void biomeModification(final BiomeLoadingEvent evt) {
        evt.getGeneration().getStructures().add(() -> HisabisaConfiguredStructure.CONFIGURED_HISABISA_STRUCTURE);
    }

}
