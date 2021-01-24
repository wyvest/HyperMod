package net.iridescent.wyvtilities.commands;

import net.iridescent.wyvtilities.Wyvtilities;
import net.iridescent.wyvtilities.others.Utils;
import net.minecraft.command.CommandBase;
import ga.matthewtgm.lib.util.GuiScreenUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class cmds extends CommandBase {

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
        } catch (Exception ignored) {
        }
        GuiScreenUtils.getInstance().open(Wyvtilities.getInstance().configGui);


    }
}
