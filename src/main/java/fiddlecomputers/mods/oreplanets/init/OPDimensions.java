package fiddlecomputers.mods.oreplanets.init;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;

public class OPDimensions
{
    public static DimensionType DIAMONDPLANET;
    public static DimensionType EXANTERRA;
    public static DimensionType AURAFERRUM;

    public static void init()
    {
        OPDimensions.DIAMONDPLANET = WorldUtil.getDimensionTypeById(ConfigManagerOP.oreplanets_dimension.idDimensionDiamondPlanet);
        OPDimensions.EXANTERRA = WorldUtil.getDimensionTypeById(ConfigManagerOP.oreplanets_dimension.idDimensionExanterra);
        OPDimensions.AURAFERRUM = WorldUtil.getDimensionTypeById(ConfigManagerOP.oreplanets_dimension.idDimensionAuraferrum);
    }
}