package fiddlecomputers.mods.oreplanets.command;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import fiddlecomputers.mods.oreplanets.event.WorldTickEventHandler;
import fiddlecomputers.mods.oreplanets.network.PacketSimpleOP;
import fiddlecomputers.mods.oreplanets.network.PacketSimpleOP.EnumSimplePacketMP;
import fiddlecomputers.mods.oreplanets.utils.ColorUtils;
import fiddlecomputers.mods.oreplanets.utils.itemblocks.IItemRarity;

public class CommandOpenCelestialScreen extends CommandBase
{
    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/" + this.getName();
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "mpcelestial";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayerMP player = PlayerUtil.getPlayerBaseServerFromPlayerUsername(sender.getName(), true);

        if (args.length == 1 && args[0].equals("disable"))
        {
            if (!WorldTickEventHandler.survivalPlanetData.disableMessage)
            {
                GalacticraftCore.packetPipeline.sendToServer(new PacketSimpleOP(EnumSimplePacketMP.S_SAVE_DISABLE_MESSAGE, GCCoreUtil.getDimensionID(player.world)));
                sender.sendMessage(new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(new TextComponentTranslation("command.mpcelestial.1").setStyle(new Style().setColor(TextFormatting.YELLOW))));
            }
        }
        else
        {
            if (!WorldTickEventHandler.survivalPlanetData.hasSurvivalPlanetData)
            {
                GalacticraftCore.packetPipeline.sendTo(new PacketSimpleOP(EnumSimplePacketMP.C_OPEN_SURVIVAL_PLANET_GUI, player.dimension), player);
            }
            else
            {
                sender.sendMessage(new TextComponentString(ColorUtils.stringToRGB(IItemRarity.ALIEN).toColoredFont() + "[More Planets] ").appendSibling(new TextComponentTranslation("command.mpcelestial.2").setStyle(new Style().setColor(TextFormatting.RED))));
            }
        }
    }
}