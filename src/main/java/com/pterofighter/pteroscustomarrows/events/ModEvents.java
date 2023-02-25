package com.pterofighter.pteroscustomarrows.events;

import com.pterofighter.pteroscustomarrows.PterosCustomArrowsMod;
import com.pterofighter.pteroscustomarrows.entity.custom.ShortLivedArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PterosCustomArrowsMod.MOD_ID)
public class ModEvents
{
    @SubscribeEvent
    public static void disableIFramesWhenHurtByShortLivedArrow(LivingHurtEvent event)
    {
        if(!event.getEntity().level.isClientSide)
        {

            if(event.getSource().getDirectEntity() instanceof ShortLivedArrowEntity)
            {
                LivingEntity ent = event.getEntity();
                ent.invulnerableTime = 0;
            }

        }
    }
}
