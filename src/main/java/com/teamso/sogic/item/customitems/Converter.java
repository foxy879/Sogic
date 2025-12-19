package com.teamso.sogic.item.customitems;

import com.teamso.sogic.Block.ModBlocks;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class Converter extends Item {

    private static final Map<Block,Block> CONVERTER_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS
                    ,Blocks.COPPER_BLOCK, ModBlocks.RUBY_BLOCK.get());

    public Converter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Block clickedblock = level.getBlockState(pContext.getClickedPos()).getBlock();
        if (CONVERTER_MAP.containsKey(clickedblock)) {
            if(!level.isClientSide){
                level.setBlockAndUpdate(pContext.getClickedPos(), CONVERTER_MAP.get(clickedblock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level), ((ServerPlayer)
                        pContext.getPlayer()),item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
                );
                level.playSound(pContext.getPlayer(),pContext.getClickedPos(),
                        SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }

        return InteractionResult.SUCCESS;

    }
}

