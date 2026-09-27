package com.teamso.sogic.datagen;

import com.teamso.sogic.Sogic;
import com.teamso.sogic.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Sogic.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.aaso);
        blockWithItem(ModBlocks.RUBY_BLOCK);

        stairsBlock(ModBlocks.STAIR_TEST.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()));
        slabBlock(ModBlocks.SLAB_TEST.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()),blockTexture(ModBlocks.SOUND_BLOCK.get()));

        buttonBlock(ModBlocks.BUTTON_TEST.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()));
        pressurePlateBlock(ModBlocks.PRESSURE_PLATE_RUBY.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()));

        fenceBlock(ModBlocks.FENCE_TEST.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()));
        fenceGateBlock(ModBlocks.FENCEGATE_TEST.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()));
        wallBlock(ModBlocks.WALL_TEST.get(),blockTexture(ModBlocks.SOUND_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.DOOR_TEST.get(), modLoc("block/alexandrite_door_bottom"), modLoc("block/alexandrite_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.TRAP_DOOR_TEST.get(), modLoc("block/alexandrite_trapdoor"),true, "cutout");

        blockItem(ModBlocks.STAIR_TEST);
        blockItem(ModBlocks.SLAB_TEST);
        blockItem(ModBlocks.PRESSURE_PLATE_RUBY);
        blockItem(ModBlocks.FENCEGATE_TEST);
        blockItem(ModBlocks.TRAP_DOOR_TEST,"_bottom");




    }
    private void blockWithItem (RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));

    }
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("sogic:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("sogic:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
