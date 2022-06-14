package fiddlecomputers.mods.oreplanets.capability;


import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface AbstractCapabilityDataOP
{
    static AbstractCapabilityDataOP get(Entity entity)
    {
        return entity.getCapability(CapabilityProviderOP.MORE_PLANETS_CAP, null);
    }

    void writeNBT(NBTTagCompound nbt);

    void readNBT(NBTTagCompound nbt);
}