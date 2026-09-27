package com.teamso.sogic.datagen;

import com.teamso.sogic.Sogic;
import com.teamso.sogic.blocks.ModBlocks;
import com.teamso.sogic.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
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

        stairBuilder(ModBlocks.STAIR_TEST.get(), Ingredient.of(ModItems.SONEDA.get())).group("elpepe")
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SLAB_TEST.get(), ModItems.SONEDA.get());

        buttonBuilder(ModBlocks.BUTTON_TEST.get(), Ingredient.of(ModItems.SONEDA.get())).group("elpepe")
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, ModBlocks.PRESSURE_PLATE_RUBY.get(), ModItems.SONEDA.get());

        fenceBuilder(ModBlocks.FENCE_TEST.get(), Ingredient.of(ModItems.SONEDA.get())).group("elpepe")
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.FENCEGATE_TEST.get(), Ingredient.of(ModItems.SONEDA.get())).group("elpepe")
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WALL_TEST.get(), ModItems.SONEDA.get());

        doorBuilder(ModBlocks.DOOR_TEST.get(), Ingredient.of(ModItems.SONEDA.get())).group("elpepe")
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);
        trapdoorBuilder(ModBlocks.TRAP_DOOR_TEST.get(), Ingredient.of(ModItems.SONEDA.get())).group("elpepe")
                .unlockedBy(getHasName(ModItems.SONEDA.get()), has(ModItems.SONEDA.get())).save(pRecipeOutput);

    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Sogic.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
