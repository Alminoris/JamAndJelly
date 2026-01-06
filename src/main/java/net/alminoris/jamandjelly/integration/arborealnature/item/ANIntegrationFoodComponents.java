package net.alminoris.jamandjelly.integration.arborealnature.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Dictionary;
import java.util.Hashtable;

public class ANIntegrationFoodComponents
{
    static FoodComponent QUINCE_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 0.2f).build();

    static FoodComponent PLUM_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 0.2f).build();

    static FoodComponent MANGO_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 1), 0.3f).build();

    static FoodComponent FIGS_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 150, 0), 0.25f).build();

    static FoodComponent VIBURNUM_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 120, 0), 0.2f).build();

    static FoodComponent WHITE_MULBERRY_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 200, 0), 0.2f).build();

    static FoodComponent WILD_CHERRY_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 150, 1), 0.2f).build();

    static FoodComponent BILBERRY_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0), 0.3f).build();

    static FoodComponent BLACKBERRY_JAM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 0.2f).build();

    static FoodComponent PINK_CURRANT_JAM = new FoodComponent.Builder()
            .nutrition(4).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 100, 0), 0.2f).build();

    static FoodComponent QUINCE_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.6f)
            .build();

    static FoodComponent PLUM_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.6f)
            .build();

    static FoodComponent MANGO_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.8f)
            .build();

    static FoodComponent FIGS_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.7f)
            .build();

    static FoodComponent VIBURNUM_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.5f)
            .build();

    static FoodComponent WHITE_MULBERRY_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.6f)
            .build();

    static FoodComponent WILD_CHERRY_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.7f)
            .build();

    static FoodComponent BILBERRY_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.8f)
            .build();

    static FoodComponent BLACKBERRY_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.6f)
            .build();

    static FoodComponent PINK_CURRANT_JUICE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.7f)
            .build();


    public static final Dictionary<String, FoodComponent> AN_JAM_FOOD_COMPONENTS = new Hashtable<>();
    public static final Dictionary<String, FoodComponent> AN_JUICE_FOOD_COMPONENTS = new Hashtable<>();

    static
    {
        AN_JAM_FOOD_COMPONENTS.put("quince", QUINCE_JAM);
        AN_JAM_FOOD_COMPONENTS.put("plum", PLUM_JAM);
        AN_JAM_FOOD_COMPONENTS.put("mango", MANGO_JAM);
        AN_JAM_FOOD_COMPONENTS.put("figs", FIGS_JAM);
        AN_JAM_FOOD_COMPONENTS.put("viburnum", VIBURNUM_JAM);
        AN_JAM_FOOD_COMPONENTS.put("white_mulberry", WHITE_MULBERRY_JAM);
        AN_JAM_FOOD_COMPONENTS.put("wild_cherry", WILD_CHERRY_JAM);
        AN_JAM_FOOD_COMPONENTS.put("bilberry", BILBERRY_JAM);
        AN_JAM_FOOD_COMPONENTS.put("blackberry", BLACKBERRY_JAM);
        AN_JAM_FOOD_COMPONENTS.put("pink_currant", PINK_CURRANT_JAM);

        AN_JUICE_FOOD_COMPONENTS.put("quince", QUINCE_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("plum", PLUM_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("mango", MANGO_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("figs", FIGS_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("viburnum", VIBURNUM_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("white_mulberry", WHITE_MULBERRY_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("wild_cherry", WILD_CHERRY_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("bilberry", BILBERRY_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("blackberry", BLACKBERRY_JUICE);
        AN_JUICE_FOOD_COMPONENTS.put("pink_currant", PINK_CURRANT_JUICE);
    }
}
