package net.iridescent.wyvtilities.chat.modules;


import net.iridescent.wyvtilities.chat.*;
import net.iridescent.wyvtilities.files.HyperLeagueConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

public class GuildWelcomer implements ChatReceiveModule {

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void onMessageReceived(@NotNull ClientChatReceivedEvent event) {
        final String text = event.message.getUnformattedText();
        final Matcher matcher = getLanguage().guildPlayerJoinRegex.matcher(text);

        if (matcher.matches()) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/gc Welcome to HyperLeague " + matcher.group("player") + "! Type /g discord for the invite link!");
        }
    }

    @Override
    public boolean isEnabled() {
        return HyperLeagueConfig.guildWelcomer;
    }
}