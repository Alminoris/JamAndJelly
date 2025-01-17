package net.alminoris.jamandjelly.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;

public class ModFoodComponents
{
    public static final FoodComponent APPLE_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 240, 1), 0.25f).build();

    public static final FoodComponent SWEETBERRY_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 400, 0), 0.15f).build();

    public static final FoodComponent MELON_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 300), 0.2f).build();

    public static final FoodComponent JELLY = new FoodComponent.Builder().nutrition(1).saturationModifier(0.6F).usingConvertsTo(Items.BOWL).build();

    public static final FoodComponent APPLE_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.7f)
            .build();

    public static final FoodComponent SWEETBERRY_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.5f)
            .build();

    public static final FoodComponent MELON_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.4f)
            .build();
}
