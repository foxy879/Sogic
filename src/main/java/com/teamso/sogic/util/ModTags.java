package com.teamso.sogic.util;

import com.teamso.sogic.Sogic;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Sogic.MOD_ID, name));

        }
    }

    public static class Items {

        public static final TagKey<Item> TRANSFORMABLE_ITEM = createTag("transformable_item");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Sogic.MOD_ID, name));
        }

        public static final TagKey<Item> TEST_INGOTS =
                TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "ingots/test"));







    }
}
