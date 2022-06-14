package fiddlecomputers.mods.oreplanets.planets.diamondplanet.world.gen;

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
//import stevekung.mods.stevekunglib.world.gen.MapGenCavesBase;
//import stevekung.mods.stevekunglib.world.gen.WorldGenLiquidLake;

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
        //int y = this.rand.nextInt(this.rand.nextInt(248) + 8);
        this.abandonedSatellite.generateStructure(this.world, this.rand, chunkpos);

//        if (this.rand.nextInt(4) == 0)
//        {
//            if (y < 48)
//            {
//                new WorldGenLiquidLake(MPBlocks.INFECTED_PURLONITE_WATER_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), false).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
//            }
//        }
//        if (this.rand.nextInt(8) == 0)
//        {
//            if (y < 48)
//            {
//                new WorldGenLiquidLake(MPBlocks.INFECTED_PURLONITE_LAVA_FLUID_BLOCK.getDefaultState(), MPBlocks.DIONA_ROCK.getDefaultState(), true).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, y, this.rand.nextInt(16) + 8));
//            }
//        }
//        for (int i = 0; i < 8; ++i)
//        {
//            new WorldGenSpaceDungeons(MPBlocks.DIONA_ANCIENT_CHEST.getDefaultState(), MPBlocks.DIONA_COBBLESTONE.getDefaultState(), MPBlocks.ALBETIUS_WORM_EGG_ROCK.getDefaultState()).generate(this.world, this.rand, pos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(256), this.rand.nextInt(16) + 8));
//        }
//        if (this.rand.nextInt(250) == 0)
//        {
//            int posX = this.rand.nextInt(16) + 8;
//            int posZ = this.rand.nextInt(16) + 8;
//            int posY = this.world.getTopSolidOrLiquidBlock(pos.add(posX, 0, posZ)).getY();
//            new WorldGenCrashedAlienProbe().generate(this.world, this.rand, pos.add(posX, posY, posZ));
//        }
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
    protected IBlockState getTopBlock()
    {
        return Blocks.DIAMOND_BLOCK.getDefaultState();
    }

    @Override
    protected IBlockState getSubBlock()
    {
        return Blocks.DIAMOND_BLOCK.getDefaultState();
    }

    @Override
    protected IBlockState getStoneBlock()
    {
        return Blocks.DIAMOND_BLOCK.getDefaultState();
    }
}