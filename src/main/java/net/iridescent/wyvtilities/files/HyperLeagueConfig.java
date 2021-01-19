package net.iridescent.wyvtilities.files;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;
import java.io.File;

@SuppressWarnings("unused")
public class HyperLeagueConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH, name = "Guild Welcome Message",
            description = "Sends a HyperLeague specific guild welcome message when a person join HyperLeague.",
            category = "Chat"
    )
    public static boolean guildWelcomer;


    public HyperLeagueConfig() {
        super(new File("./config/hyperleague.toml"));
        initialize();
    }

}
