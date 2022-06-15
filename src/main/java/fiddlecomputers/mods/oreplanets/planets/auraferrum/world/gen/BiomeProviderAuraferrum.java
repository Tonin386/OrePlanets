package fiddlecomputers.mods.oreplanets.planets.auraferrum.world.gen;

import fiddlecomputers.mods.oreplanets.init.OPBiomes;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeProviderSpace;
import net.minecraft.world.biome.Biome;

public class BiomeProviderAuraferrum extends BiomeProviderSpace {

	@Override
	public Biome getBiome() {
		return OPBiomes.AURAFERRUM;
	}

}
