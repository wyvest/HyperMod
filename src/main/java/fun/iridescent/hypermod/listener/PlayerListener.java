package fun.iridescent.hypermod.listener;

import fun.iridescent.hypermod.HyperMod;
import fun.iridescent.hypermod.others.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class PlayerListener {

    private boolean hasCheckedForUpdate = false;

    @SubscribeEvent
    protected void checkForUpdates(EntityJoinWorldEvent event) {
        if(!hasCheckedForUpdate) {
            this.hasCheckedForUpdate = true;
            Thread updateCheckThread = new Thread(() -> {
                if(HyperMod.getInstance().getVersionChecker().getVersion().equalsIgnoreCase(References.VER)) return;
                EntityPlayerSP player;
                ChatComponentText updateMessage = new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "[" + HyperMod.getInstance().getVersionChecker().getVersion() + "]");
                updateMessage.setChatStyle(updateMessage.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, HyperMod.getInstance().getVersionChecker().getDownloadURL())));
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                player = Minecraft.getMinecraft().thePlayer;
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Your version of " + EnumChatFormatting.GREEN + References.NAME + EnumChatFormatting.YELLOW +  " is out of date!\n" + EnumChatFormatting.RED + "Please update: ").appendSibling(updateMessage));
            });
            updateCheckThread.start();
        }
    }


}
