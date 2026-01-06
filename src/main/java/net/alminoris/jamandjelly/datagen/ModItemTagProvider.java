package net.alminoris.jamandjelly.datagen;

import net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems;
import net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;
import static net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems.WF_JAM_NAMES;


public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        getOrCreateTagBuilder(ModTags.Items.JAM_BOTTLES)
                .add(ModItems.APPLE_JAM_BOTTLE)
                .add(ModItems.SWEETBERRY_JAM_BOTTLE)
                .add(ModItems.MELON_JAM_BOTTLE)
                .add(Items.HONEY_BOTTLE);

        getOrCreateTagBuilder(ModTags.Items.JUICE_BOTTLES)
                .add(ModItems.GLASS_APPLE_JUICE_BOTTLE)
                .add(ModItems.GLASS_SWEETBERRY_JUICE_BOTTLE)
                .add(ModItems.GLASS_MELON_JUICE_BOTTLE);

        getOrCreateTagBuilder(ModTags.Items.JAM_INGREDIENTS)
                .add(ModItems.APPLE_CHOPPED)
                .add(ModItems.SWEETBERRY_CHOPPED)
                .add(ModItems.MELON_CHOPPED);

        for(String name : AN_JAM_NAMES)
        {
            getOrCreateTagBuilder(ModTags.Items.JAM_BOTTLES)
                    .add(ANIntegrationItems.AN_JAM_BOTTLES.get(name));

            getOrCreateTagBuilder(ModTags.Items.JUICE_BOTTLES)
                    .add(ANIntegrationItems.AN_JUICE_BOTTLES.get(name));

            getOrCreateTagBuilder(ModTags.Items.JAM_INGREDIENTS)
                    .add(ANIntegrationItems.AN_JAM_CHOPPED.get(name));
        }

        for(String name : WF_JAM_NAMES)
        {
            getOrCreateTagBuilder(ModTags.Items.JAM_BOTTLES)
                    .add(WFIntegrationItems.WF_JAM_BOTTLES.get(name));

            getOrCreateTagBuilder(ModTags.Items.JUICE_BOTTLES)
                    .add(WFIntegrationItems.WF_JUICE_BOTTLES.get(name));

            getOrCreateTagBuilder(ModTags.Items.JAM_INGREDIENTS)
                    .add(WFIntegrationItems.WF_JAM_CHOPPED.get(name));
        }
    }
}
