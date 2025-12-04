package com.teamso.sogic.Block;
import com.teamso.sogic.Sogic;
import com.teamso.sogic.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    private static final DeferredRegister <Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Sogic.MOD_ID);

    public static final RegistryObject <Block> aaso = registerBlock("blocktest", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));


    private static <T extends Block> RegistryObject <T> registerBlock(String name, Supplier<T> block) {

        RegistryObject<T> Toreturn = BLOCKS.register(name, block);
        registerBlockItem(name, Toreturn);
        return Toreturn;
    }

    private static <T extends Block> void  registerBlockItem (String name, RegistryObject<T> block) {

        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));

    }
    public static final RegistryObject<Block> RUBY_BLOCK =
            BLOCKS.register("ruby_block", () ->
                    new Block(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops()));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
