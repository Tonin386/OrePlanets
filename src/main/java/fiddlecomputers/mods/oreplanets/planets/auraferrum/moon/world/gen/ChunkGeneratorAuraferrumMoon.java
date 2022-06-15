package fiddlecomputers.mods.oreplanets.planets.auraferrum.moon.world.gen;

import java.util.ArrayList;
import java.util.List;

import buildcraft.api.BCBlocks;
import fiddlecomputers.mods.oreplanets.utils.world.gen.ChunkGeneratorBaseOP;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockBasic;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGeneratorAuraferrumMoon extends ChunkGeneratorBaseOP {

	public ChunkGeneratorAuraferrumMoon(World world, long seed) {
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
		this.createCraters(chunkX, chunkZ, primer);
		
	}

	@Override
	protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ) {
		
	}

	@Override
	protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z) {
		
	}


	@Override
	protected List<IBlockState> getTopBlocks() {
		List<IBlockState> returnList = new ArrayList<IBlockState>();

		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState(), 300));
		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), 2500));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_ALUMINUM), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_COPPER), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_TIN), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_SILICON), 5));
		
		return returnList;
	}

	@Override
	protected List<IBlockState> getSubBlocks() {
		List<IBlockState> returnList = new ArrayList<IBlockState>();
		
		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState(), 65));
		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), 10));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_ALUMINUM), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_COPPER), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_TIN), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_SILICON), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_ALUMINUM), 1));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_COPPER), 1));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_TIN), 1));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_SILICON), 1));
		
		return returnList;
	}

	@Override
	protected List<IBlockState> getStoneBlocks() {
		List<IBlockState> returnList = new ArrayList<IBlockState>();

		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState(), 10));
		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), 10));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_ALUMINUM), 15));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_COPPER), 15));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_TIN), 15));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.ORE_SILICON), 15));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_ALUMINUM), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_COPPER), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_TIN), 5));
		returnList.addAll(populateLayer(GCBlocks.basicBlock.getDefaultState().withProperty(BlockBasic.BASIC_TYPE, BlockBasic.EnumBlockBasic.DECO_BLOCK_SILICON), 5));;
		
		return returnList;
	}

}
