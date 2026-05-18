package com.teamso.sogic.blocks.custom;

import com.teamso.sogic.item.ModItems;
import com.teamso.sogic.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.teamso.sogic.util.ModTags.Items.TEST_INGOTS;

public class SoundBlock extends Block {

    public SoundBlock(Properties properties) {
        super(properties);
    }



    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull BlockHitResult pHitResult) {
        if (!pLevel.isClientSide){

            int x = pPos.getX();
            int y = pPos.getY();
            int z = pPos.getZ();


            pLevel.playSound(null,pPos, SoundEvents.AMETHYST_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);

            ((ServerLevel)pLevel).sendParticles(ParticleTypes.NOTE,x,y,z,5,0.3,0.3,0.3,0.10);

            List<Item> ALLOW_ITEMS = List.of(
                    Items.DIAMOND, Items.GOLD_INGOT,ModItems.SONEDA.get()
            );

            Item ramdon_item = ALLOW_ITEMS.get(pLevel.getRandom().nextInt(ALLOW_ITEMS.size()));

            ItemStack Amount =  new ItemStack(ramdon_item, 1);

            ItemEntity itemEntity = new ItemEntity(pLevel,
                    x + 0.5,
                    y + 1,
                    z + 0.5,
                    Amount);
            pLevel.addFreshEntity(itemEntity);

            ItemStack stack = pPlayer.getMainHandItem();
            if(stack.is(TEST_INGOTS));

            pPlayer.sendSystemMessage(
                    Component.literal(
                            "ITEM EN MANO: " + stack.getItem().toString()
                    )
            );


            if (stack.is(TEST_INGOTS)) {
                pPlayer.sendSystemMessage(
                        Component.literal("ESTE ITEM ES UN INGOTE DE LA TAG")
                );
            } else {
                pPlayer.sendSystemMessage(
                        Component.literal("NO pertenece a la tag")
                );
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof ItemEntity itemEntity) {
            if(isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }

            if(itemEntity.getItem().getItem() == Items.RABBIT_FOOT) {
                itemEntity.setItem(new ItemStack(Items.EMERALD, itemEntity.getItem().getCount()));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE_ITEM);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

        pTooltipComponents.add(Component.translatable("tooltip.sogic.soundblock.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
