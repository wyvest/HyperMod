package net.iridescent.wyvtilities.commands;

import net.iridescent.wyvtilities.Wyvtilities;
import net.iridescent.wyvtilities.others.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class cmds extends CommandBase{

    private final String lineDivider = "------------------------------------";

    @Override
    public String getCommandName() {
        return "wyvtilities";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        try {
            if (args[0].equalsIgnoreCase("help")) {
                Utils.getInstance().sendMessage("\n" + EnumChatFormatting.GOLD + lineDivider + "\n" + EnumChatFormatting.GREEN + "default - Opens GUI\n");
                return;
            }
        } catch (Exception ignored) {}
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    protected void onTick(TickEvent.ClientTickEvent event) {
        Minecraft.getMinecraft().displayGuiScreen(Wyvtilities.getInstance().configGui);
        MinecraftForge.EVENT_BUS.unregister(this);
    }


}
