package fiddlecomputers.mods.oreplanets.utils.items;

public class ItemDungeonKeyOP extends ItemBaseOP implements IDungeonKey
{
    public ItemDungeonKeyOP(String name, int tier)
    {
        this.setMaxStackSize(1);
        this.setTranslationKey(name);
    }

    @Override
    public EnumSortCategoryItem getItemCategory()
    {
        return EnumSortCategoryItem.DUNGEON_KEY;
    }
}