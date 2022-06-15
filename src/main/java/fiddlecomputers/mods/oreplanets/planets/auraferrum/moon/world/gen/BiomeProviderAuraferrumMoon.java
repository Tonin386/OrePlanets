package fiddlecomputers.mods.oreplanets.planets.auraferrum.moon.world.gen;

import fiddlecomputers.mods.oreplanets.init.OPBiomes;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;

public class BiomeProviderAuraferrumMoon extends BiomeProviderSpace {

	@Override
	public Biome getBiome() {
		return OPBiomes.AURAFERRUM_MOON;
	}

}
