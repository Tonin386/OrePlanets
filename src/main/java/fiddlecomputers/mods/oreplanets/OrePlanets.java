package fiddlecomputers.mods.oreplanets;

import fiddlecomputers.mods.oreplanets.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        useMetadata = true,
        version = Reference.MOD_VERSION,
        dependencies = Reference.MOD_DEPENDENCIES,
        acceptedMinecraftVersions = Reference.MOD_MC_VERSION_RANGE
)
public class OrePlanets {
    
    @SidedProxy(clientSide = "fiddlecomputers.mods.oreplanets.proxy.ClientProxy", serverSide = "fiddlecomputers.mods.oreplanets.proxy.CommonProxy")
    public static IProxy proxy;
	
	@Instance(Reference.MOD_ID)
	public static OrePlanets _instance;
    public OrePlanets() {}
        
    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
    	proxy.init(event);
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }
    
    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent event) {
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
    }
}
