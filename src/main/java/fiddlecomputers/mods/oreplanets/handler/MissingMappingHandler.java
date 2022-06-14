package fiddlecomputers.mods.oreplanets.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class MissingMappingHandler
{
    @SubscribeEvent
    public void onBlockMissingMappings(RegistryEvent.MissingMappings<Block> event)
    {
    	
    }

    @SubscribeEvent
    public void onItemMissingMappings(RegistryEvent.MissingMappings<Item> event)
    {
    	
    }

    @SubscribeEvent
    public void onPotionMissingMappings(RegistryEvent.MissingMappings<Potion> event)
    {
    	
    }

    @SubscribeEvent
    public void onEntityMissingMappings(RegistryEvent.MissingMappings<EntityEntry> event)
    {
    	
    }
}