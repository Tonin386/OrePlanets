package fiddlecomputers.mods.oreplanets.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityItemStackRendererOP extends TileEntityItemStackRenderer
{
    public static final TileEntityItemStackRendererOP INSTANCE = new TileEntityItemStackRendererOP();

    @Override
    public void renderByItem(ItemStack itemStack)
    {
    	
    }
}