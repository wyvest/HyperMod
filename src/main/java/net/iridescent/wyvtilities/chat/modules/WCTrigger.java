package net.iridescent.wyvtilities.chat.modules;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import org.jetbrains.annotations.NotNull;
import net.iridescent.wyvtilities.chat.ChatReceiveModule;
import net.iridescent.wyvtilities.hud.elements.impl.ElementWCCounter;

import java.util.Locale;

public class WCTrigger implements ChatReceiveModule {
    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        final String message = event.message.getUnformattedText().toLowerCase(Locale.ENGLISH);

        if (message.contains("wc")) {
            ElementWCCounter.wcCounter = +1;
        }
    }
}



