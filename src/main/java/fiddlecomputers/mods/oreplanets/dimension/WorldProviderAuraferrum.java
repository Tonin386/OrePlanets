package fiddlecomputers.mods.oreplanets.dimension;

import java.util.Arrays;
import java.util.List;

import fiddlecomputers.mods.oreplanets.config.ConfigManagerOP;
import fiddlecomputers.mods.oreplanets.init.OPDimensions;
import fiddlecomputers.mods.oreplanets.init.OPPlanets;
import fiddlecomputers.mods.oreplanets.planets.auraferrum.world.gen.BiomeProviderAuraferrum;
import fiddlecomputers.mods.oreplanets.planets.auraferrum.world.gen.ChunkGeneratorAuraferrum;
import fiddlecomputers.mods.oreplanets.utils.dimension.WorldProviderOP;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderAuraferrum extends WorldProviderOP {


	//TODO not important
    @Override
    public Vector3 getFogColor()
    {
        float f = 1.2F - this.getStarBrightness(1.0F);
        return new Vector3(24.0D / 255.0D * f, 24.0D / 255.0D * f, 24.0D / 255.0D * f);
    }

    //TODO not important
    @Override
    public Vector3 getSkyColor()
    {
        return new Vector3(0, 0, 0);
    }

    @Override
    public long getDayLength()
    {
        return 20000L;
    }

    //TODO not important
    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.25F);
        value = MathHelper.clamp(value, 0.0F, 1.0F);
        return value * value * 0.5F + 0.3F;
    }

    //TODO not important
    @Override
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float partialTicks)
    {
        float angle = this.world.getCelestialAngle(partialTicks);
        float value = 1.0F - (MathHelper.cos(angle * ((float)Math.PI * 2.0F)) * 2.0F + 0.1F);
        value = MathHelper.clamp(value, 0.55F, 1.0F);
        value = 1.0F - value;
        return value * 1.9F;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 3D;
    }

    @Override
    public float getGravity()
    {
        return 0.0125F;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier >= ConfigManagerOP.oreplanets_planet_settings.planetAuraferrumTier;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 1.5F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return OPPlanets.AURAFERRUM;
    }

    @Override
    public float getThermalLevelModifier()
    {
        if (this.isDaytime())
        {
            return 0.25F;
        }
        else
        {
            return 0.05F;
        }
    }

    //TODO not important
    @Override
    protected void renderSky()
    {
        //this.setSkyRenderer(new SkyProviderExanterra(this.getSolarSize()));
    }

    @Override
    public void init()
    {
        super.init();
        this.biomeProvider = new BiomeProviderAuraferrum();
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorAuraferrum(this.world, this.world.getSeed());
    }

    @Override
    public DimensionType getDimensionType()
    {
        return OPDimensions.AURAFERRUM;
    }

    @Override
    public List<Block> getSurfaceBlocks()
    {
    	return Arrays.asList(Blocks.STONE);
    }
}
