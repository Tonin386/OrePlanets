package fiddlecomputers.mods.oreplanets.init;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import net.minecraft.world.biome.Biome;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.planets.diamondplanet.world.gen.BiomeDiamondPlanet;

public class OPBiomes
{
    public static Biome DIAMONDPLANET = new BiomeDiamondPlanet(new Biome.BiomeProperties("DiamondPlanet").setRainfall(0.0F).setTemperature(0.2F));
    
    public static void init()
    {
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.DIAMONDPLANET, "DiamondPlanet");
    }

    public static void registerTypes()
    {
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.DIAMONDPLANET, COLD, DEAD, DRY);
    }
}