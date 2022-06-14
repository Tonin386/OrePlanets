package fiddlecomputers.mods.oreplanets.client.renderer;

import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.utils.BlocksItemsRegistry;
import fiddlecomputers.mods.oreplanets.utils.LoggerOP;

public class ItemModelRenderer
{
    public static void init()
    {
        ItemModelRenderer.registerBlockRenderer();
        ItemModelRenderer.registerItemRenderer();
    }

    private static void registerBlockRenderer()
    {
        BlocksItemsRegistry.SINGLE_BLOCK_RENDER_LIST.forEach((block, name) -> OrePlanets.CLIENT_REGISTRY.registerModelRender(block, name));
    }

    private static void registerItemRenderer()
    {
        BlocksItemsRegistry.SINGLE_ITEM_RENDER_LIST.forEach((item, name) -> OrePlanets.CLIENT_REGISTRY.registerModelRender(item, name));
    }

    public static void registerCCLRenderer()
    {
        try
        {
            LoggerOP.info("Successfully registered CodeChickenCore item renderer for More Planets blocks/items");
        }
        catch (Exception e)
        {
            LoggerOP.info("CodeChickenCore not loaded, using vanilla TileEntityItemStackRenderer");
        }
    }
}