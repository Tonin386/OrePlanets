package fiddlecomputers.mods.oreplanets.planets.diamondstar.world.gen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;
import fiddlecomputers.mods.oreplanets.init.OPBiomes;

public class BiomeProviderDiamondStar extends BiomeProviderSpace
{
    @Override
    public Biome getBiome()
    {
        return OPBiomes.DIAMONDSTAR;
    }
}