package com.teamso.sogic.creativemodtab;



import com.teamso.sogic.Block.ModBlocks;
import com.teamso.sogic.Sogic;
import com.teamso.sogic.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeModeTab {


    public static final DeferredRegister <CreativeModeTab> CREATIVE_MOD_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Sogic.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SOGIC_CREATIVE_MODE_TAB = CREATIVE_MOD_TAB.register("creativemodtab", () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab." + Sogic.MOD_ID + ".soneda_items"))
            .icon(()-> new ItemStack(ModItems.SONEDA.get()))
            .displayItems((parameters,output)-> {
                output.accept(ModItems.SONEDA.get());
                output.accept(ModItems.CONVERTER.get());
                output.accept(ModBlocks.SOUND_BLOCK.get());
            })


            .build());


    public static void register (IEventBus eventBus) {
        CREATIVE_MOD_TAB.register(eventBus);
    }



}
