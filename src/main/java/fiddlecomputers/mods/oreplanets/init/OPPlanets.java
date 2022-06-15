package fiddlecomputers.mods.oreplanets.init;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeMoon;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;
import fiddlecomputers.mods.oreplanets.init.OPPlanets;
import fiddlecomputers.mods.oreplanets.dimension.*;
import fiddlecomputers.mods.oreplanets.utils.CelestialRegistryUtils;


public class OPPlanets {
    public static Planet DIAMONDPLANET;
    public static Planet EXANTERRA;
    public static Planet AURAFERRUM;
 
    public static void init()
    {
        // Planets
        OPPlanets.DIAMONDPLANET = CelestialRegistryUtils.createPlanet("diamondplanet", GalacticraftCore.solarSystemSol, -14.25F, 4.25F, 20.0F, 0.876F, ConfigManagerOP.oreplanets_planet_settings.planetDiamondPlanetTier, ConfigManagerOP.oreplanets_dimension.idDimensionDiamondPlanet, WorldProviderDiamondPlanet.class);
        OPPlanets.EXANTERRA = CelestialRegistryUtils.createPlanet("exanterra", GalacticraftCore.solarSystemSol, 10F, 1.15F, 1.3F, 0.5F, ConfigManagerOP.oreplanets_planet_settings.planetExanterraTier, ConfigManagerOP.oreplanets_dimension.idDimensionExanterra, WorldProviderExanterra.class);
        OPPlanets.AURAFERRUM = CelestialRegistryUtils.createPlanet("auraferrum", GalacticraftCore.solarSystemSol, 0.65F, 0.65F, 0.65F, 2F, ConfigManagerOP.oreplanets_planet_settings.planetAuraferrumTier, ConfigManagerOP.oreplanets_dimension.idDimensionAuraferrum, WorldProviderAuraferrum.class);
        
        // Planets
        CelestialRegistryUtils.setAtmosphereComponentList(OPPlanets.DIAMONDPLANET, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(OPPlanets.DIAMONDPLANET, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegistryUtils.setChecklistKeys(OPPlanets.DIAMONDPLANET, "equip_oxygen_suit");
        
        CelestialRegistryUtils.setAtmosphereComponentList(OPPlanets.EXANTERRA, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(OPPlanets.EXANTERRA, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegistryUtils.setChecklistKeys(OPPlanets.EXANTERRA, "equip_oxygen_suit");
        
        CelestialRegistryUtils.setAtmosphereComponentList(OPPlanets.AURAFERRUM, EnumAtmosphericGas.ARGON, EnumAtmosphericGas.HELIUM);
        CelestialRegistryUtils.setAtmosphere(OPPlanets.AURAFERRUM, false, false, false, 0.0F, 0.0F, 0.0F);
        CelestialRegistryUtils.setChecklistKeys(OPPlanets.AURAFERRUM, "equip_oxygen_suit");
    }

    public static void register()
    {
        TeleportTypeMoon teleport = new TeleportTypeMoon();

        CelestialRegistryUtils.registerSolarSystem(GalacticraftCore.solarSystemSol);

        CelestialRegistryUtils.registerPlanet(OPPlanets.DIAMONDPLANET);
        CelestialRegistryUtils.registerTeleportType(WorldProviderDiamondPlanet.class, teleport);
        CelestialRegistryUtils.registerRocketGui(WorldProviderDiamondPlanet.class, "diamondplanet");

        CelestialRegistryUtils.registerPlanet(OPPlanets.EXANTERRA);
        CelestialRegistryUtils.registerTeleportType(WorldProviderExanterra.class, teleport);
        CelestialRegistryUtils.registerRocketGui(WorldProviderExanterra.class, "exanterra");

        CelestialRegistryUtils.registerPlanet(OPPlanets.AURAFERRUM);
        CelestialRegistryUtils.registerTeleportType(WorldProviderAuraferrum.class, teleport);
        CelestialRegistryUtils.registerRocketGui(WorldProviderAuraferrum.class, "auraferrum");
    }

}
