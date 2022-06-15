package fiddlecomputers.mods.oreplanets.init;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import net.minecraft.world.biome.Biome;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.planets.auraferrum.moon.world.gen.BiomeAuraferrumMoon;
import fiddlecomputers.mods.oreplanets.planets.auraferrum.world.gen.BiomeAuraferrum;
import fiddlecomputers.mods.oreplanets.planets.diamondplanet.world.gen.BiomeDiamondPlanet;
import fiddlecomputers.mods.oreplanets.planets.exanterra.world.gen.BiomeExanterra;

public class OPBiomes
{
    public static Biome DIAMONDPLANET = new BiomeDiamondPlanet(new Biome.BiomeProperties("DiamondPlanet").setRainfall(0.0F).setTemperature(0.2F));
	public static Biome EXANTERRA = new BiomeExanterra(new Biome.BiomeProperties("Exanterra").setRainfall(0.0F).setTemperature(0.9F));
	public static Biome AURAFERRUM = new BiomeAuraferrum(new Biome.BiomeProperties("Auraferrum").setRainfall(0.0F).setTemperature(1.4F));
	public static Biome AURAFERRUM_MOON = new BiomeAuraferrumMoon(new Biome.BiomeProperties("Auraferrum Moon").setRainfall(0.0F).setTemperature(1.4F));
    
    public static void init()
    {
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.DIAMONDPLANET, "DiamondPlanet");
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.EXANTERRA, "Exanterra");
        OrePlanets.COMMON_REGISTRY.registerBiome(OPBiomes.AURAFERRUM, "Auraferrum");
        OrePlanets.COMMON_REGISTRY.registerBiome(AURAFERRUM_MOON, "Auraferrum Moon");
    }

    public static void registerTypes()
    {
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.DIAMONDPLANET, COLD, DEAD, DRY);
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.EXANTERRA, COLD, DEAD, DRY);
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.AURAFERRUM, HOT, DEAD, DRY);
        OrePlanets.COMMON_REGISTRY.registerBiomeType(OPBiomes.AURAFERRUM_MOON, COLD, DEAD, DRY);
    }
}