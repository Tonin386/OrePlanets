package fiddlecomputers.mods.oreplanets.utils.items;

import micdoodle8.mods.galacticraft.api.item.GCRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import fiddlecomputers.mods.oreplanets.utils.client.renderer.IItemModelRender;
import fiddlecomputers.mods.oreplanets.utils.itemblocks.IItemRarity;
import fiddlecomputers.mods.oreplanets.utils.ColorUtils;

public class ItemBaseOP extends Item implements ISortableItem, IItemModelRender, IItemRarity, GCRarity
{
    private EnumSortCategoryItem category;
    private ColorUtils.RGB rgb;
    private String name;

    public ItemBaseOP() {}

    public ItemBaseOP(String name)
    {
        this.setTranslationKey(name);
    }

    @Override
    public Item setTranslationKey(String name)
    {
        this.name = name;
        return super.setTranslationKey(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return this.category == null ? EnumSortCategoryItem.GENERAL : this.category;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public ColorUtils.RGB getRarity()
    {
        return this.rgb != null ? this.rgb : null;
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return this instanceof IItemRarity && ((IItemRarity)this).getRarity() != null ? ((IItemRarity)this).getRarity().toColoredFont() + super.getItemStackDisplayName(itemStack) : super.getItemStackDisplayName(itemStack);
    }

    public ItemBaseOP setSortCategory(EnumSortCategoryItem category)
    {
        this.category = category;
        return this;
    }

    public ItemBaseOP setRarityRGB(ColorUtils.RGB rgb)
    {
        this.rgb = rgb;
        return this;
    }
}