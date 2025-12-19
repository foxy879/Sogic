package com.teamso.sogic.item;

import com.teamso.sogic.Block.ModBlocks;
import com.teamso.sogic.Sogic;
import com.teamso.sogic.item.customitems.Converter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Sogic.MOD_ID);

    public static final RegistryObject<Item> SONEDA = ITEMS.register("soneda", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> So = ITEMS.register("son", () -> new Item(new Item.Properties()));

    public static final RegistryObject <Item> x = ITEMS.register("x", () -> new Prueba(new Item.Properties()));

    public static final RegistryObject<Item> RUBY_BLOCK_ITEM =
            ITEMS.register("ruby_block", () ->
                    new BlockItem(ModBlocks.RUBY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject <Item> CONVERTER = ITEMS.register("converter", () ->
            new Converter(new Item.Properties().durability(50)));






public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);




    }

}
