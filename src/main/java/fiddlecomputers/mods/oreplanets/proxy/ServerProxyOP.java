package fiddlecomputers.mods.oreplanets.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import fiddlecomputers.mods.oreplanets.utils.EnumParticleTypesOP;

public class ServerProxyOP
{
    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}

    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z) {}

    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z, Object[] objects) {}

    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z, double motionX, double motionY, double motionZ) {}

    public void spawnParticle(EnumParticleTypesOP type, double x, double y, double z, double motionX, double motionY, double motionZ, Object[] objects) {}

    public void resetFloatingTick(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)player).connection.update();
        }
    }

    public void registerVariant() {}
}