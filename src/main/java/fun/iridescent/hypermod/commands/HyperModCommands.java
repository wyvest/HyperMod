package fun.iridescent.hypermod.commands;

//unlike normal HyperMod this uses vigilance instead of TGMLib / default mc stuff which is why it looks a bit different

import club.sk1er.mods.core.gui.notification.Notifications;
import club.sk1er.mods.core.util.Multithreading;
import fun.iridescent.hypermod.HyperMod;
import fun.iridescent.hypermod.chat.ChatUtils;
import club.sk1er.mods.core.ModCore;
import fun.iridescent.hypermod.files.Config;
import fun.iridescent.hypermod.others.APIUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;


public class HyperModCommands extends CommandBase {
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
                    ModCore.getInstance().getGuiHandler().open(HyperMod.INSTANCE.getHyperLeagueConfig().gui());
            }
            else {
                if (args[0].equalsIgnoreCase("help")) {
                    ChatUtils.getInstance().sendMessage(
                            "literally go to the wiki dumbass");
                    return;
                }
                if(args[0].equalsIgnoreCase("setkey")) {
                    if (args.length == 1) {
                        Notifications.INSTANCE.pushNotification("HyperMod - HyperLeague", "Please provide a valid API key!");
                        return;
                    }
                    Multithreading.runAsync(() -> {
                        String apiKey = args[1];
                        if (APIUtil.getJSONResponse("https://api.hypixel.net/key?key=" + apiKey).get("success").getAsBoolean()) {
                            Config.apiKey = apiKey;
                            HyperMod.hyperLeagueConfig().markDirty();
                            Notifications.INSTANCE.pushNotification("HyperMod - HyperLeague", "Updated your API key to " + apiKey);
                            HyperMod.hyperLeagueConfig().writeData();
                        } else {
                            Notifications.INSTANCE.pushNotification("HyperMod - HyperLeague", "Please provide a valid API key!");
                        }
                    });
                    return;
                }
                if (args[0].equalsIgnoreCase("gexp")) {
                    int gexp = fun.iridescent.hypermod.chat.modules.gexp.gexp;
                    if (gexp == Integer.parseInt(null)) {
                        Notifications.INSTANCE.pushNotification("HyperMod - HyperLeague", "The request failed! Please type in a valid API key!");

                    } else {
                        Notifications.INSTANCE.pushNotification("HyperMod - HyperLeague", "You have " + gexp + " daily gexp!");

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
