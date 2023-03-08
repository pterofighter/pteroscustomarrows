package com.pterofighter.pteroscustomarrows.entity;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes
{
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PterosCustomArrowsMod.MOD_ID);

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW =
            ENTITY_TYPES.register("explosive_arrow",
            () -> EntityType.Builder.of((EntityType.EntityFactory<ExplosiveArrowEntity>) ExplosiveArrowEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build("explosive_arrow"));


    public static final RegistryObject<EntityType<LightningArrowEntity>> LIGHTNING_ARROW =
            ENTITY_TYPES.register("lightning_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<LightningArrowEntity>) LightningArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("lightning_arrow"));

    public static final RegistryObject<EntityType<InstaKillArrowEntity>> INSTAKILL_ARROW =
            ENTITY_TYPES.register("instakill_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<InstaKillArrowEntity>) InstaKillArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("instakill_arrow"));

    public static final RegistryObject<EntityType<ArrowRainArrowEntity>> ARROW_RAIN_ARROW =
            ENTITY_TYPES.register("arrow_rain_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<ArrowRainArrowEntity>) ArrowRainArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("arrow_rain_arrow"));
//
    public static final RegistryObject<EntityType<ShortLivedArrowEntity>> SHORT_LIVED_ARROW =
            ENTITY_TYPES.register("short_lived_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<ShortLivedArrowEntity>) ShortLivedArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("short_lived_arrow"));

    public static final RegistryObject<EntityType<BlackHoleArrowEntity>> BLACK_HOLE_ARROW =
            ENTITY_TYPES.register("black_hole_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<BlackHoleArrowEntity>) BlackHoleArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("black_hole_arrow"));

    public static final RegistryObject<EntityType<SeekingArrowEntity>> SEEKING_ARROW =
            ENTITY_TYPES.register("seeking_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<SeekingArrowEntity>) SeekingArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("black_hole_arrow"));

    public static final RegistryObject<EntityType<TreeArrowEntity>> TREE_ARROW =
            ENTITY_TYPES.register("tree_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<TreeArrowEntity>) TreeArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("tree_arrow"));

    public static final RegistryObject<EntityType<ScatterArrowEntity>> SCATTER_ARROW =
            ENTITY_TYPES.register("scatter_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<ScatterArrowEntity>) ScatterArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("scatter_arrow"));

    public static final RegistryObject<EntityType<PoisonGasArrowEntity>> POISON_GAS_ARROW =
            ENTITY_TYPES.register("poison_gas_arrow",
                    () -> EntityType.Builder.of((EntityType.EntityFactory<PoisonGasArrowEntity>) PoisonGasArrowEntity::new,
                            MobCategory.MISC).sized(0.5F, 0.5F).build("poison_gas_arrow"));

//    public static final RegistryObject<EntityType<HeavyArrowEntity>> HEAVY_ARROW =
//            ENTITY_TYPES.register("heavy_arrow",
//                    () -> EntityType.Builder.of((EntityType.EntityFactory<HeavyArrowEntity>) HeavyArrowEntity::new,
//                            MobCategory.MISC).sized(0.5F, 0.5F).build("heavy_arrow"));
//
//    public static final RegistryObject<EntityType<DecoyArrowEntity>> DECOY_ARROW =
//            ENTITY_TYPES.register("decoy_arrow",
//                    () -> EntityType.Builder.of((EntityType.EntityFactory<DecoyArrowEntity>) DecoyArrowEntity::new,
//                            MobCategory.MISC).sized(0.5F, 0.5F).build("decoy_arrow"));
//
//    public static final RegistryObject<EntityType<EnderPearlArrowEntity>> ENDER_PEARL_ARROW =
//            ENTITY_TYPES.register("ender_pearl_arrow",
//                    () -> EntityType.Builder.of((EntityType.EntityFactory<EnderPearlArrowEntity>) EnderPearlArrowEntity::new,
//                            MobCategory.MISC).sized(0.5F, 0.5F).build("ender_pearl_arrow"));





    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
