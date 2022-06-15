package fiddlecomputers.mods.oreplanets.init;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import net.minecraft.world.biome.Biome;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.planets.diamondplanet.world.gen.BiomeDiamondPlanet;
import fiddlecomputers.mods.oreplanets.planets.exanterra.world.gen.BiomeExanterra;

public class OPBiomes
{
    public static Biome DIAMONDPLANET = new BiomeDiamondPlanet(new Biome.BiomeProperties("DiamondPlanet").setRainfall(0.0F).setTemperature(0.2F));
	public static Biome EXANTERRA = new BiomeExanterra(new Biome.BiomeProperties("Exanterra").setRainfall(0.0F).setTemperature(0.9F));
    
    public static void init()
    {
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.DIAMONDPLANET, "DiamondPlanet");
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.EXANTERRA, "Exanterra");
    }

    public static void registerTypes()
    {
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.DIAMONDPLANET, COLD, DEAD, DRY);
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.EXANTERRA, COLD, DEAD, DRY);
    }
}