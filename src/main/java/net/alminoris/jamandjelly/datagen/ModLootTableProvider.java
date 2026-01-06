package net.alminoris.jamandjelly.datagen;

import net.alminoris.jamandjelly.block.ModBlocks;
import net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks;
import net.alminoris.jamandjelly.integration.wildfields.block.WFIntegrationBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.alminoris.jamandjelly.integration.arborealnature.block.ANIntegrationBlocks.AN_WOOD_NAMES;
import static net.alminoris.jamandjelly.integration.arborealnature.item.ANIntegrationItems.AN_JAM_NAMES;
import static net.alminoris.jamandjelly.integration.wildfields.block.WFIntegrationBlocks.WF_WOOD_NAMES;
import static net.alminoris.jamandjelly.integration.wildfields.item.WFIntegrationItems.WF_JAM_NAMES;
import static net.alminoris.jamandjelly.util.helper.BlockSetsHelper.PLASTIC_BLOCK_NAMES;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {
        addDrop(ModBlocks.JAR_BLACK);
        addDrop(ModBlocks.JAR_BROWN);
        addDrop(ModBlocks.JAR_GRAY);
        addDrop(ModBlocks.JAR_LIGHT_GRAY);
        addDrop(ModBlocks.JAR_WHITE);
        addDrop(ModBlocks.JAR_RED);
        addDrop(ModBlocks.JAR_ORANGE);
        addDrop(ModBlocks.JAR_YELLOW);
        addDrop(ModBlocks.JAR_PURPLE);
        addDrop(ModBlocks.JAR_MAGENTA);
        addDrop(ModBlocks.JAR_PINK);
        addDrop(ModBlocks.JAR_BLUE);
        addDrop(ModBlocks.JAR_CYAN);
        addDrop(ModBlocks.JAR_LIGHT_BLUE);
        addDrop(ModBlocks.JAR_GREEN);
        addDrop(ModBlocks.JAR_LIME);
        addDrop(ModBlocks.APPLE_JAM_BLOCK);
        addDrop(ModBlocks.SWEETBERRY_JAM_BLOCK);
        addDrop(ModBlocks.MELON_JAM_BLOCK);
        addDrop(ModBlocks.APPLE_JELLY_BLOCK);
        addDrop(ModBlocks.SWEETBERRY_JELLY_BLOCK);
        addDrop(ModBlocks.MELON_JELLY_BLOCK);
        for(String name : AN_JAM_NAMES)
        {
            addDrop(ANIntegrationBlocks.AN_JAM_BLOCKS.get(name));
            addDrop(ANIntegrationBlocks.AN_JELLY_BLOCKS.get(name));
        }
        for(String name : WF_JAM_NAMES)
        {
            addDrop(WFIntegrationBlocks.WF_JAM_BLOCKS.get(name));
            addDrop(WFIntegrationBlocks.WF_JELLY_BLOCKS.get(name));
        }

        for(String name: PLASTIC_BLOCK_NAMES)
        {
            addDrop(ModBlocks.PLASTIC_BLOCKS.get(name));
            addDrop(ModBlocks.SLABS.get(name));
            addDrop(ModBlocks.STAIRS.get(name));
            addDrop(ModBlocks.BUTTONS.get(name));
            addDrop(ModBlocks.PRESSURE_PLATES.get(name));
            addDrop(ModBlocks.WALLS.get(name));
        }
        addDrop(ModBlocks.KELP_BLOCK);
        addDrop(ModBlocks.JAMMING_POT);
        addDrop(ModBlocks.JUICER);
        addDrop(ModBlocks.CHOPPING_BOARD_OAK);
        addDrop(ModBlocks.CHOPPING_BOARD_BIRCH);
        addDrop(ModBlocks.CHOPPING_BOARD_SPRUCE);
        addDrop(ModBlocks.CHOPPING_BOARD_JUNGLE);
        addDrop(ModBlocks.CHOPPING_BOARD_ACACIA);
        addDrop(ModBlocks.CHOPPING_BOARD_DARK_OAK);
        addDrop(ModBlocks.CHOPPING_BOARD_CRIMSON);
        addDrop(ModBlocks.CHOPPING_BOARD_WARPED);
        addDrop(ModBlocks.CHOPPING_BOARD_MANGROVE);
        addDrop(ModBlocks.CHOPPING_BOARD_CHERRY);
        addDrop(ModBlocks.CHOPPING_BOARD_BAMBOO);
        for(String name : AN_WOOD_NAMES)
            addDrop(ANIntegrationBlocks.AN_CHOPPING_BOARDS.get(name));
        for(String name : WF_WOOD_NAMES)
            addDrop(WFIntegrationBlocks.WF_CHOPPING_BOARDS.get(name));
    }

    private LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops)
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    private LootTable.Builder leavesItemDrops(Block leaves, Block sapling, Item item, float... saplingChance)
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance)
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .conditionally(this.createWithoutShearsOrSilkTouchCondition())
                        .with(
                                ((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(item)))
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                        )
                );
    }
}
