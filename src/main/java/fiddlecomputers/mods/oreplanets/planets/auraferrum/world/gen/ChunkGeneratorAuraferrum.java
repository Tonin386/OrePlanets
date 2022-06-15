package fiddlecomputers.mods.oreplanets.planets.auraferrum.world.gen;

import java.util.ArrayList;
import java.util.List;

import fiddlecomputers.mods.oreplanets.utils.world.gen.ChunkGeneratorBaseOP;
import fiddlecomputers.mods.oreplanets.world.gen.WorldGenLiquidLake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGeneratorAuraferrum extends ChunkGeneratorBaseOP {

	public ChunkGeneratorAuraferrum(World world, long seed) {
		super(world, seed);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
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

        int y = this.rand.nextInt(this.rand.nextInt(248) + 8);
        if (this.rand.nextInt(8) == 0)
        {
            if (y > 60)
            {
                new WorldGenLiquidLake(Blocks.LAVA.getDefaultState(), Blocks.STONE.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));    	
            }
        }
	}

	@Override
	protected List<IBlockState> getTopBlocks() {
		List<IBlockState> returnList = new ArrayList<IBlockState>();
		
		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState(), 300));
		returnList.addAll(populateLayer(Blocks.GRAVEL.getDefaultState(), 2500));
		returnList.addAll(populateLayer(Blocks.IRON_ORE.getDefaultState(), 5));
		returnList.addAll(populateLayer(Blocks.GOLD_ORE.getDefaultState(), 5));
		returnList.addAll(populateLayer(Blocks.REDSTONE_ORE.getDefaultState(), 8));
		returnList.addAll(populateLayer(Blocks.LAPIS_ORE.getDefaultState(), 3));
		returnList.addAll(populateLayer(Blocks.DIAMOND_ORE.getDefaultState(), 2));
		
		return returnList;
	}

	@Override
	protected List<IBlockState> getSubBlocks() {
		List<IBlockState> returnList = new ArrayList<IBlockState>();
		
		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState(), 65));
		returnList.addAll(populateLayer(Blocks.GRAVEL.getDefaultState(), 10));
		returnList.addAll(populateLayer(Blocks.IRON_ORE.getDefaultState(), 5));
		returnList.addAll(populateLayer(Blocks.GOLD_ORE.getDefaultState(), 5));
		returnList.addAll(populateLayer(Blocks.REDSTONE_ORE.getDefaultState(), 8));
		returnList.addAll(populateLayer(Blocks.LAPIS_ORE.getDefaultState(), 3));
		returnList.addAll(populateLayer(Blocks.DIAMOND_ORE.getDefaultState(), 2));
		returnList.addAll(populateLayer(Blocks.IRON_BLOCK.getDefaultState(), 5));
		returnList.addAll(populateLayer(Blocks.GOLD_BLOCK.getDefaultState(), 5));
		returnList.addAll(populateLayer(Blocks.REDSTONE_BLOCK.getDefaultState(), 8));
		returnList.addAll(populateLayer(Blocks.LAPIS_BLOCK.getDefaultState(), 3));
		returnList.addAll(populateLayer(Blocks.DIAMOND_BLOCK.getDefaultState(), 1));
		
		return returnList;
	}

	@Override
	protected List<IBlockState> getStoneBlocks() {
		List<IBlockState> returnList = new ArrayList<IBlockState>();

		returnList.addAll(populateLayer(Blocks.STONE.getDefaultState(), 10));
		returnList.addAll(populateLayer(Blocks.GRAVEL.getDefaultState(), 10));
		returnList.addAll(populateLayer(Blocks.IRON_ORE.getDefaultState(), 2));
		returnList.addAll(populateLayer(Blocks.GOLD_ORE.getDefaultState(), 2));
		returnList.addAll(populateLayer(Blocks.REDSTONE_ORE.getDefaultState(), 2));
		returnList.addAll(populateLayer(Blocks.LAPIS_ORE.getDefaultState(), 1));
		returnList.addAll(populateLayer(Blocks.DIAMOND_ORE.getDefaultState(), 1));
		returnList.addAll(populateLayer(Blocks.IRON_BLOCK.getDefaultState(), 20));
		returnList.addAll(populateLayer(Blocks.GOLD_BLOCK.getDefaultState(), 20));
		returnList.addAll(populateLayer(Blocks.REDSTONE_BLOCK.getDefaultState(), 25));
		returnList.addAll(populateLayer(Blocks.LAPIS_BLOCK.getDefaultState(), 15));
		returnList.addAll(populateLayer(Blocks.DIAMOND_BLOCK.getDefaultState(), 1));
		
		return returnList;
	}

}
