package eu.octanne.edora.block.blocks;

import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.block.enums.EdoraSlabType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class EdoraSlabBlock extends Block implements Waterloggable {

    public static final EnumProperty<EdoraSlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape BOTTOM_SHAPE;
    protected static final VoxelShape TOP_SHAPE;
    protected static final VoxelShape WEST_SHAPE;
    protected static final VoxelShape EAST_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;

    static {
        TYPE = EdoraSlabType.SLAB_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
        NORTH_SHAPE = Block.createCuboidShape(0D, 0D, 0D, 16D, 16D, 8D);
        SOUTH_SHAPE = Block.createCuboidShape(0D, 0D, 8D, 16D, 16D, 16D);
        WEST_SHAPE = Block.createCuboidShape(0D, 0D, 0D, 8D, 16D, 16D);
        EAST_SHAPE = Block.createCuboidShape(8D, 0D, 0D, 16D, 16D, 16D);
        BOTTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
        TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    public EdoraSlabBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState) this.getDefaultState().with(TYPE, EdoraSlabType.BOTTOM))
                .with(WATERLOGGED, false));
    }

    public boolean hasSidedTransparency(BlockState state) {
        return state.get(TYPE) != EdoraSlabType.DOUBLE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        EdoraSlabType slabType = state.get(TYPE);
        switch (slabType) {
            case TOP:
                return TOP_SHAPE;
            case BOTTOM:
                return BOTTOM_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        Direction direction = ctx.getSide();
        double xRel = ctx.getHitPos().x - (double)ctx.getBlockPos().getX();
        double yRel = ctx.getHitPos().y - (double)ctx.getBlockPos().getY();
        double zRel = ctx.getHitPos().z - (double)ctx.getBlockPos().getZ();
        boolean isDownOrUp = direction == Direction.DOWN || direction == Direction.UP;
        
        if (blockState.isOf(this)) {
            return this.getDefaultState().with(TYPE, EdoraSlabType.DOUBLE).with(WATERLOGGED, false);
        }else if (ctx.getPlayer().isSneaking()) {
            EdoraSlabType slabType = EdoraSlabType.BOTTOM;
            if (direction == Direction.NORTH) {
                slabType = EdoraSlabType.SOUTH;
            } else if (direction == Direction.SOUTH) {
                slabType = EdoraSlabType.NORTH;
            } else if (direction == Direction.EAST) {
                slabType = EdoraSlabType.WEST;
            } else if (direction == Direction.WEST) {
                slabType = EdoraSlabType.EAST;
            } else if (isDownOrUp) {
                EdoraMain.log(Level.INFO, "LOC : "+xRel+" "+yRel+" "+zRel);
            }
            return this.getDefaultState().with(TYPE, slabType).with(WATERLOGGED, ctx.getWorld().getFluidState(blockPos).getFluid() == Fluids.WATER);
        } else {
            return oldPlacement(ctx);
        }
    }

    private BlockState oldPlacement(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        Direction direction = ctx.getSide();
        if (blockState.isOf(this) && (blockState.get(TYPE) == EdoraSlabType.BOTTOM || blockState.get(TYPE) == EdoraSlabType.TOP)) {
            return ((BlockState)blockState.with(TYPE, EdoraSlabType.DOUBLE)).with(WATERLOGGED, false);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            BlockState blockState2 = ((BlockState)this.getDefaultState().with(TYPE, EdoraSlabType.BOTTOM)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            return direction != Direction.DOWN && (direction == Direction.UP || ctx.getHitPos().y - (double)blockPos.getY() <= 0.5D) ? blockState2 : blockState2.with(TYPE, EdoraSlabType.TOP);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        EdoraSlabType slabType = state.get(TYPE);
        Direction direction = context.getSide();
        double xRel = context.getHitPos().x - (double)context.getBlockPos().getX();
        double yRel = context.getHitPos().y - (double)context.getBlockPos().getY();
        double zRel = context.getHitPos().z - (double)context.getBlockPos().getZ();
        if (slabType != EdoraSlabType.DOUBLE && itemStack.getItem() == this.asItem()) {
            if (context.getPlayer().isSneaking()) {
                // Check Fill UP
                if ((direction == Direction.UP && slabType == EdoraSlabType.BOTTOM) ||
                        (slabType == EdoraSlabType.BOTTOM && yRel >= 0.5)) return true;
                // Check Fill Down
                else if ((direction == Direction.DOWN && slabType == EdoraSlabType.TOP) || 
                        (slabType == EdoraSlabType.TOP && yRel <= 0.5)) return true;
                // Check Fill WEST
                else if((direction == Direction.WEST && slabType == EdoraSlabType.EAST) ||
                        (slabType == EdoraSlabType.EAST && xRel <= 0.5)) return true;
                // Check Fill EAST
                else if((direction == Direction.EAST && slabType == EdoraSlabType.WEST) ||
                        (slabType == EdoraSlabType.WEST && xRel >= 0.5)) return true;
                // Check Fill SOUTH
                else if((direction == Direction.SOUTH && slabType == EdoraSlabType.NORTH) ||
                    (slabType == EdoraSlabType.NORTH && zRel >= 0.5)) return true;
                // Check Fill NORTH
                else if((direction == Direction.NORTH && slabType == EdoraSlabType.SOUTH) ||
                    (slabType == EdoraSlabType.SOUTH && zRel <= 0.5)) return true;
                else return false;
            }else {
                // Check Fill UP
                if ((direction == Direction.UP && slabType == EdoraSlabType.BOTTOM) ||
                        (slabType == EdoraSlabType.BOTTOM && yRel >= 0.5)) return true;
                // Check Fill Down
                else if ((direction == Direction.DOWN && slabType == EdoraSlabType.TOP) || 
                        (slabType == EdoraSlabType.TOP && yRel <= 0.5)) return true;
                else return false;
            }
        } else {
            return false;
        }
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean) state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != EdoraSlabType.DOUBLE ? Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState)
                : false;
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != EdoraSlabType.DOUBLE ? Waterloggable.super.canFillWithFluid(world, pos, state, fluid)
                : false;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState,
            WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if ((Boolean) state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        switch (type) {
            case LAND:
                return false;
            case WATER:
                return world.getFluidState(pos).isIn(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
    }

}
