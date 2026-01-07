package com.teamso.sogic.item.customitems;

import com.teamso.sogic.Block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Block clickedblock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (CONVERTER_MAP.containsKey(clickedblock)) {
            if(!level.isClientSide){

                BlockPos blockclickedpos = pContext.getClickedPos();
                //
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        BlockPos postotal = blockclickedpos.offset(x, y, 0);
                        Block bloque = level.getBlockState(postotal).getBlock();

                        if (clickedblock == bloque) {
                            level.setBlockAndUpdate(
                                    postotal,
                                    CONVERTER_MAP.get(bloque).defaultBlockState());
                        }
                    }
                }
                pContext.getItemInHand().hurtAndBreak(1,((ServerLevel) level), ((ServerPlayer)
                        pContext.getPlayer()),item -> {
                    assert pContext.getPlayer() != null;
                    pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                });
                //sound to used item
                level.playSound(null,blockclickedpos,
                        SoundEvents.AMETHYST_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);

                double x = blockclickedpos.getX();
                double y = blockclickedpos.getY();
                double z = blockclickedpos.getZ();

                ((ServerLevel) level).sendParticles(ParticleTypes.EXPLOSION,x,y,z,3,0.3,0.3,0.3,0.3);

            }
            if (pContext.getPlayer()!= null){
            pContext.getPlayer().getCooldowns().addCooldown(this, 10);
            }
        }

        return InteractionResult.SUCCESS;

    }
}

