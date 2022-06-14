package fiddlecomputers.mods.oreplanets.utils.helper;

import micdoodle8.mods.galacticraft.api.recipe.CompressorRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.utils.debug.JSONRecipe;

public class RecipeHelper
{
    public static void addRecipe(ItemStack output, Object... obj)
    {
        if (OrePlanets.isDevelopment)
        {
            JSONRecipe.addShapedRecipe(output, obj);
        }
    }

    public static void addRecipe(ItemStack output, String group, Object... obj)
    {
        if (OrePlanets.isDevelopment)
        {
            JSONRecipe.addShapedRecipe(output, group, obj);
        }
    }

    public static void addRecipe(ItemStack output, String group, String altName, Object... obj)
    {
        if (OrePlanets.isDevelopment)
        {
            JSONRecipe.addShapedRecipe(output, group, altName, obj);
        }
    }

    public static void addShapelessRecipe(ItemStack output, Object... obj)
    {
        if (OrePlanets.isDevelopment)
        {
            JSONRecipe.addShapelessRecipe(output, obj);
        }
    }

    public static void addShapelessRecipe(ItemStack output, String group, Object... obj)
    {
        if (OrePlanets.isDevelopment)
        {
            JSONRecipe.addShapelessRecipe(output, group, obj);
        }
    }

    public static void addShapelessRecipe(ItemStack output, String group, String altName, Object... obj)
    {
        if (OrePlanets.isDevelopment)
        {
            JSONRecipe.addShapelessRecipe(output, group, altName, obj);
        }
    }

    public static void addSmeltingRecipe(ItemStack input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addCompressorRecipe(ItemStack output, Object... obj)
    {
        CompressorRecipes.addRecipe(output, obj);
    }

    public static void addShapelessCompressorRecipe(ItemStack output, Object... obj)
    {
        CompressorRecipes.addShapelessRecipe(output, obj);
    }
}