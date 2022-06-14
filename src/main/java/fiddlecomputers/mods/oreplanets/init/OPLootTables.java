package fiddlecomputers.mods.oreplanets.init;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import fiddlecomputers.mods.oreplanets.OrePlanets;

public class OPLootTables
{
    // Entity
    public static final ResourceLocation INFECTED_PURLONITE_SPIDER = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_purlonite_spider");
    public static final ResourceLocation INFECTED_PURLONITE_SLIME_BOSS = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_purlonite_slime_boss");
    public static final ResourceLocation INFECTED_PURLONITE_SLIME = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_purlonite_slime");
    public static final ResourceLocation ZELIUS_CREEPER = OrePlanets.COMMON_REGISTRY.registerEntityLoot("zelius_creeper");
    public static final ResourceLocation ZELIUS_ZOMBIE = OrePlanets.COMMON_REGISTRY.registerEntityLoot("zelius_zombie");
    public static final ResourceLocation ZELIUS_SKELETON = OrePlanets.COMMON_REGISTRY.registerEntityLoot("zelius_skeleton");
    public static final ResourceLocation ALIEN_MINER = OrePlanets.COMMON_REGISTRY.registerEntityLoot("alien_miner");
    public static final ResourceLocation CHEESE_COW = OrePlanets.COMMON_REGISTRY.registerEntityLoot("cheese_cow");
    public static final ResourceLocation CHEESE_FLOATER = OrePlanets.COMMON_REGISTRY.registerEntityLoot("cheese_floater");
    public static final ResourceLocation CHEESE_SLIME = OrePlanets.COMMON_REGISTRY.registerEntityLoot("cheese_slime");
    public static final ResourceLocation INFECTED_GUARDIAN = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_guardian");
    public static final ResourceLocation INFECTED_ELDER_GUARDIAN = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_elder_guardian");
    public static final ResourceLocation INFECTED_SKELETON = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_skeleton");
    public static final ResourceLocation INFECTED_SNOWMAN = OrePlanets.COMMON_REGISTRY.registerEntityLoot("infected_snowman");
    public static final ResourceLocation SHLIME = OrePlanets.COMMON_REGISTRY.registerEntityLoot("shlime");
    public static final ResourceLocation BEARRY = OrePlanets.COMMON_REGISTRY.registerEntityLoot("bearry");
    public static final ResourceLocation GIANT_BLUEBERRY = OrePlanets.COMMON_REGISTRY.registerEntityLoot("giant_blueberry");
    public static final ResourceLocation MARSHMALLOW = OrePlanets.COMMON_REGISTRY.registerEntityLoot("marshmallow");
    public static final ResourceLocation TERRASTONE_GOLEM = OrePlanets.COMMON_REGISTRY.registerEntityLoot("terrastone_golem");

    // Jelly Slime
    public static final ResourceLocation JELLY_SLIME_GRAPE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "grape");
    public static final ResourceLocation JELLY_SLIME_RASPBERRY = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "raspberry");
    public static final ResourceLocation JELLY_SLIME_STRAWBERRY = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "strawberry");
    public static final ResourceLocation JELLY_SLIME_BERRY = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "berry");
    public static final ResourceLocation JELLY_SLIME_LIME = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "lime");
    public static final ResourceLocation JELLY_SLIME_ORANGE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "orange");
    public static final ResourceLocation JELLY_SLIME_GREEN = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "green");
    public static final ResourceLocation JELLY_SLIME_LEMON = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("jelly_slime", "lemon");

    // Dyed
    public static final ResourceLocation SHLIME_WOOL_WHITE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "white");
    public static final ResourceLocation SHLIME_WOOL_ORANGE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "orange");
    public static final ResourceLocation SHLIME_WOOL_MAGENTA = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "magenta");
    public static final ResourceLocation SHLIME_WOOL_LIGHT_BLUE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "light_blue");
    public static final ResourceLocation SHLIME_WOOL_YELLOW = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "yellow");
    public static final ResourceLocation SHLIME_WOOL_LIME = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "lime");
    public static final ResourceLocation SHLIME_WOOL_PINK = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "pink");
    public static final ResourceLocation SHLIME_WOOL_GRAY = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "gray");
    public static final ResourceLocation SHLIME_WOOL_SILVER = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "silver");
    public static final ResourceLocation SHLIME_WOOL_CYAN = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "cyan");
    public static final ResourceLocation SHLIME_WOOL_PURPLE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "purple");
    public static final ResourceLocation SHLIME_WOOL_BLUE = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "blue");
    public static final ResourceLocation SHLIME_WOOL_BROWN = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "brown");
    public static final ResourceLocation SHLIME_WOOL_GREEN = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "green");
    public static final ResourceLocation SHLIME_WOOL_RED = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "red");
    public static final ResourceLocation SHLIME_WOOL_BLACK = OrePlanets.COMMON_REGISTRY.registerEntitySubLoot("shlime_wool", "black");

    // Chests
    public static final ResourceLocation COMMON_SPACE_DUNGEON = OrePlanets.COMMON_REGISTRY.registerChestLoot("common_space_dungeon");
    public static final ResourceLocation COMMON_SPACE_MINESHAFT = OrePlanets.COMMON_REGISTRY.registerChestLoot("common_space_mineshaft");
    public static final ResourceLocation CRASHED_ALIEN_PROBE = OrePlanets.COMMON_REGISTRY.registerChestLoot("crashed_alien_probe");
    public static final ResourceLocation NIBIRU_JUNGLE_TEMPLE = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_jungle_temple");
    public static final ResourceLocation NIBIRU_JUNGLE_TEMPLE_DISPENSER = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_jungle_temple_dispenser");
    public static final ResourceLocation NIBIRU_DESERT_PYRAMID = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_desert_pyramid");
    public static final ResourceLocation NIBIRU_STRONGHOLD_CORRIDOR = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_stronghold_corridor");
    public static final ResourceLocation NIBIRU_STRONGHOLD_LIBRARY = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_stronghold_library");
    public static final ResourceLocation NIBIRU_STRONGHOLD_CROSSING = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_stronghold_crossing");
    public static final ResourceLocation NIBIRU_VILLAGE_LIBRARY = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_village_library");
    public static final ResourceLocation NIBIRU_VILLAGE_BLACKSMITH = OrePlanets.COMMON_REGISTRY.registerChestLoot("nibiru_village_blacksmith");
    public static final ResourceLocation CRASHED_ALIEN_SHIP = OrePlanets.COMMON_REGISTRY.registerChestLoot("crashed_alien_ship");

    // Gameplay
    public static final ResourceLocation SPACE_FISHING = OrePlanets.COMMON_REGISTRY.registerGameplayLoot("space_fishing");
    public static final ResourceLocation SPACE_FISH_JUNK = OrePlanets.COMMON_REGISTRY.registerFishingLoot("junk_space");
    public static final ResourceLocation SPACE_FISH_TREASURE = OrePlanets.COMMON_REGISTRY.registerFishingLoot("treasure_space");
    public static final ResourceLocation INFECTED_PURLONITE_WATER_FISHING = OrePlanets.COMMON_REGISTRY.registerGameplayLoot("infected_purlonite_water_fishing");
    public static final ResourceLocation CHEESE_MILK_FISHING = OrePlanets.COMMON_REGISTRY.registerGameplayLoot("cheese_milk_fishing");
    public static final ResourceLocation INFECTED_PURLONITE_WATER_FISH = OrePlanets.COMMON_REGISTRY.registerFishingLoot("infected_purlonite_fish");
    public static final ResourceLocation CHEESE_FISH = OrePlanets.COMMON_REGISTRY.registerFishingLoot("cheese_fish");

    public static ItemStack getTieredKey(Random rand, int tier)
    {
        return GalacticraftRegistry.getDungeonLoot(tier).get(rand.nextInt(GalacticraftRegistry.getDungeonLoot(tier).size())).copy();
    }
}