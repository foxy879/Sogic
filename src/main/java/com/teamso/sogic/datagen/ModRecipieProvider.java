package com.teamso.sogic.datagen;

import com.teamso.sogic.Sogic;
import com.teamso.sogic.blocks.ModBlocks;
import com.teamso.sogic.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipieProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipieProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {

        List<ItemLike> SMALTEABLE = List.of(ModBlocks.RUBY_BLOCK.get(), ModBlocks.SOUND_BLOCK.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.SONEDA.get())
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SONEDA.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RUBY_BLOCK.get()), has(ModBlocks.RUBY_BLOCK.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SONEDA.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RUBY_BLOCK.get()), has(ModBlocks.RUBY_BLOCK.get())).save(pRecipeOutput, Sogic.MOD_ID + "ruby_from_soneda");

        oreSmelting(pRecipeOutput,SMALTEABLE,RecipeCategory.MISC,ModItems.SONEDA.get(),25f,100,"elpepe");
        oreBlasting(pRecipeOutput,SMALTEABLE,RecipeCategory.MISC,ModItems.SONEDA.get(),25f,100,"elpepe");





    }

}
