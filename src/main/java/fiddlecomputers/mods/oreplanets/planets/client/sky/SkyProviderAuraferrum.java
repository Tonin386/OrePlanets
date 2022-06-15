package fiddlecomputers.mods.oreplanets.planets.client.sky;

import fiddlecomputers.mods.oreplanets.client.renderer.sky.SkyProviderBaseOP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;

public class SkyProviderAuraferrum extends SkyProviderBaseOP {

    public SkyProviderAuraferrum(float solarSize)
    {
        this.solarSize = 50.0F * solarSize;
    }

    @Override
    protected void renderObjects(float partialTicks, WorldClient world, Minecraft mc)
    {
    	
    }

    @Override
    protected void renderStars(float starBrightness)
    {
        GlStateManager.color(starBrightness, starBrightness, starBrightness, this.getStarBrightness());
    }

    @Override
    protected float getStarBrightness()
    {
        return 3F;
    }

    @Override
    protected int getStarCount()
    {
        return 0;
    }

    @Override
    protected double getStarSpreadMultiplier()
    {
        return 150.0D;
    }
}
