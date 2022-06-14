package fiddlecomputers.mods.oreplanets.planets.exanterra.world.gen;

import java.util.ArrayList;
import java.util.List;

import fiddlecomputers.mods.oreplanets.utils.world.gen.ChunkGeneratorBaseOP;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGeneratorExanterra extends ChunkGeneratorBaseOP {

	public ChunkGeneratorExanterra(World world, long seed) {
		super(world, seed);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	@Override
	protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ) {
		
	}

	@Override
	protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ) {
		
	}

	@Override
	protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z) {
		// TODO Try to create oil lakes using WorldGenLiquidLake
		
	}

	@Override
	protected List<IBlockState> getTopBlocks() {
    	List<IBlockState> returnList = new ArrayList<IBlockState>();
    	returnList.add(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockNewLog.LOG_AXIS, EnumAxis.Z));
    	returnList.add(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockNewLog.LOG_AXIS, EnumAxis.X));
		return returnList;
	}

	@Override
	protected List<IBlockState> getSubBlocks() {
    	List<IBlockState> returnList = new ArrayList<IBlockState>();
    	returnList.add(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockOldLog.LOG_AXIS, EnumAxis.Z));
    	returnList.add(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockOldLog.LOG_AXIS, EnumAxis.X));
		return returnList;
	}

	@Override
	protected List<IBlockState> getStoneBlocks() {
    	List<IBlockState> returnList = new ArrayList<IBlockState>();
    	returnList.add(Blocks.COAL_BLOCK.getDefaultState());
		return returnList;
	}

}
