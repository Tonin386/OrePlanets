package fiddlecomputers.mods.oreplanets.init;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;

public class OPDimensions
{
	public static DimensionType SPACE_NETHER;
    public static DimensionType DIAMONDPLANET;
    public static DimensionType EXANTERRA;

    public static void init()
    {
        OPDimensions.SPACE_NETHER = WorldUtil.getDimensionTypeById(ConfigManagerOP.oreplanets_dimension.idDimensionSpaceNether);
        OPDimensions.DIAMONDPLANET = WorldUtil.getDimensionTypeById(ConfigManagerOP.oreplanets_dimension.idDimensionDiamondPlanet);
        OPDimensions.EXANTERRA = WorldUtil.getDimensionTypeById(ConfigManagerOP.oreplanets_dimension.idDimensionExanterra);
    }
}