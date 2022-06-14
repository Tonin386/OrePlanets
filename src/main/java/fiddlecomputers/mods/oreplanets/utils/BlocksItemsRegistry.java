package fiddlecomputers.mods.oreplanets.utils;

import java.util.*;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fiddlecomputers.mods.oreplanets.OrePlanets;
import fiddlecomputers.mods.oreplanets.itemblocks.ItemBlockTESRMP;
import fiddlecomputers.mods.oreplanets.utils.blocks.EnumSortCategoryBlock;
import fiddlecomputers.mods.oreplanets.utils.blocks.ISortableBlock;
import fiddlecomputers.mods.oreplanets.utils.client.renderer.IItemModelRender;
import fiddlecomputers.mods.oreplanets.utils.itemblocks.ItemBlockOP;
import fiddlecomputers.mods.oreplanets.utils.items.EnumSortCategoryItem;
import fiddlecomputers.mods.oreplanets.utils.items.ISortableItem;
import fiddlecomputers.mods.oreplanets.utils.client.ClientUtils;

public class BlocksItemsRegistry
{
    public static final Map<EnumSortCategoryBlock, List<StackSorted>> SORT_MAP_BLOCKS = new HashMap<>();
    public static final Map<EnumSortCategoryItem, List<StackSorted>> SORT_MAP_ITEMS = new HashMap<>();
    public static final Map<Block, String> SINGLE_BLOCK_RENDER_LIST = new HashMap<>();
    public static final Map<Item, String> SINGLE_ITEM_RENDER_LIST = new HashMap<>();
    public static final List<Item> TESR_ITEM_RENDER = new ArrayList<>();

    public static void registerBlock(Block block)
    {
        BlocksItemsRegistry.registerBlock(block, ItemBlockOP::new);
    }

    public static void registerBlock(Block block, @Nullable Function<Block, ItemBlock> itemBlock)
    {
        OrePlanets.COMMON_REGISTRY.registerBlock(block, itemBlock);

        if (block instanceof IItemModelRender)
        {
            IItemModelRender blockRender = (IItemModelRender) block;

            if (blockRender.getName() != null)
            {
                BlocksItemsRegistry.SINGLE_BLOCK_RENDER_LIST.put(block, blockRender.getName());
            }
        }
        if (itemBlock != null)
        {
            if (ClientUtils.isEffectiveClient())
            {
                BlocksItemsRegistry.registerSorted(block);
                ItemBlock itemBlockTESR = itemBlock.apply(block);

                if (itemBlockTESR instanceof ItemBlockTESRMP)
                {
                    BlocksItemsRegistry.TESR_ITEM_RENDER.add(Item.getItemFromBlock(itemBlockTESR.getBlock()));
                }
            }
        }
    }

    public static void registerItem(Item item)
    {
        OrePlanets.COMMON_REGISTRY.registerItem(item);

        if (item instanceof IItemModelRender)
        {
            IItemModelRender itemRender = (IItemModelRender) item;

            if (itemRender.getName() != null)
            {
                BlocksItemsRegistry.SINGLE_ITEM_RENDER_LIST.put(item, itemRender.getName());
            }
        }
        if (ClientUtils.isEffectiveClient())
        {
            BlocksItemsRegistry.registerSorted(item);
        }
    }

    public static void registerSorted(Block block)
    {
        if (block instanceof ISortableBlock)
        {
            Item item = Item.getItemFromBlock(block);

            if (item == Items.AIR)
            {
                return;
            }

            ISortableBlock sortableBlock = (ISortableBlock) block;
            EnumSortCategoryBlock categoryBlock = sortableBlock.getBlockCategory();

            if (!BlocksItemsRegistry.SORT_MAP_BLOCKS.containsKey(categoryBlock))
            {
                BlocksItemsRegistry.SORT_MAP_BLOCKS.put(categoryBlock, new ArrayList<>());
            }
            BlocksItemsRegistry.SORT_MAP_BLOCKS.get(categoryBlock).add(new StackSorted(block));
        }
    }

    public static void postRegisteredSortBlock()
    {
        List<StackSorted> itemOrderListBlocks = new ArrayList<>();

        for (EnumSortCategoryBlock type : EnumSortCategoryBlock.VALUES)
        {
            List<StackSorted> stackSorteds = BlocksItemsRegistry.SORT_MAP_BLOCKS.get(type);

            if (stackSorteds != null)
            {
                itemOrderListBlocks.addAll(stackSorteds);
            }
        }
    }

    public static void registerSorted(Item item)
    {
        if (item instanceof ISortableItem)
        {
            ISortableItem sortableItem = (ISortableItem) item;
            EnumSortCategoryItem categoryItem = sortableItem.getItemCategory();

            if (!BlocksItemsRegistry.SORT_MAP_ITEMS.containsKey(categoryItem))
            {
                BlocksItemsRegistry.SORT_MAP_ITEMS.put(categoryItem, new ArrayList<>());
            }
            BlocksItemsRegistry.SORT_MAP_ITEMS.get(categoryItem).add(new StackSorted(item));
        }
    }

    public static void postRegisteredSortItem()
    {
        List<StackSorted> itemOrderListItems = new ArrayList<>();

        for (EnumSortCategoryItem type : EnumSortCategoryItem.VALUES)
        {
            List<StackSorted> stackSorteds = BlocksItemsRegistry.SORT_MAP_ITEMS.get(type);

            if (stackSorteds != null)
            {
                itemOrderListItems.addAll(stackSorteds);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static List<String> getDescription(String name)
    {
        return Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(name, 150);
    }
}