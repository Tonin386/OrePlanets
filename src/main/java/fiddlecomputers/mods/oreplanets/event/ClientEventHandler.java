package fiddlecomputers.mods.oreplanets.event;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.api.event.client.CelestialBodyRenderEvent;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore.EventSpecialRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;
import fiddlecomputers.mods.oreplanets.utils.itemblocks.IItemRarity;
import fiddlecomputers.mods.oreplanets.utils.ColorUtils;
import fiddlecomputers.mods.oreplanets.utils.client.ClientUtils;
import fiddlecomputers.mods.oreplanets.utils.client.event.AddRainParticleEvent;
import fiddlecomputers.mods.oreplanets.utils.client.event.FirstPersonViewOverlayEvent;
import fiddlecomputers.mods.oreplanets.utils.client.event.RenderEntityOverlayEvent;

public class ClientEventHandler
{
    private Minecraft mc;
    private boolean firstWorldJoin;
    private boolean initVersionCheck;
    public static final List<BlockPos> RECEIVER_RENDER_POS = new ArrayList<>();
    public static final List<BlockPos> WASTE_RENDER_POS = new ArrayList<>();

    public static int ticks;
    public static int ticksPaused;
    public static float renderPartialTicks;

    public ClientEventHandler()
    {
        this.mc = Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
    {
        this.firstWorldJoin = false;
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (WorldTickEventHandler.survivalPlanetData == null)
        {
            return;
        }
        if (ConfigManagerOP.oreplanets_general.enableSurvivalPlanetSelection && event.getEntity() == this.mc.player && !this.firstWorldJoin && !WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData && !WorldTickEventHandler.survivalPlanetData.disableMessage)
        {
            ITextComponent component = new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(new TextComponentTranslation("message.survival_planet.1").appendText(" ").appendSibling(new TextComponentTranslation("message.survival_planet.2").setStyle(new Style().setColor(TextFormatting.AQUA))).appendText(" ").appendSibling(new TextComponentTranslation("message.survival_planet.3")).setStyle(new Style().setColor(TextFormatting.YELLOW)));
            component.getStyle().setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/mpcelestial"));
            ClientUtils.printClientMessage(component);
            ITextComponent component2 = new TextComponentTranslation("message.survival_planet.4").appendText(" ").appendSibling(new TextComponentTranslation("message.survival_planet.5").setStyle(new Style().setColor(TextFormatting.RED))).setStyle(new Style().setColor(TextFormatting.YELLOW));
            component2.getStyle().setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/mpcelestial disable"));
            ClientUtils.printClientMessage(component2);
            this.firstWorldJoin = true;
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderWorldLast(RenderWorldLastEvent event)
    {
    	
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(ClientTickEvent event)
    {
        if (this.mc.currentScreen instanceof GuiMainMenu)
        {
            ClientEventHandler.ticks = 0;
            ClientEventHandler.ticksPaused = 0;
        }
        if (event.phase == Phase.START)
        {
            ClientEventHandler.ticks++;
            ClientEventHandler.renderPartialTicks = ClientEventHandler.ticks + this.mc.getRenderPartialTicks();

            if (!this.mc.isGamePaused())
            {
                ClientEventHandler.ticksPaused++;
            }
        }
        if (OrePlanets.isDevelopment)
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_F7))
            {
                try
                {
                    // used for real time debugging item description
                    Object proxy = Class.forName("mezz.jei.JustEnoughItems").getDeclaredMethod("getProxy").invoke(Class.forName("mezz.jei.startup.ProxyCommonClient"));
                    Field pluginsField = proxy.getClass().getDeclaredField("plugins");
                    pluginsField.setAccessible(true);
                    Class<?> starter = Class.forName("mezz.jei.startup.JeiStarter");
                    Object obj = starter.newInstance();
                    Method method = obj.getClass().getDeclaredMethod("start", List.class);
                    method.invoke(obj, (ArrayList<?>) pluginsField.get(proxy));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5) && !this.mc.player.getHeldItemMainhand().isEmpty())
            {
                GuiScreen.setClipboardString(this.mc.player.getHeldItemMainhand().getDisplayName());
            }
        }
        if (this.mc.currentScreen instanceof GuiMainMenu)
        {
            ClientEventHandler.RECEIVER_RENDER_POS.clear();
            ClientEventHandler.WASTE_RENDER_POS.clear();
        }
        if (event.phase == Phase.START)
        {
            if (this.mc.player != null)
            {
                if (!this.initVersionCheck)
                {
                    OrePlanets.CHECKER.startCheckIfFailed();

                    if (ConfigManagerOP.oreplanets_general.enableVersionChecker)
                    {
                        OrePlanets.CHECKER.printInfo(this.mc.player);
                    }
                    this.initVersionCheck = true;
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerTick(PlayerTickEvent event)
    {
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderSpecial(EventSpecialRender event)
    {
    }

    @SubscribeEvent
    public void onRenderEntityOverlay(RenderEntityOverlayEvent event)
    {
    	
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderFirstPersonViewOverlay(FirstPersonViewOverlayEvent event)
    {
    	
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onBossBarRender(RenderGameOverlayEvent.BossInfo event)
    {
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
    {
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderFog(FogColors event)
    {
    }

    @SubscribeEvent
    public void onRenderCelestialPost(CelestialBodyRenderEvent.Post event)
    {
        if (this.mc.currentScreen instanceof GuiCelestialSelection)
        {
            GuiCelestialSelection gui = (GuiCelestialSelection) this.mc.currentScreen;
            boolean enable = true;
            float partialTicks = fiddlecomputers.mods.oreplanets.event.ClientEventHandler.renderPartialTicks;

            if (event.celestialBody == GalacticraftCore.planetOverworld && enable)
            {
                float size = gui.getWidthForCelestialBody(event.celestialBody) / 16.0F;
                float orbitTick = MathHelper.sin(partialTicks * 0.2F) / 10.0F + 0.5F;
                GlStateManager.translate(6.0F, orbitTick + -6.5F, 0.0F);
                this.mc.renderEngine.bindTexture(new ResourceLocation("moreplanets:textures/gui/celestialbodies/ion_cannon.png"));
                gui.drawTexturedModalRect(-7.5F * size, -1.75F * size, 2.0F, 2.0F, 0, 0, 32, 32, false, false, 32, 32);
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onAddRainParticle(AddRainParticleEvent event)
    {
    }
}