package com.cyanstain.tutorialmod.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SpecalBlock extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream.of(Block.makeCuboidShape(6, 0, 5, 10, 6, 7),
			Block.makeCuboidShape(7, 3, 0, 9, 4, 16), Block.makeCuboidShape(10, 0, 1, 11, 11, 2),
			Block.makeCuboidShape(6, 10, 1, 11, 11, 2), Block.makeCuboidShape(8, 6, 1, 11, 7, 2),
			Block.makeCuboidShape(5, 0, 14, 6, 11, 15), Block.makeCuboidShape(5, 0, 14, 10, 1, 15),
			Block.makeCuboidShape(5, 5, 14, 10, 6, 15), Block.makeCuboidShape(10, 6, 14, 11, 10, 15),
			Block.makeCuboidShape(10, 1, 14, 11, 5, 15), Block.makeCuboidShape(5, 10, 14, 10, 11, 15))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();
	private static final VoxelShape SHAPE_W = Stream.of(Block.makeCuboidShape(5, 0, 6, 7, 6, 10),
			Block.makeCuboidShape(0, 3, 7, 16, 4, 9), Block.makeCuboidShape(1, 0, 5, 2, 11, 6),
			Block.makeCuboidShape(1, 10, 5, 2, 11, 10), Block.makeCuboidShape(1, 6, 5, 2, 7, 8),
			Block.makeCuboidShape(14, 0, 10, 15, 11, 11), Block.makeCuboidShape(14, 0, 6, 15, 1, 11),
			Block.makeCuboidShape(14, 5, 6, 15, 6, 11), Block.makeCuboidShape(14, 6, 5, 15, 10, 6),
			Block.makeCuboidShape(14, 1, 5, 15, 5, 6), Block.makeCuboidShape(14, 10, 6, 15, 11, 11))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();
	private static final VoxelShape SHAPE_E = Stream.of(Block.makeCuboidShape(9, 0, 6, 11, 6, 10),
			Block.makeCuboidShape(0, 3, 7, 16, 4, 9), Block.makeCuboidShape(14, 0, 10, 15, 11, 11),
			Block.makeCuboidShape(14, 10, 6, 15, 11, 11), Block.makeCuboidShape(14, 6, 8, 15, 7, 11),
			Block.makeCuboidShape(1, 0, 5, 2, 11, 6), Block.makeCuboidShape(1, 0, 5, 2, 1, 10),
			Block.makeCuboidShape(1, 5, 5, 2, 6, 10), Block.makeCuboidShape(1, 6, 10, 2, 10, 11),
			Block.makeCuboidShape(1, 1, 10, 2, 5, 11), Block.makeCuboidShape(1, 10, 5, 2, 11, 10)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();
	private static final VoxelShape SHAPE_S = Stream.of(Block.makeCuboidShape(6, 0, 9, 10, 6, 11),
			Block.makeCuboidShape(7, 3, 0, 9, 4, 16), Block.makeCuboidShape(5, 0, 14, 6, 11, 15),
			Block.makeCuboidShape(5, 10, 14, 10, 11, 15), Block.makeCuboidShape(5, 6, 14, 8, 7, 15),
			Block.makeCuboidShape(10, 0, 1, 11, 11, 2), Block.makeCuboidShape(6, 0, 1, 11, 1, 2),
			Block.makeCuboidShape(6, 5, 1, 11, 6, 2), Block.makeCuboidShape(5, 6, 1, 6, 10, 2),
			Block.makeCuboidShape(5, 1, 1, 6, 5, 2), Block.makeCuboidShape(6, 10, 1, 11, 11, 2)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public SpecalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case SOUTH:
			return SHAPE_S;
		case EAST:
			return SHAPE_E;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);

	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			ServerWorld serverWorld = (ServerWorld) worldIn;
			LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			serverWorld.addLightningBolt(entity);

		}
		return ActionResultType.SUCCESS;
	}

}
