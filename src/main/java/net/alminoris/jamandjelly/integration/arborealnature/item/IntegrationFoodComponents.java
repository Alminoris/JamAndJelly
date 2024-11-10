package net.alminoris.jamandjelly.integration.arborealnature.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Dictionary;
import java.util.Hashtable;

public class IntegrationFoodComponents
{
    static FoodComponent QUINCE_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 0.2f).build();

    static FoodComponent PLUM_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 0.2f).build();

    static FoodComponent MANGO_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 1), 0.3f).build();

    static FoodComponent FIGS_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 150, 0), 0.25f).build();


    public static final Dictionary<String, FoodComponent> JAM_FOOD_COMPONENTS = new Hashtable<>();

    static
    {
        JAM_FOOD_COMPONENTS.put("quince", QUINCE_JAM);
        JAM_FOOD_COMPONENTS.put("plum", PLUM_JAM);
        JAM_FOOD_COMPONENTS.put("mango", MANGO_JAM);
        JAM_FOOD_COMPONENTS.put("figs", FIGS_JAM);
    }
}
