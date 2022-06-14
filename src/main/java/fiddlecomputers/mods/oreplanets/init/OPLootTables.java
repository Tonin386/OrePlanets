package fiddlecomputers.mods.oreplanets.init;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import net.minecraft.item.ItemStack;

public class OPLootTables
{
    public static ItemStack getTieredKey(Random rand, int tier)
    {
        return GalacticraftRegistry.getDungeonLoot(tier).get(rand.nextInt(GalacticraftRegistry.getDungeonLoot(tier).size())).copy();
    }
}