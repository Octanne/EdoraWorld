package eu.octanne.edora.biome.surfacebuilder;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class JungleSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {

    public JungleSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
        super(codec);
    }

    public void generate(Random random, Chunk chunk, Biome biome, int i, int j, int k, double d, BlockState blockState,
            BlockState blockState2, int l, int m, long n, TernarySurfaceConfig ternarySurfaceConfig) {
        this.generate(random, chunk, biome, i, j, k, d, blockState, blockState2, ternarySurfaceConfig.getTopMaterial(),
                ternarySurfaceConfig.getUnderMaterial(), ternarySurfaceConfig.getUnderwaterMaterial(), l, m);
    }

    protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise,
            BlockState defaultBlock, BlockState fluidBlock, BlockState topBlock, BlockState underBlock,
            BlockState underwaterBlock, int seaLevel, int i) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int j = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int k;
        BlockState blockState5;
        if (j == 0) {
            boolean bl = false;

            for (k = height; k >= i; --k) {
                mutable.set(x, k, z);
                BlockState blockState = chunk.getBlockState(mutable);
                if (blockState.isAir()) {
                    bl = false;
                } else if (blockState.isOf(defaultBlock.getBlock())) {
                    if (!bl) {
                        if (k >= seaLevel) {
                            blockState5 = Blocks.AIR.getDefaultState();
                        } else if (k == seaLevel - 1) {
                            blockState5 = biome.getTemperature(mutable) < 0.15F ? Blocks.ICE.getDefaultState()
                                    : fluidBlock;
                        } else if (k >= seaLevel - (7 + j)) {
                            blockState5 = defaultBlock;
                        } else {
                            blockState5 = underwaterBlock;
                        }

                        chunk.setBlockState(mutable, blockState5, false);
                    }

                    bl = true;
                }
            }
        } else {
            BlockState blockState6 = underBlock;
            k = -1;

            for (int m = height; m >= i; --m) {
                mutable.set(x, m, z);
                blockState5 = chunk.getBlockState(mutable);
                if (blockState5.isAir()) {
                    k = -1;
                } else if (blockState5.isOf(defaultBlock.getBlock())) {
                    if (k == -1) {
                        k = j;
                        BlockState blockState12;
                        if (m >= seaLevel + 2) {
                            blockState12 = topBlock;
                        } else if (m >= seaLevel - 1) {
                            blockState6 = underBlock;
                            blockState12 = topBlock;
                        } else if (m >= seaLevel - 4) {
                            blockState6 = underBlock;
                            blockState12 = underBlock;
                        } else if (m >= seaLevel - (7 + j)) {
                            blockState12 = blockState6;
                        } else {
                            blockState6 = defaultBlock;
                            blockState12 = underwaterBlock;
                        }

                        chunk.setBlockState(mutable, blockState12, false);
                    } else if (k > 0) {
                        --k;
                        chunk.setBlockState(mutable, blockState6, false);
                        if (k == 0 && blockState6.isOf(Blocks.SAND) && j > 1) {
                            k = random.nextInt(4) + Math.max(0, m - seaLevel);
                            blockState6 = blockState6.isOf(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.getDefaultState()
                                    : Blocks.SANDSTONE.getDefaultState();
                        }
                    }
                }
            }
        }

    }
}
