package fun.iridescent.hypermod.files;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.*;

import java.io.File;

public class Config extends Vigilant {

    @Property(
            type = PropertyType.TEXT,
            name = "Hypixel API Key",
            description = "Your Hypixel API key, which can be obtained from /api new. Required for some features.\nSet this with /wyvtils setkey <key>.",
            category = "General",
            subcategory = "API"
    )
    public static String apiKey = "";

    @Property(
            type = PropertyType.SWITCH, name = "Guild Welcome Message",
            description = "Sends a HyperLeague specific guild welcome message when a person join HyperLeague.",
            category = "Chat"
    )
    public static boolean guildWelcomer;


    public Config() {
        super(new File("./config/wyvest/hyperleague.toml"));
        initialize();
    }

}
