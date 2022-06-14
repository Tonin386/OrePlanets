package fiddlecomputers.mods.oreplanets.utils.dimension;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.event.EventHandlerGC;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fiddlecomputers.mods.oreplanets.world.IStartedDimension;

public abstract class WorldProviderOP extends WorldProviderSpace implements ISolarLevel, IStartedDimension
{
    @Override
    public boolean canRespawnHere()
    {
        if (EventHandlerGC.bedActivated)
        {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasSunset()
    {
        return false;
    }

    @Override
    public double getFuelUsageMultiplier()
    {
        return 0.9D;
    }

    @Override
    public void setup(EntityPlayerMP player)
    {
        GCPlayerStats stats = GCPlayerStats.get(player);
        SchematicRegistry.unlockNewPage(player, new ItemStack(GCItems.schematic, 1, 1)); //Knows how to build T2 rocket
        SchematicRegistry.unlockNewPage(player, new ItemStack(MarsItems.schematic, 1, 0)); //Knows how to build T3 rocket
        stats.getExtendedInventory().setInventorySlotContents(0, new ItemStack(GCItems.oxMask, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(1, new ItemStack(GCItems.oxygenGear, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(2, new ItemStack(GCItems.oxTankHeavy, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(3, new ItemStack(GCItems.oxTankHeavy, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(5, new ItemStack(GCItems.basicItem, 1, 19));
        stats.getExtendedInventory().setInventorySlotContents(6, new ItemStack(AsteroidsItems.thermalPadding, 1, 0));
        stats.getExtendedInventory().setInventorySlotContents(7, new ItemStack(AsteroidsItems.thermalPadding, 1, 1));
        stats.getExtendedInventory().setInventorySlotContents(8, new ItemStack(AsteroidsItems.thermalPadding, 1, 2));
        stats.getExtendedInventory().setInventorySlotContents(9, new ItemStack(AsteroidsItems.thermalPadding, 1, 3));
        player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
        player.inventory.addItemStackToInventory(new ItemStack(AsteroidsItems.canisterLOX));
    }

    @Override
    public int getDungeonSpacing()
    {
        return 704;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer()
    {
        if (super.getSkyRenderer() == null)
        {
            this.renderSky();
        }
        return super.getSkyRenderer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getCloudRenderer()
    {
        if (super.getCloudRenderer() == null)
        {
            this.renderCloud();
        }
        return super.getCloudRenderer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getWeatherRenderer()
    {
        if (super.getWeatherRenderer() == null)
        {
            this.renderWeather();
        }
        return super.getWeatherRenderer();
    }

    @Override
    public BiomeProvider getBiomeProvider()
    {
        return this.biomeProvider;
    }

    @Override
    protected void init()
    {
        this.hasSkyLight = true;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass()
    {
        return null;
    }

    @Override
    public ResourceLocation getDungeonChestType()
    {
        return null;
    }

    protected void renderCloud()
    {
        this.setCloudRenderer(new CloudRenderer());
    }

    protected void renderSky() {}
    protected void renderWeather() {}

    @Override
    public abstract IChunkGenerator createChunkGenerator();
}