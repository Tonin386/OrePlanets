package fiddlecomputers.mods.oreplanets.planets.diamondplanet.world.gen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import fiddlecomputers.mods.oreplanets.planets.diamondplanet.world.gen.structure.MapGenAbandonedSatellite;
import fiddlecomputers.mods.oreplanets.utils.world.gen.ChunkGeneratorBaseOP;

public class ChunkGeneratorDiamondPlanet extends ChunkGeneratorBaseOP
{
    private final MapGenAbandonedSatellite abandonedSatellite = new MapGenAbandonedSatellite();

    public ChunkGeneratorDiamondPlanet(World world, long seed)
    {
        super(world, seed);
    }

    @Override
    protected void preGenerateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.createCraters(chunkX, chunkZ, primer);
    }

    @Override
    protected void generateChunk(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        this.abandonedSatellite.generate(this.world, chunkX, chunkZ, primer);
    }

    @Override
    protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z)
    {
        this.abandonedSatellite.generateStructure(this.world, this.rand, chunkpos);
    }

    @Override
    public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
    {
        this.abandonedSatellite.generate(this.world, chunkX, chunkZ, null);
    }

    @Override
    public boolean isInsideStructure(World world, String name, BlockPos pos)
    {
    	if ("AbandonedSatellite".equals(name) && this.abandonedSatellite != null)
        {
            return this.abandonedSatellite.isInsideStructure(pos);
        }
    	
    	return false;
    }

    @Override
    @Nullable
    public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored)
    {
    	if ("AbandonedSatellite".equals(name) && this.abandonedSatellite != null)
        {
            return this.abandonedSatellite.getNearestStructurePos(world, pos, findUnexplored);
        }
    	
    	return null;
    }

    @Override
    protected List<IBlockState> getTopBlocks()
    {
    	List<IBlockState> returnList = new ArrayList<IBlockState>();
    	returnList.add(Blocks.SAND.getDefaultState());
        return returnList;
    }

    @Override
    protected List<IBlockState> getSubBlocks()
    {
    	List<IBlockState> returnList = new ArrayList<IBlockState>();
    	returnList.add(Blocks.QUARTZ_BLOCK.getDefaultState());
    	returnList.add(Blocks.QUARTZ_BLOCK.getDefaultState());
    	returnList.add(Blocks.QUARTZ_BLOCK.getDefaultState());
    	returnList.add(Blocks.DIAMOND_BLOCK.getDefaultState());
        return returnList;
    }

    @Override
    protected List<IBlockState> getStoneBlocks()
    {
    	List<IBlockState> returnList = new ArrayList<IBlockState>();
    	returnList.add(Blocks.DIAMOND_BLOCK.getDefaultState());
        return returnList;
    }
}