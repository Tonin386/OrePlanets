package fiddlecomputers.mods.oreplanets.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import fiddlecomputers.mods.oreplanets.Reference;
import fiddlecomputers.mods.oreplanets.utils.CommonUtils;

public class CapabilityHandlerOP
{
    private static final ResourceLocation CONSTANT = new ResourceLocation(Reference.MOD_ID, "more_planets_data");

    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(CapabilityHandlerOP.CONSTANT, new CapabilityProviderOP());
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        /*if (event.isWasDeath())
        {
            EntityPlayer player = event.getEntityPlayer();
            MorePlanetsCapabilityData currentData = player.getCapability(CapabilityProviderOP.MORE_PLANETS_CAP, null);
            MorePlanetsCapabilityData oldData = event.getOriginal().getCapability(CapabilityProviderOP.MORE_PLANETS_CAP, null);
            currentData.setStartCelestial(oldData.getStartCelestial());
        }*/
    }

    public static void register()
    {
        CapabilityManager.INSTANCE.register(AbstractCapabilityDataOP.class, new Capability.IStorage<AbstractCapabilityDataOP>()
        {
            @Override
            public NBTBase writeNBT(Capability<AbstractCapabilityDataOP> capability, AbstractCapabilityDataOP instance, EnumFacing side)
            {
                NBTTagCompound nbt = new NBTTagCompound();
                instance.writeNBT(nbt);
                return nbt;
            }

            @Override
            public void readNBT(Capability<AbstractCapabilityDataOP> capability, AbstractCapabilityDataOP instance, EnumFacing side, NBTBase nbt)
            {
                instance.readNBT((NBTTagCompound) nbt);
            }
        }, CapabilityDataOP::new);
        CommonUtils.registerEventHandler(new CapabilityHandlerOP());
    }
}