package fiddlecomputers.mods.oreplanets.recipe;

import fiddlecomputers.mods.oreplanets.utils.debug.JSONRecipe;

public class CraftingManagerOP
{
    public static void init()
    {
        CraftingManagerOP.addOtherRecipe();

        if (JSONRecipe.ENABLE)
        {
            CraftingManagerOP.addBlockRecipe();
            CraftingManagerOP.addItemRecipe();
        }
        JSONRecipe.generateConstants();
    }

     //TEMP
    protected static void addBlockRecipe()
    {
    	
    }

     //TEMP
    protected static void addItemRecipe()
    {
    	
    }

    private static void addOtherRecipe()
    {
    	
    }
}