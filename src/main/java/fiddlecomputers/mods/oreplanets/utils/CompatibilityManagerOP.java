package fiddlecomputers.mods.oreplanets.utils;

import java.lang.reflect.Method;

import net.minecraftforge.fml.common.Loader;

public class CompatibilityManagerOP
{
    public static final String baublesModId = "baubles";
    public static final boolean isCTMLoaded = Loader.isModLoaded("ctm");
    public static final boolean isBigReactorLoaded = Loader.isModLoaded("bigreactors");
    public static final boolean isOpenBlocksLoaded = Loader.isModLoaded("openblocks");
    public static final boolean isEnderIOLoaded = Loader.isModLoaded("enderio");
    public static final boolean isMobGrindingUtilsLoaded = Loader.isModLoaded("mob_grinding_utils");
    public static final boolean isCCLLoaded = Loader.isModLoaded("codechickenlib");
    public static final boolean isBaubleLoaded = Loader.isModLoaded("baubles");

    // Extreme Reactors
    private static Method erRegisterFluid;
    private static float conductivityEmerald;
    private static float conductivityCopper;
    private static float conductivityGold;

    public static void init()
    {
        if (CompatibilityManagerOP.isCTMLoaded)
        {
            LoggerOP.info("Enabled CTM integrations");
        }
        if (CompatibilityManagerOP.isBigReactorLoaded)
        {
            CompatibilityManagerOP.initBigReactorCompat();
            CompatibilityManagerOP.registerExtremeReactorFluid("infected_water_fluid", 0.45F, 0.925F, 1.25F, CompatibilityManagerOP.conductivityCopper);
            CompatibilityManagerOP.registerExtremeReactorFluid("purify_water_fluid", 0.525F, 0.75F, 4.0F, CompatibilityManagerOP.conductivityEmerald);
            CompatibilityManagerOP.registerExtremeReactorFluid("cheese_milk_fluid", 0.675F, 0.575F, 1.95F, CompatibilityManagerOP.conductivityGold);
            CompatibilityManagerOP.registerExtremeReactorFluid("infected_purlonite_water_fluid", 0.3F, 0.65F, 2.45F, CompatibilityManagerOP.conductivityCopper);
        }
    }

    public static boolean isModAddedXpFluid()
    {
        return !CompatibilityManagerOP.isOpenBlocksLoaded && !CompatibilityManagerOP.isEnderIOLoaded && !CompatibilityManagerOP.isMobGrindingUtilsLoaded;
    }

    public static void registerExtremeReactorFluid(String name, float absorption, float heatEfficiency, float moderation, float heatConductivity)
    {
        LoggerOP.info("Registering {} into reactor interior", name);

        try
        {
            CompatibilityManagerOP.erRegisterFluid.invoke(null, name, absorption, heatEfficiency, moderation, heatConductivity);
        }
        catch (Exception e) {}
    }

    private static void initBigReactorCompat()
    {
        LoggerOP.info("Enabled Extreme Reactors integrations");

        try
        {
            Class<?> reactorInterior = Class.forName("erogenousbeef.bigreactors.api.registry.ReactorInterior");
            Class<?> iHeatEntity = Class.forName("erogenousbeef.bigreactors.api.IHeatEntity");
            CompatibilityManagerOP.erRegisterFluid = reactorInterior.getDeclaredMethod("registerFluid", String.class, float.class, float.class, float.class, float.class);
            CompatibilityManagerOP.conductivityEmerald = iHeatEntity.getDeclaredField("conductivityEmerald").getFloat(iHeatEntity);
            CompatibilityManagerOP.conductivityCopper = iHeatEntity.getDeclaredField("conductivityCopper").getFloat(iHeatEntity);
            CompatibilityManagerOP.conductivityGold = iHeatEntity.getDeclaredField("conductivityGold").getFloat(iHeatEntity);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}