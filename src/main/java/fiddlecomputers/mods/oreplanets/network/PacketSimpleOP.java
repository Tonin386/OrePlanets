package fiddlecomputers.mods.oreplanets.network;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.network.NetworkUtil;
import micdoodle8.mods.galacticraft.core.network.PacketBase;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fiddlecomputers.mods.oreplanets.client.gui.GuiCelestialSelection;
import fiddlecomputers.mods.oreplanets.event.ClientEventHandler;
import fiddlecomputers.mods.oreplanets.event.WorldTickEventHandler;
import fiddlecomputers.mods.oreplanets.utils.LoggerOP;
import fiddlecomputers.mods.oreplanets.utils.TeleportUtils;
import fiddlecomputers.mods.oreplanets.utils.TeleporterSpaceNether;
import fiddlecomputers.mods.oreplanets.utils.itemblocks.IItemRarity;
import fiddlecomputers.mods.oreplanets.utils.ColorUtils;
import fiddlecomputers.mods.oreplanets.utils.JsonUtils;

public class PacketSimpleOP extends PacketBase
{
    private EnumSimplePacketMP type;
    private List<Object> data;

    public PacketSimpleOP() {}

    public PacketSimpleOP(EnumSimplePacketMP packetType, int dimID, Object... dataList)
    {
        super(dimID);
        List<Object> data = Arrays.asList(dataList);

        if (packetType.getDecodeClasses().length != data.size())
        {
            LoggerOP.warning("More Planets Simple Packet found data length different than packet type: {}", packetType.name());
        }
        this.type = packetType;
        this.data = data;
    }

    @Override
    public void encodeInto(ByteBuf buffer)
    {
        super.encodeInto(buffer);
        buffer.writeInt(this.type.ordinal());

        try
        {
            NetworkUtil.encodeData(buffer, this.data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void decodeInto(ByteBuf buffer)
    {
        super.decodeInto(buffer);
        this.type = EnumSimplePacketMP.values[buffer.readInt()];

        if (this.type.getDecodeClasses().length > 0)
        {
            this.data = NetworkUtil.decodeData(this.type.getDecodeClasses(), buffer);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer player)
    {
        BlockPos pos;

        switch (this.type)
        {
        case C_REMOVE_GUIDE_POS:
            pos = (BlockPos) this.data.get(0);
            ClientEventHandler.RECEIVER_RENDER_POS.remove(pos);
            break;
        case C_RELOAD_RENDERER:
            FMLClientHandler.instance().getClient().renderGlobal.loadRenderers();
            break;
        case C_OPEN_SURVIVAL_PLANET_GUI:
            GuiCelestialSelection gui = new GuiCelestialSelection();
            FMLClientHandler.instance().getClient().displayGuiScreen(gui);
            break;
        case C_MESSAGE_SURVIVAL_PLANET:
            String name = (String)this.data.get(0);
            player.sendMessage(new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(JsonUtils.create("message.survival_planet_selected.1" + " " + TextFormatting.AQUA + name + TextFormatting.RESET + " " + "message.survival_planet_selected.2")));
            break;
        default:
            break;
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
        EntityPlayerMP playerMP = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
        GCPlayerStats stats = GCPlayerStats.get(playerMP);

        switch (this.type)
        {
        case S_FAILED_UNLOCK_CHEST:
            if (stats.getChatCooldown() == 0)
            {
                player.sendMessage(new TextComponentString("gui.valid_key.message"));
                stats.setChatCooldown(100);
            }
            break;
        case S_START_SURVIVAL_PLANET:
            if (WorldTickEventHandler.survivalPlanetData != null && !WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
            {
                int sourceDimId = (int)this.data.get(0);
                String celestialName = (String)this.data.get(1);
                LoggerOP.info("Start survival planet at: {}, Dimension: {}", celestialName, WorldUtil.getProviderForNameServer(celestialName).getDimension());
                WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData = true;
                WorldTickEventHandler.survivalPlanetData.survivalPlanetName = celestialName;
                WorldTickEventHandler.survivalPlanetData.setDirty(true);
                TeleportUtils.teleportPlayerToPlanet(playerMP, playerMP.getServer(), sourceDimId, WorldUtil.getProviderForNameServer(celestialName).getDimension());
            }
            break;
        case S_SAVE_DISABLE_MESSAGE:
            if (WorldTickEventHandler.survivalPlanetData != null && !WorldTickEventHandler.survivalPlanetData.disableMessage)
            {
                WorldTickEventHandler.survivalPlanetData.disableMessage = true;
                WorldTickEventHandler.survivalPlanetData.setDirty(true);
            }
            break;
        case S_TRANSFER_PLAYER:
            int dimID = (int)this.data.get(0);
            playerMP.server.getPlayerList().transferPlayerToDimension(playerMP, dimID, new TeleporterSpaceNether(playerMP.server.getWorld(dimID), player.getPosition(), player.world.provider));
            GalacticraftCore.packetPipeline.sendTo(new PacketSimpleOP(EnumSimplePacketMP.C_RELOAD_RENDERER, player.dimension), playerMP);
            break;
        default:
            break;
        }
    }

    public enum EnumSimplePacketMP
    {
        // SERVER
        S_FAILED_UNLOCK_CHEST(Side.SERVER, String.class),
        S_START_SURVIVAL_PLANET(Side.SERVER, Integer.class, String.class),
        S_SAVE_DISABLE_MESSAGE(Side.SERVER),
        S_TRANSFER_PLAYER(Side.SERVER, Integer.class),

        // CLIENT
        C_REMOVE_GUIDE_POS(Side.CLIENT, BlockPos.class),
        C_RELOAD_RENDERER(Side.CLIENT),
        C_OPEN_SURVIVAL_PLANET_GUI(Side.CLIENT),
        C_MESSAGE_SURVIVAL_PLANET(Side.CLIENT, String.class),
        ;

        private Side targetSide;
        private Class<?>[] decodeAs;
        public static final EnumSimplePacketMP[] values = EnumSimplePacketMP.values();

        private EnumSimplePacketMP(Side targetSide, Class<?>... decodeAs)
        {
            this.targetSide = targetSide;
            this.decodeAs = decodeAs;
        }

        public Side getTargetSide()
        {
            return this.targetSide;
        }

        public Class<?>[] getDecodeClasses()
        {
            return this.decodeAs;
        }
    }

    public static void sendToAllAround(PacketSimpleOP packet, World world, int dimID, BlockPos pos, double radius)
    {
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;
        double r2 = radius * radius;

        for (EntityPlayer player : world.playerEntities)
        {
            if (player instanceof EntityPlayerMP)
            {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;

                if (playerMP.dimension == dimID)
                {
                    double dx = x - playerMP.posX;
                    double dy = y - playerMP.posY;
                    double dz = z - playerMP.posZ;

                    if (dx * dx + dy * dy + dz * dz < r2)
                    {
                        GalacticraftCore.packetPipeline.sendTo(packet, playerMP);
                    }
                }
            }
        }
    }
}
