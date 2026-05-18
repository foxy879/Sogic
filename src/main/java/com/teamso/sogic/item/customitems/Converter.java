package com.teamso.sogic.item.customitems;

import com.teamso.sogic.blocks.ModBlocks;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static net.minecraft.core.Direction.*;

public class Converter extends Item {


    //map of allowed blocks

    private static final Map<Block, Block> CONVERTER_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS
                    , Blocks.COPPER_BLOCK, ModBlocks.RUBY_BLOCK.get());

    public Converter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();

        Block clickedblock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if (CONVERTER_MAP.containsKey(clickedblock)) {

            if (!level.isClientSide) {

                Direction face = pContext.getClickedFace();

                BlockPos blockclickedpos = pContext.getClickedPos();

                // AREA 3x3 SEGUN LA CARA
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        BlockPos postotal = switch (face) {

                            // plano horizontal
                            case UP, DOWN ->
                                    blockclickedpos.offset(x, 0, y);

                            // plano frontal
                            case NORTH, SOUTH ->
                                    blockclickedpos.offset(x, y, 0);

                            // plano lateral
                            case EAST, WEST ->
                                    blockclickedpos.offset(0, y, x);
                        };

                        Block bloque =
                                level.getBlockState(postotal).getBlock();

                        if (clickedblock == bloque) {

                            level.setBlockAndUpdate(
                                    postotal,
                                    CONVERTER_MAP.get(bloque)
                                            .defaultBlockState()
                            );
                        }
                    }
                }
                // daño del item
                pContext.getItemInHand().hurtAndBreak(
                        1,
                        ((ServerLevel) level),
                        ((ServerPlayer) pContext.getPlayer()),
                        item -> {

                            assert pContext.getPlayer() != null;

                            pContext.getPlayer().onEquippedItemBroken(
                                    item,
                                    EquipmentSlot.MAINHAND
                            );
                        }
                );

                // sonido
                level.playSound(
                        null,
                        blockclickedpos,
                        SoundEvents.AMETHYST_BLOCK_BREAK,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F
                );


                double px = blockclickedpos.getX();
                double py = blockclickedpos.getY();
                double pz = blockclickedpos.getZ();

                ((ServerLevel) level).sendParticles(
                        ParticleTypes.EXPLOSION,
                        px,
                        py,
                        pz,
                        3,
                        0.3,
                        0.3,
                        0.3,
                        0.3
                );
            }

            // cooldown
            if (pContext.getPlayer() != null) {

                pContext.getPlayer()
                        .getCooldowns()
                        .addCooldown(this, 10);
            }
        }

        return InteractionResult.SUCCESS;
    }

    private static @NotNull InteractionResult getInteractionResult() {
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.sogic.converter.converter.shift_down"));
        }else {
            pTooltipComponents.add(Component.translatable("tooltip.sogic.converter.converter"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}

