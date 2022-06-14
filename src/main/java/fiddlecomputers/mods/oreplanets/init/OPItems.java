package fiddlecomputers.mods.oreplanets.init;

import net.minecraft.item.Item;
import fiddlecomputers.mods.oreplanets.utils.BlocksItemsRegistry;
import fiddlecomputers.mods.oreplanets.utils.items.ItemDungeonKeyOP;

public class OPItems
{
    public static Item DIAMONDPLANET_DUNGEON_KEY;
    
    public static void init()
    {
        OPItems.DIAMONDPLANET_DUNGEON_KEY = new ItemDungeonKeyOP("diamondplanet_dungeon_key", 4);
        
        OPItems.preRegister();
        OPItems.register();
        OPItems.postRegister();
    }

    private static void preRegister()
    {
    	
    }

    private static void register()
    {
        BlocksItemsRegistry.registerItem(OPItems.DIAMONDPLANET_DUNGEON_KEY);
    }

    private static void postRegister()
    {
    	
    }
}