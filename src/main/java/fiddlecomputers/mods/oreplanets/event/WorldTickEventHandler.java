package fiddlecomputers.mods.oreplanets.event;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import fiddlecomputers.mods.oreplanets.data.WorldDataSurvivalPlanet;

public class WorldTickEventHandler
{
    public static WorldDataSurvivalPlanet survivalPlanetData = null;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        this.loadPlanetData();
    }

    @SubscribeEvent
    public void onClientConnectServer(FMLNetworkEvent.ServerConnectionFromClientEvent event)
    {
        this.loadPlanetData();
    }

    private void loadPlanetData()
    {
        World world = WorldUtil.getWorldForDimensionServer(0);

        if (world == null)
        {
            return;
        }

        WorldTickEventHandler.survivalPlanetData = (WorldDataSurvivalPlanet) world.loadData(WorldDataSurvivalPlanet.class, WorldDataSurvivalPlanet.saveDataID);

        if (WorldTickEventHandler.survivalPlanetData == null)
        {
            WorldTickEventHandler.survivalPlanetData = new WorldDataSurvivalPlanet(WorldDataSurvivalPlanet.saveDataID);
            world.setData(WorldDataSurvivalPlanet.saveDataID, WorldTickEventHandler.survivalPlanetData);
        }
    }
}