package fiddlecomputers.mods.oreplanets.config;

import net.minecraftforge.common.config.Config;
import fiddlecomputers.mods.oreplanets.Reference;

@Config(modid = Reference.MOD_ID)
public class ConfigManagerOP
{
    @Config.LangKey(value = "oreplanets_general")
    @Config.Comment(value = "Based Ore Planets Configuration. Version Checker, Debug, Mod Option. etc.")
    public static final General oreplanets_general = new General();

    @Config.LangKey(value = "oreplanets_dimension")
    @Config.Comment(value = "Planet or Moon Dimension IDs Configuration.")
    public static final Dimension oreplanets_dimension = new Dimension();

    @Config.LangKey(value = "oreplanets_planet_settings")
    @Config.Comment(value = "Planet Configuration.")
    public static final PlanetSettings oreplanets_planet_settings = new PlanetSettings();

    @Config.LangKey(value = "oreplanets_world_gen")
    @Config.Comment(value = "World Gen Configuration.")
    public static final WorldGenSettings oreplanets_world_gen_settings = new WorldGenSettings();

    @Config.LangKey(value = "oreplanets_other")
    @Config.Comment(value = "Others Configuration.")
    public static final Other oreplanets_other = new Other();

    // General
    public static class General
    {
        @Config.Name(value = "Enable Debug Logging")
        public boolean enableDebug = false;

        @Config.Name(value = "Enable Version Checker")
        @Config.RequiresMcRestart
        public boolean enableVersionChecker = true;

        @Config.Name(value = "Enable Night Vision Effect while boss fight")
        public boolean enableNightVisionEffect = false;

        @Config.Name(value = "Enable Black Hole Explosion")
        public boolean enableBlackHoleExplosion = true;

        @Config.Name(value = "Enable Survival Planet Selection")
        @Config.RequiresMcRestart
        public boolean enableSurvivalPlanetSelection = false;

        @Config.Name(value = "Use 3D Item Model for Torch")
        @Config.RequiresMcRestart
        public boolean use3DTorchItemModel = true;

        @Config.Name(value = "Use Colored Star in the Sky")
        @Config.RequiresWorldRestart
        public boolean useColoredStar = true;

        @Config.Name(value = "Use Fancy Star in the Sky")
        @Config.RequiresWorldRestart
        public boolean useFancyStar = true;
    }

    // Dimensions
    public static class Dimension
    {
        @Config.Name(value = "Space Nether Dimension ID")
        public int idDimensionSpaceNether = -2541;

        @Config.Name(value = "Diamond Planet Dimension ID")
        public int idDimensionDiamondPlanet = -2542;
    }

    // Planet Settings
    public static class PlanetSettings
    {
        @Config.Name(value = "Diamond Planet Planet Tier")
        @Config.RequiresMcRestart
        public int planetDiamondPlanetTier = 3;
    }

    // World Gen Settings
    public static class WorldGenSettings
    {
        @Config.Name(value = "Enable All Common Ore Gen on all planets")
        @Config.Comment(value = "Common Ores are Copper, Tin, Aluminum, vanilla ores.")
        public boolean enableCommonOreGenAllPlanets = true;

        @Config.Name(value = "Enable Common Ore on Diamond Planet")
        public boolean enableCommonDiamondPlanetOre = false;
    }

    // Others
    public static class Other
    {
        @Config.Name(value = "Base Schematic ID")
        public int idBaseSchematic = 850;

        @Config.Name(value = "Base Schematic GUI ID")
        public int idBaseSchematicGui = 550;

        @Config.Name(value = "Enable Description in Waila Tooltip")
        public boolean enableDescriptionInWaila = false;
    }
}