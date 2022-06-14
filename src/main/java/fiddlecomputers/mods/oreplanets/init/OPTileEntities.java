package fiddlecomputers.mods.oreplanets.init;

import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.tileentity.*;

public class OPTileEntities
{
    public static void init()
    {
        OrePlanets.COMMON_REGISTRY.registerTileEntity(TileEntityDummy.class, "dummy");
        OrePlanets.COMMON_REGISTRY.registerTileEntity(TileEntitySpacePortal.class, "space_portal");
    }
}