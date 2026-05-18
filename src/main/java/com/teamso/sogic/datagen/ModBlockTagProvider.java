package com.teamso.sogic.datagen;

import com.teamso.sogic.Sogic;
import com.teamso.sogic.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Sogic.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SOUND_BLOCK.get())
                .add(ModBlocks.aaso.get())
                .add(ModBlocks.RUBY_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL);
        


        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SOUND_BLOCK.get())
                .add(ModBlocks.aaso.get())
                .add(ModBlocks.RUBY_BLOCK.get());


    }
}
