package com.teamso.sogic.blocks;
import com.teamso.sogic.blocks.custom.SoundBlock;
import com.teamso.sogic.Sogic;
import com.teamso.sogic.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister <Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Sogic.MOD_ID);

    public static final RegistryObject <Block> aaso = registerBlock("blocktest", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject <Block> SOUND_BLOCK = registerBlock("sound_block",  () -> new SoundBlock(BlockBehaviour.Properties.of()));

    public static final RegistryObject <StairBlock> STAIR_TEST = registerBlock("stair_test", () -> new StairBlock(ModBlocks.RUBY_BLOCK.get()
            .defaultBlockState(),BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()));

    public static final RegistryObject <SlabBlock> SLAB_TEST = registerBlock("slab_test", () -> new SlabBlock(
            BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()));

    public static final RegistryObject <PressurePlateBlock> PRESSURE_PLATE_RUBY = registerBlock("pressure_plate_ruby",
            () -> new PressurePlateBlock(BlockSetType.IRON,BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()));

    public static final RegistryObject <ButtonBlock> BUTTON_TEST = registerBlock("button_test", () -> new ButtonBlock(BlockSetType.IRON,
            4,BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject <FenceBlock> FENCE_TEST = registerBlock("fence_test", () -> new FenceBlock(
            BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()));

    public static final RegistryObject <FenceGateBlock> FENCEGATE_TEST = registerBlock("fence_gate_test", () -> new FenceGateBlock(
            WoodType.ACACIA,BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()));

    public static final RegistryObject <WallBlock> WALL_TEST = registerBlock("wall_test", () -> new WallBlock
            (BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops()));

    public static final RegistryObject <DoorBlock> DOOR_TEST = registerBlock("door_test", () -> new DoorBlock(BlockSetType.ACACIA,
            BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject <TrapDoorBlock> TRAP_DOOR_TEST = registerBlock("trap_door_test", () -> new TrapDoorBlock(BlockSetType.ACACIA,
            BlockBehaviour.Properties.of().strength(2).requiresCorrectToolForDrops().noOcclusion()));

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
