package com.pterofighter.pteroscustomarrows.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ArrowsConfig
{
    //arrow rain arrow values
    public static ForgeConfigSpec.IntValue arrowRainArrowsFired;
    public static ForgeConfigSpec.BooleanValue arrowRainScaleWithFire;
    public static ForgeConfigSpec.IntValue arrowRainDuration;
    public static ForgeConfigSpec.BooleanValue arrowRainScaleWithPower;
    public static ForgeConfigSpec.IntValue arrowRainHeight;

    //black hole arrow values
    public static ForgeConfigSpec.IntValue blackHoleDuration;
    public static ForgeConfigSpec.DoubleValue blackHoleSuckRange;
    public static ForgeConfigSpec.DoubleValue blackHoleSuckStrength;
    public static ForgeConfigSpec.IntValue blackHoleSuckInterval;

    //explosive arrow values
    public static ForgeConfigSpec.DoubleValue explosionArrowRadius;
    public static ForgeConfigSpec.IntValue explosionArrowGroundExplodeDelay;
    public static ForgeConfigSpec.BooleanValue explosionArrowBreakBlocks;
    public static ForgeConfigSpec.BooleanValue explosionArrowScaleWithFlame;

    //Heavy arrow values
    public static ForgeConfigSpec.DoubleValue heavyArrowBaseDamage;

    //Instakill arrow values
    public static ForgeConfigSpec.IntValue instakillArrowVelocity;
    public static ForgeConfigSpec.DoubleValue instakillArrowBaseDamage;
    public static ForgeConfigSpec.BooleanValue instakillArrowBreakBlocks;
    public static ForgeConfigSpec.IntValue instakillArrowPierceLevel;
    public static ForgeConfigSpec.BooleanValue instakillArrowIsNoGravity;

    //Poison Gas arrow values
    public static ForgeConfigSpec.IntValue poisonGasArrowDuration;
    public static ForgeConfigSpec.DoubleValue poisonGasArrowRadius;
    public static ForgeConfigSpec.IntValue poisonGasArrowWaitTime;
    public static ForgeConfigSpec.IntValue poisonGasArrowPoisonDuration;
    public static ForgeConfigSpec.IntValue poisonGasArrowPoisonLevel;
    public static ForgeConfigSpec.IntValue poisonGasArrowHarmLevel;

    //Scatter Arrow values
    public static ForgeConfigSpec.IntValue scatterArrowArrowsFired;
    public static ForgeConfigSpec.IntValue scatterArrowInaccuracy;
    public static ForgeConfigSpec.DoubleValue scatterArrowBaseDamage;
    public static ForgeConfigSpec.BooleanValue scatterArrowScaleWithPower;
    public static ForgeConfigSpec.BooleanValue scatterArrowScaleWithFlame;
    public static ForgeConfigSpec.BooleanValue scatterArrowScaleWithPunch;

    //Seeker Arrow values
    public static ForgeConfigSpec.IntValue seekerArrowDespawnTime;
    public static ForgeConfigSpec.DoubleValue seekerArrowTargetingRange;
    public static ForgeConfigSpec.IntValue seekerArrowTargetingWaitTime;
    public static ForgeConfigSpec.DoubleValue seekerArrowTargetingDamageBonus;
    public static ForgeConfigSpec.BooleanValue seekerArrowArrowsBounce;





    public static void init(ForgeConfigSpec.Builder config)
    {
        config.comment("Arrow Rain Config");

        arrowRainArrowsFired = config.comment("Number of arrows fired per tick in arrow rain")
                .defineInRange("arrowRainArrowsFired", 7,1,100);

        arrowRainScaleWithFire = config.comment("Makes the arrow rain scale with the flame enchantment")
                .define(".arrowRainScaleWithFire", true);

        arrowRainScaleWithPower = config.comment("Makes the arrow rain scale with power enchantment")
                .define("arrowRainScaleWithPower", true);

        arrowRainDuration = config.comment("How man ticks arrow Rain will last")
                .defineInRange("arrowRainDuration", 600,1,100000);

        arrowRainHeight = config.comment("How many blocks above from where the arrow landed will the arrows come from")
                .defineInRange("arrowRainHeight", 69,1,1000);

        config.comment("Black Hole Arrow Config");

        blackHoleDuration = config.comment("How man ticks the sucking effect will last for black hole arrow")
                .defineInRange("blackHoleDuration", 300,1,100000);

        blackHoleSuckRange = config.comment("how many block away entities will be pulled to the black hole arrow ")
                .defineInRange("blackHoleSuckRange", 8.0,0,1000);

        blackHoleSuckStrength = config.comment("how strong the blackhole arrow pull strength will be ")
                .defineInRange("blackHoleSuckStrength", 0.3,0,1000);

        blackHoleSuckInterval = config.comment("how many ticks in between the black hole arrow will pull an entity")
                .defineInRange("blackHoleDuration", 3,0,10000);

        config.comment("Explosive Arrow Config");

        explosionArrowBreakBlocks = config.comment("determines if explosion arrow will break blocks ")
                .define("explosiveArrowBreakBlocks", true);

        explosionArrowRadius = config.comment("how big explosion arrow explosion radius will be")
                .defineInRange("explosionArrowRadius", 4.0,0,100);

        explosionArrowGroundExplodeDelay = config.comment("how many ticks will it take for explosion arrow to detonate once it touches the ground")
                .defineInRange("explosionArrowGroundExplodeDelay", 60,0,100000);

        explosionArrowScaleWithFlame = config.comment("determines if explosion arrow, explosion will cause flame if fired by flame bow")
                .define("explosionArrowScaleWithFlame", true);

        config.comment("Heavy Arrow Config");

        heavyArrowBaseDamage = config.comment("Determines the base damage of the heavy arrow")
                .defineInRange("heavyArrowBaseDamage", 3.0D,0,100000);

        config.comment("InstaKill Arrow Config");

        instakillArrowVelocity = config.comment("Determines the velocity of the instakill arrow")
                .defineInRange("instakillArrowVelocity", 1000,0,100000);

        instakillArrowBaseDamage = config.comment("Determines the base damage of the instakill arrow")
                .defineInRange("instakillArrowBaseDamage", 500.0D,0,100000);

        instakillArrowPierceLevel = config.comment("Determines the pierce level of the instakill arrow")
                .defineInRange("instakillArrowPierceLevel", 10,0,100);

        instakillArrowBreakBlocks = config.comment("Determines if the instakill arrow will break the block it hits")
                .define("instakillArrowBreakBlocks", true);

        instakillArrowIsNoGravity = config.comment("Determines if the instakill arrow will fly straight")
                .define("instakillArrowIsNoGravity", true);

        config.comment("Poison Gas Arrow Config");

        poisonGasArrowDuration = config.comment("Determines how many ticks the effect cloud will last")
                .defineInRange("poisonGasArrowDuration", 600,0,100000);

        poisonGasArrowRadius = config.comment("Determines how large the radius of the effect cloud will be")
                .defineInRange("poisonGasArrowRadius", 10.0D,0,100);

        poisonGasArrowWaitTime = config.comment("Determines how many ticks until the effect cloud will take effect after effect cloud has spawned")
                .defineInRange("poisonGasArrowWaitTime", 30,0,100000);

        poisonGasArrowPoisonDuration = config.comment("Determines how long the poison effect will last after leaving the cloud")
                .defineInRange("poisonGasArrowPoisonDuration", 300,0,100000);

        poisonGasArrowPoisonLevel = config.comment("Determines what level the poison will be")
                .defineInRange("poisonGasArrowPoisonLevel", 0,0,10);

        poisonGasArrowHarmLevel = config.comment("Determines what level the harm will be")
                .defineInRange("poisonGasArrowHarmLevel", 0,0,10);

        config.comment("Scatter Arrow Config");

        scatterArrowArrowsFired = config.comment("Determines how many arrows will be fired by scatter arrow")
                .defineInRange("scatterArrowArrowsFired", 13,1,100);

        scatterArrowInaccuracy = config.comment("Determines how much spread there will be when firing the scatter arrow")
                .defineInRange("scatterArrowInaccuracy", 10,1,100);

        scatterArrowBaseDamage = config.comment("Determines the base damage of each arrow fired by scatter arrow")
                .defineInRange("scatterArrowBaseDamage", 2.0D,0,100000);

        scatterArrowScaleWithPower = config.comment("Determines if scatter arrow scales with power enchantment")
                .define("scatterArrowScaleWithPower", true);

        scatterArrowScaleWithFlame = config.comment("Determines if scatter arrow scales with flame enchantment")
                .define("scatterArrowScaleWithFlame", true);

        scatterArrowScaleWithPunch = config.comment("Determines if scatter arrow scales with punch enchantment")
                .define("scatterArrowScaleWithPunch", true);

        config.comment("Seeker Arrow Config");

        seekerArrowTargetingRange = config.comment("Determines the block range in which the seeker arrow will seek entities")
                .defineInRange("seekerArrowTargetingRange", 100.0D,0,100000);

        seekerArrowDespawnTime = config.comment("Determines how many ticks seeker arrows will stay in the world before despawning")
                .defineInRange("seekerArrowDespawnTime", 600,0,100000);

        seekerArrowTargetingWaitTime = config.comment("Determines how many ticks seeker arrows will wait to seek after being fired")
                .defineInRange("seekerArrowDespawnTime", 10,0,100000);

        seekerArrowTargetingDamageBonus = config.comment("Determines the damage bonus seeker arrows get when they started seeking entities")
                .defineInRange("seekerArrowTargetingDamageBonus", 4.0D,0,100000);

        seekerArrowArrowsBounce = config.comment("Determines if seeker arrows will bounce off of blocks instead of sticking into them")
                .define("seekerArrowArrowsBounce", true);



    }
}
