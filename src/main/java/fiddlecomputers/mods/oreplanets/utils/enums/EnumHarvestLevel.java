package fiddlecomputers.mods.oreplanets.utils.enums;

public enum EnumHarvestLevel
{
    PICKAXE,
    AXE,
    SHOVEL;

    @Override
    public String toString()
    {
        return this.name().toLowerCase();
    }
}