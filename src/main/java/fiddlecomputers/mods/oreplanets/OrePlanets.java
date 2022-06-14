package fiddlecomputers.mods.oreplanets;

import com.google.common.collect.Lists;

import fiddlecomputers.mods.oreplanets.capability.CapabilityHandlerOP;
import fiddlecomputers.mods.oreplanets.command.CommandOpenCelestialScreen;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;
import fiddlecomputers.mods.oreplanets.event.ClientEventHandler;
import fiddlecomputers.mods.oreplanets.event.EntityEventHandler;
import fiddlecomputers.mods.oreplanets.event.GeneralEventHandler;
import fiddlecomputers.mods.oreplanets.event.WorldTickEventHandler;
import fiddlecomputers.mods.oreplanets.handler.DataFixersOP;
import fiddlecomputers.mods.oreplanets.handler.GuiHandlerOP;
import fiddlecomputers.mods.oreplanets.handler.MissingMappingHandler;
import fiddlecomputers.mods.oreplanets.init.*;
import fiddlecomputers.mods.oreplanets.network.PacketSimpleOP;
import fiddlecomputers.mods.oreplanets.recipe.CraftingManagerOP;
import fiddlecomputers.mods.oreplanets.recipe.SmeltingManagerOP;
import fiddlecomputers.mods.oreplanets.utils.BlocksItemsRegistry;
import fiddlecomputers.mods.oreplanets.utils.CommonRegistryUtils;
import fiddlecomputers.mods.oreplanets.utils.CommonUtils;
import fiddlecomputers.mods.oreplanets.utils.CompatibilityManagerOP;
import fiddlecomputers.mods.oreplanets.utils.LoggerOP;
import fiddlecomputers.mods.oreplanets.utils.PaintingOP;
import fiddlecomputers.mods.oreplanets.utils.VersionChecker;
import fiddlecomputers.mods.oreplanets.utils.client.ClientRegistryUtils;
import fiddlecomputers.mods.oreplanets.utils.client.ClientUtils;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import net.minecraft.advancements.FrameType;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import fiddlecomputers.mods.oreplanets.proxy.ServerProxyOP;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        useMetadata = true,
        version = Reference.MOD_VERSION,
        dependencies = Reference.MOD_DEPENDENCIES,
        acceptedMinecraftVersions = Reference.MOD_MC_VERSION_RANGE
)
public class OrePlanets {

    @SidedProxy(clientSide = "fiddlecomputers.mods.oreplanets.proxy.ClientProxyOP", serverSide = "fiddlecomputers.mods.oreplanets.proxy.ServerProxyOP")
    public static ServerProxyOP PROXY;
    
    @Instance(Reference.MOD_ID)
    public static OrePlanets INSTANCE;

    public static VersionChecker CHECKER;
    public static ClientRegistryUtils CLIENT_REGISTRY;
    public static final CommonRegistryUtils COMMON_REGISTRY = new CommonRegistryUtils(Reference.MOD_ID);

    static
    {
        FluidRegistry.enableUniversalBucket();

        try
        {
            OrePlanets.isDevelopment = Launch.classLoader.getClassBytes("net.minecraft.world.World") != null;
        }
        catch (Exception e) {}

        if (ClientUtils.isClient())
        {
            OrePlanets.CLIENT_REGISTRY = new ClientRegistryUtils(Reference.MOD_ID);
        }
    }
    public static boolean isDevelopment;
   
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CompatibilityManagerOP.init();
        OrePlanets.initModInfo(event.getModMetadata());
        CommonUtils.registerEventHandler(this);
        CapabilityHandlerOP.register();

        OPPlanets.init();
        OPPotions.init();
        OPBiomes.init();
        OPOthers.init();
        OPPlanets.register();
        OrePlanets.PROXY.preInit(event);
        OrePlanets.CHECKER = new VersionChecker(OrePlanets.INSTANCE, Reference.MOD_NAME, "");

        if (ConfigManagerOP.oreplanets_general.enableVersionChecker)
        {
            OrePlanets.CHECKER.startCheck();
        }

        EnumHelper.addEnum(FrameType.class, "TASK_PURPLE", new Class[] { String.class, Integer.TYPE, TextFormatting.class }, "task_purple", 0, TextFormatting.DARK_PURPLE);
        PaintingOP.RONG = EnumHelper.addArt("RONG", "rong", 32, 32, 0, 128);

        if (ClientUtils.isClient())
        {
            ClientCommandHandler.instance.registerCommand(new CommandOpenCelestialScreen());
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        OPTileEntities.init();
        OPOreDictionary.init();
        DataFixersOP.init();
        GalacticraftCore.packetPipeline.addDiscriminator(1999, PacketSimpleOP.class);
        OrePlanets.PROXY.init(event);

        if (ClientUtils.isClient())
        {
            BlocksItemsRegistry.postRegisteredSortBlock();
            BlocksItemsRegistry.postRegisteredSortItem();
            CommonUtils.registerEventHandler(new ClientEventHandler());
        }

        CommonUtils.registerEventHandler(new EntityEventHandler());
        CommonUtils.registerEventHandler(new GeneralEventHandler());
        CommonUtils.registerEventHandler(new WorldTickEventHandler());
        CommonUtils.registerEventHandler(new MissingMappingHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        OrePlanets.PROXY.postInit(event);
        CommonUtils.registerGuiHandler(this, new GuiHandlerOP());
        CraftingManagerOP.init();
        SmeltingManagerOP.init();
        OPSchematics.init();
        OPDimensions.init();
    }

    @EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event)
    {
        WorldTickEventHandler.survivalPlanetData = null;
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Reference.MOD_ID))
        {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public void onBiomeRegister(RegistryEvent.Register<Biome> event)
    {
        OPBiomes.registerTypes();
    }

    @SubscribeEvent
    public void onSoundRegister(RegistryEvent.Register<SoundEvent> event)
    {
        LoggerOP.info("Initialize sounds from {}", OPSounds.class);
    }

    private static void initModInfo(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = Reference.MOD_ID;
        info.name = Reference.MOD_NAME;
        info.version = Reference.MOD_VERSION;
        info.description = "An add-on adds new exotic resources planets into Galacticraft!";
        info.url = "";
        info.credits = "All credits goes to Galacticraft Sources/API, Translators and some people who helped.";
        info.authorList = Lists.newArrayList("FiddleComputers");
    }
}
