package com.teamso.sogic.Block.custom;

import com.teamso.sogic.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

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
        }
        return InteractionResult.SUCCESS;
    }
}
