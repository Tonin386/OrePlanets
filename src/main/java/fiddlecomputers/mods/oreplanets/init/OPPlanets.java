package fiddlecomputers.mods.oreplanets.init;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;
import fiddlecomputers.mods.oreplanets.dimension.WorldProviderSpaceNether;
import fiddlecomputers.mods.oreplanets.init.OPPlanets;
import fiddlecomputers.mods.oreplanets.planets.diamondstar.dimension.WorldProviderDiamondStar;
import fiddlecomputers.mods.oreplanets.utils.CelestialRegistryUtils;


public class OPPlanets {
    public static Planet SPACE_NETHER;
    public static Planet DIAMONDSTAR;
 
    public static void init()
    {
        // Planets
        OPPlanets.SPACE_NETHER = CelestialRegistryUtils.createPlanet("space_nether", GalacticraftCore.solarSystemSol, 0.0F, 0.25F, 1.0F, 1.0F, -1, ConfigManagerOP.oreplanets_dimension.idDimensionSpaceNether, WorldProviderSpaceNether.class);
        OPPlanets.DIAMONDSTAR = CelestialRegistryUtils.createPlanet("diamondstar", GalacticraftCore.solarSystemSol, -14.25F, 4.25F, 20.0F, 0.876F, ConfigManagerOP.oreplanets_planet_settings.planetDiamondStarTier, ConfigManagerOP.oreplanets_dimension.idDimensionDiamondStar, WorldProviderDiamondStar.class);
        // Planets
        CelestialRegistryUtils.setAtmosphereComponentList(OPPlanets.DIAMONDSTAR, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(OPPlanets.DIAMONDSTAR, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegistryUtils.setChecklistKeys(OPPlanets.DIAMONDSTAR, "equip_oxygen_suit");
    }

    public static void register()
    {
        TeleportTypeMoon teleport = new TeleportTypeMoon();

        CelestialRegistryUtils.registerSolarSystem(GalacticraftCore.solarSystemSol);

        CelestialRegistryUtils.registerPlanet(OPPlanets.DIAMONDSTAR);
        CelestialRegistryUtils.registerTeleportType(WorldProviderDiamondStar.class, teleport);
        CelestialRegistryUtils.registerRocketGui(WorldProviderDiamondStar.class, "Diamond Star");
    }

}
