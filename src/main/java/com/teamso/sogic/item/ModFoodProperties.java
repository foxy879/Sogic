package com.teamso.sogic.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties ELECTROBERRIES = new FoodProperties.Builder().nutrition(3).saturationModifier(5).fast().
            effect(new MobEffectInstance(MobEffects.INVISIBILITY,400),1f).build();
}
