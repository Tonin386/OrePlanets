package fiddlecomputers.mods.oreplanets.init;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import net.minecraft.world.biome.Biome;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.planets.diamondstar.world.gen.BiomeDiamondStar;

public class OPBiomes
{
    public static Biome DIAMONDSTAR = new BiomeDiamondStar(new Biome.BiomeProperties("DiamondStar").setRainfall(0.0F).setTemperature(0.2F));
    
    public static void init()
    {
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.DIAMONDSTAR, "DiamondStar");
    }

    public static void registerTypes()
    {
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.DIAMONDSTAR, COLD, DEAD, DRY);
    }
}