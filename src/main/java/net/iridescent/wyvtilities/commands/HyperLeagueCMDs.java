package net.iridescent.wyvtilities.commands;


import net.iridescent.wyvtilities.Wyvtilities;
import club.sk1er.mods.core.ModCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;


public class HyperLeagueCMDs extends CommandBase {
    @Override
    public String getCommandName() {
        return "hyperleague";
    }


    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ModCore.getInstance().getGuiHandler().open(Wyvtilities.INSTANCE.getConfig().gui());
        } else {
            Wyvtilities.INSTANCE.sendMessage("&cIncorrect arguments. Command usage is: " + getCommandUsage(sender));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
