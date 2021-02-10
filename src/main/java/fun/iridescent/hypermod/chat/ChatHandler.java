package fun.iridescent.hypermod.chat;


import fun.iridescent.hypermod.chat.modules.GuildWelcomer;
import club.sk1er.mods.core.util.MinecraftUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler {

    private final List<ChatReceiveModule> receiveModules = new ArrayList<>();

    public ChatHandler() {
        this.registerModule(new GuildWelcomer());
    }

    private void registerModule(ChatReceiveModule chatModule) {
        this.receiveModules.add(chatModule);
    }


    @SubscribeEvent
    public void handleChat(ClientChatReceivedEvent event) {
        if (!MinecraftUtils.isHypixel()) {
            return;
        }

        for (ChatReceiveModule module : this.receiveModules) {
            if (module.isEnabled()) {
                module.onMessageReceived(event);
                if (event.isCanceled()) {
                    return;
                }
            }
        }
    }}

