package eu.octanne.edora.block.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class EdoraGrassBlock extends GrassBlock {

    private Block dirt_block;

    public EdoraGrassBlock(Settings settings, Block dirt_block) {
        super(settings);
        this.dirt_block = dirt_block;
    }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
           return true;
        } else if (blockState.getFluidState().getLevel() == 8) {
           return false;
        } else {
           int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
           return i < world.getMaxLightLevel();
        }
    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!canSurvive(state, world, pos)) {
           world.setBlockState(pos, dirt_block.getDefaultState());
        } else {
           if (world.getLightLevel(pos.up()) >= 9) {
              BlockState blockState = this.getDefaultState();
  
              for(int i = 0; i < 4; ++i) {
                 BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                 if (world.getBlockState(blockPos).isOf(dirt_block) && canSpread(blockState, world, blockPos)) {
                    world.setBlockState(blockPos, (BlockState)blockState.with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                 }
              }
           }
  
        }
     }
    
}
