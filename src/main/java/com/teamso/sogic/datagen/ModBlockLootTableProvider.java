package com.teamso.sogic.datagen;

import com.teamso.sogic.blocks.ModBlocks;
import com.teamso.sogic.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {


        dropSelf(ModBlocks.SOUND_BLOCK.get());

        dropSelf(ModBlocks.DOOR_TEST.get());

        this.add(ModBlocks.DOOR_TEST.get(),
                block -> createDoorTable(ModBlocks.DOOR_TEST.get()));

        dropSelf(ModBlocks.TRAP_DOOR_TEST.get());
        dropSelf(ModBlocks.FENCEGATE_TEST.get());
        dropSelf(ModBlocks.FENCE_TEST.get());
        dropSelf(ModBlocks.SLAB_TEST.get());

        this.add(ModBlocks.SLAB_TEST.get(),
                block -> createSlabItemTable(ModBlocks.SLAB_TEST.get()));
        
        dropSelf(ModBlocks.STAIR_TEST.get());

        dropSelf(ModBlocks.BUTTON_TEST.get());
        dropSelf(ModBlocks.PRESSURE_PLATE_RUBY.get());
        dropSelf(ModBlocks.WALL_TEST.get());



        this.add(ModBlocks.RUBY_BLOCK.get(), block -> createOreDrop(ModBlocks.RUBY_BLOCK.get(), ModItems.SONEDA.get()) );
        this.add(ModBlocks.aaso.get(),block -> createMultipleOreDrops(ModBlocks.aaso.get(),ModItems.SONEDA.get(),2f,5f));


    }
    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
