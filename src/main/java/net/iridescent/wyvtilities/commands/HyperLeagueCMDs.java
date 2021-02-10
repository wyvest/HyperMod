package net.iridescent.wyvtilities.commands;

//unlike normal Wyvtilities this uses vigilance instead of TGMLib / default mc stuff which is why it looks a bit different

import club.sk1er.mods.core.gui.notification.Notifications;
import club.sk1er.mods.core.util.Multithreading;
import net.iridescent.wyvtilities.Wyvtilities;
import club.sk1er.mods.core.ModCore;
import net.iridescent.wyvtilities.chat.ChatUtils;
import net.iridescent.wyvtilities.files.HyperLeagueConfig;
import net.iridescent.wyvtilities.others.APIUtil;
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
        try {
            if (args.length == 0) {
                    ModCore.getInstance().getGuiHandler().open(Wyvtilities.INSTANCE.getHyperLeagueConfig().gui());
            }
            else {
                if (args[0].equalsIgnoreCase("help")) {
                    ChatUtils.getInstance().sendMessage(
                            "literally go to the wiki dumbass");
                    return;
                }
                if(args[0].equalsIgnoreCase("setkey")) {
                    if (args.length == 1) {
                        Notifications.INSTANCE.pushNotification("Wyvtilities - HyperLeague", "Please provide a valid API key!");
                        return;
                    }
                    Multithreading.runAsync(() -> {
                        String apiKey = args[1];
                        if (APIUtil.getJSONResponse("https://api.hypixel.net/key?key=" + apiKey).get("success").getAsBoolean()) {
                            HyperLeagueConfig.apiKey = apiKey;
                            Wyvtilities.hyperLeagueConfig().markDirty();
                            Notifications.INSTANCE.pushNotification("Wyvtilities - HyperLeague", "Updated your API key to " + apiKey);
                            Wyvtilities.hyperLeagueConfig().writeData();
                        } else {
                            Notifications.INSTANCE.pushNotification("Wyvtilities - HyperLeague", "Please provide a valid API key!");
                        }
                    });
                    return;
                }
                if (args[0].equalsIgnoreCase("gexp")) {
                    int gexp = net.iridescent.wyvtilities.chat.modules.gexp.gexp;
                    if (gexp == Integer.parseInt(null)) {
                        Notifications.INSTANCE.pushNotification("Wyvtilities - HyperLeague", "The request failed! Please type in a valid API key!");

                    } else {
                        Notifications.INSTANCE.pushNotification("Wyvtilities - HyperLeague", "You have " + gexp + " daily gexp!");

                    }
                }
            }




        } catch (Exception ignored) {}
    }



    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
