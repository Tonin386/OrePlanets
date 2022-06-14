package fiddlecomputers.mods.oreplanets.utils;
import java.util.Locale;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;

public class CelestialRegistryUtils
{
    public static Planet createPlanet(String name, SolarSystem solar, float phaseShift, float distance, float orbitTime, float size, int tier, int id, Class<? extends WorldProvider> provider)
    {
        Planet planet = new Planet(name).setParentSolarSystem(solar);
        planet.setDimensionInfo(id, provider);
        planet.setPhaseShift(phaseShift);
        planet.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
        planet.setRelativeOrbitTime(orbitTime);
        planet.setRelativeSize(size);
        planet.setTierRequired(tier);
        planet.setBodyIcon(new ResourceLocation("oreplanets:textures/gui/celestialbodies/" + name + ".png"));
        return planet;
    }

    public static Moon createMoon(String name, Planet planet, float phaseShift, float distance, float orbitTime, float size, int tier, int id, Class<? extends WorldProvider> provider)
    {
        Moon moon = new Moon(name).setParentPlanet(planet);
        moon.setDimensionInfo(id, provider);
        moon.setPhaseShift(phaseShift);
        moon.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
        moon.setRelativeOrbitTime(orbitTime);
        moon.setRelativeSize(size);
        moon.setTierRequired(tier);
        moon.setBodyIcon(new ResourceLocation("oreplanets:textures/gui/celestialbodies/" + name + ".png"));
        return moon;
    }

    public static Satellite createSpaceStation(String name, Planet planet, float phaseShift, float distance, float orbitTime, float size, int tier)
    {
        Satellite satellite = new Satellite(name).setParentBody(planet);
        satellite.setPhaseShift(phaseShift);
        satellite.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
        satellite.setRelativeOrbitTime(orbitTime);
        satellite.setRelativeSize(size);
        satellite.setTierRequired(tier);
        satellite.setBodyIcon(new ResourceLocation("oreplanets:textures/gui/celestialbodies/" + name + ".png"));
        return satellite;
    }

    public static SolarSystem createStarAndSolarSystem(SolarSystem solarSystem, Star star, String solarName, String starName, Vector3 galaxyPos, String starResource)
    {
        solarSystem = new SolarSystem(solarName, "milky_way").setMapPosition(galaxyPos);
        star = new Star(starName).setParentSolarSystem(solarSystem);
        star.setTierRequired(-1);
        star.setBodyIcon(new ResourceLocation("oreplanets:textures/gui/celestialbodies/" + starResource + ".png"));
        solarSystem.setMainStar(star);
        return solarSystem;
    }

    public static void setAtmosphereComponentList(CelestialBody celestial, EnumAtmosphericGas... gasList)
    {
        for (EnumAtmosphericGas gas : gasList)
        {
            celestial.atmosphereComponent(gas);
        }
    }

    public static void setAtmosphere(CelestialBody celestial, Boolean breathable, boolean precipitation, boolean corrosive, float relativeTemperature, float windLevel, float density)
    {
        celestial.setAtmosphere(new AtmosphereInfo(breathable, precipitation, corrosive, relativeTemperature, windLevel, density));
    }

    public static void setChecklistKeys(CelestialBody celestial, String... keys)
    {
        celestial.addChecklistKeys(keys);
    }

    public static void registerProvider(String name, int id, int staticId, Class<? extends WorldProvider> provider)
    {
        GalacticraftRegistry.registerDimension(name, "_" + name.toLowerCase(Locale.ROOT), id, provider, false);
        GalacticraftRegistry.registerDimension(name, "_" + name.toLowerCase(Locale.ROOT), staticId, provider, true);
    }

    public static void registerSolarSystem(SolarSystem solarSystem)
    {
        GalaxyRegistry.registerSolarSystem(solarSystem);
    }

    public static void registerPlanet(Planet planet)
    {
        GalaxyRegistry.registerPlanet(planet);
    }

    public static void registerMoon(Moon moon)
    {
        GalaxyRegistry.registerMoon(moon);
    }

    public static void registerTeleportType(Class<? extends WorldProvider> clazz, ITeleportType type)
    {
        GalacticraftRegistry.registerTeleportType(clazz, type);
    }

    public static void registerRocketGui(Class<? extends WorldProvider> clazz, String resource)
    {
        GalacticraftRegistry.registerRocketGui(clazz, new ResourceLocation("oreplanets:textures/gui/rocket/" + resource + ".png"));
    }
}