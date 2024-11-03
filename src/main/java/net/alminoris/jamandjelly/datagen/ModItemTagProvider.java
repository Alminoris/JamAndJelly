package net.alminoris.jamandjelly.datagen;

import net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems;
import net.alminoris.jamandjelly.item.ModItems;
import net.alminoris.jamandjelly.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.jamandjelly.integration.arborealnature.item.IntegrationItems.JAM_NAMES;


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

        getOrCreateTagBuilder(ModTags.Items.JAM_INGREDIENTS)
                .add(ModItems.APPLE_CHOPPED)
                .add(ModItems.SWEETBERRY_CHOPPED)
                .add(ModItems.MELON_CHOPPED);

        for(String name : JAM_NAMES)
        {
            getOrCreateTagBuilder(ModTags.Items.JAM_BOTTLES)
                    .add(IntegrationItems.JAM_BOTTLES.get(name));

            getOrCreateTagBuilder(ModTags.Items.JAM_INGREDIENTS)
                    .add(IntegrationItems.JAM_CHOPPED.get(name));
        }
    }
}
