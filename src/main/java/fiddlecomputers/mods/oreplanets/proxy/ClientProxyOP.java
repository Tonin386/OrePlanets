package fiddlecomputers.mods.oreplanets.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fiddlecomputers.mods.oreplanets.client.renderer.*;
import fiddlecomputers.mods.oreplanets.init.OPSchematics;
import fiddlecomputers.mods.oreplanets.utils.BlocksItemsRegistry;
import fiddlecomputers.mods.oreplanets.utils.CompatibilityManagerOP;
import fiddlecomputers.mods.oreplanets.utils.EnumParticleTypesOP;
import fiddlecomputers.mods.oreplanets.utils.CommonUtils;

public class ClientProxyOP extends ServerProxyOP
{
    @Override
    public void registerVariant()
    {
        VariantsRenderer.init();
        BlockStateMapper.init();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        ModelLoaderRegistry.registerLoader(OBJLoaderOP.INSTANCE);
        EntityRendererOP.init();
        ClientProxyOP.handleCustomSpawning();
        CommonUtils.registerEventHandler(this);

        if (CompatibilityManagerOP.isCCLLoaded)
        {
            ItemModelRenderer.registerCCLRenderer();
        }
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        ItemModelRenderer.init();
        TileEntityRenderer.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        OPSchematics.registerSchematicTexture();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTexturesStitch(TextureStitchEvent.Pre event)
    {
    	
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onModelBake(ModelBakeEvent event)
    {
        for (Item item : BlocksItemsRegistry.TESR_ITEM_RENDER)
        {
            item.setTileEntityItemStackRenderer(TileEntityItemStackRendererOP.INSTANCE);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event)
    {
        this.registerVariant();
    }

    @Override
    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z)
    {
        this.spawnParticle(type, x, y, z, 0.0D, 0.0D, 0.0D, null);
    }

    @Override
    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z, Object[] objects)
    {
        this.spawnParticle(type, x, y, z, 0.0D, 0.0D, 0.0D, objects);
    }

    @Override
    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        this.spawnParticle(type, x, y, z, motionX, motionY, motionZ, null);
    }

    @Override
    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z, double motionX, double motionY, double motionZ, Object[] data)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.getRenderViewEntity() != null && mc.effectRenderer != null && mc.world != null)
        {
            int i = mc.gameSettings.particleSetting;
            double d6 = mc.getRenderViewEntity().posX - x;
            double d7 = mc.getRenderViewEntity().posY - y;
            double d8 = mc.getRenderViewEntity().posZ - z;
            double d9 = 16.0D;

            if (i == 1 && mc.world.rand.nextInt(3) == 0)
            {
                i = 2;
            }
            if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
            {
                return;
            }
            else if (i > 1)
            {
                return;
            }
        }
    }

    private static void handleCustomSpawning()
    {
    	

    }
}