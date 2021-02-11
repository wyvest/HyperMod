package fun.iridescent.hypermod;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.universal.ChatColor;
import club.sk1er.mods.core.util.MinecraftUtils;
import fun.iridescent.hypermod.chat.ChatHandler;
import fun.iridescent.hypermod.chat.CommandQueue;
import fun.iridescent.hypermod.commands.HyperModCommands;
import fun.iridescent.hypermod.exceptions.OutOfDateException;
import fun.iridescent.hypermod.files.Config;
import fun.iridescent.hypermod.modcore.ModCoreInstaller;
import fun.iridescent.hypermod.others.LanguageHandler;
import fun.iridescent.hypermod.others.LocrawUtil;
import fun.iridescent.hypermod.others.References;
import ga.matthewtgm.lib.TGMLib;
import ga.matthewtgm.lib.util.guiscreens.GuiAppendingManager;
import ga.matthewtgm.lib.util.keybindings.KeyBind;
import ga.matthewtgm.lib.util.keybindings.KeyBindManager;
import fun.iridescent.hypermod.listener.PlayerListener;
import fun.iridescent.hypermod.others.VersionChecker;
import fun.iridescent.hypermod.chat.modules.gexp;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.input.Keyboard;


@Mod(name = References.NAME, version = References.VER, modid = References.MODID, clientSideOnly = true)
public class HyperMod {


    @Mod.Instance(References.MODID)

    public static HyperMod INSTANCE;


    public static HyperMod getInstance() {
        return INSTANCE;
    }


    private static final VersionChecker VERSION_CHECKER = new VersionChecker();
    private final ChatHandler chatHandler = new ChatHandler();
    private final CommandQueue commandQueue = new CommandQueue();
    private final LocrawUtil locrawUtil = new LocrawUtil();
    private static gexp gexp = new gexp();
    private boolean toggled = true;
    private boolean latestVersion;
    private static Config config = new Config();
    private static final LanguageHandler languageHandler = new LanguageHandler();



    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {
        TGMLib.getInstance().setModName(References.NAME);

        if (VERSION_CHECKER.getEmergencyStatus())
            throw new OutOfDateException("PLEASE UPDATE TO THE NEW VERSION OF " + References.NAME + "\nTHIS IS AN EMERGENCY!");
        this.latestVersion = VERSION_CHECKER.getVersion().equals(References.VER);

        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);

    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        GuiAppendingManager.getInstance().init();
        final EventBus eventBus = MinecraftForge.EVENT_BUS;
        final ClientCommandHandler commandRegister = ClientCommandHandler.instance;
        config.preload();



        eventBus.register(this);
        eventBus.register(new PlayerListener());
        eventBus.register(chatHandler);
        eventBus.register(locrawUtil);
        eventBus.register(commandQueue);
        eventBus.register(languageHandler);


        GuiAppendingManager.getInstance().init();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new PlayerListener());
        KeyBindManager.getInstance().addKeyBind(new KeyBind("Open GUI", Keyboard.KEY_M) {
            @Override
            public void onPressed() {
                ModCore.getInstance().getGuiHandler().open(HyperMod.INSTANCE.getHyperLeagueConfig().gui());
            }
        });
        KeyBindManager.getInstance().init();
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
        commandRegister.registerCommand(new HyperModCommands());
    }


    public VersionChecker getVersionChecker() {
        return VERSION_CHECKER;
    }

    public boolean isLatestVersion() {
        return latestVersion;
    }

    public void sendMessage(String message) {
        MinecraftUtils.sendMessage(ChatColor.GOLD + "[HyperLeague] ", ChatColor.translateAlternateColorCodes('&', message));
    }
    public Config getHyperLeagueConfig() {
        return config;
    }

    public LanguageHandler getLanguageHandler() {
        return getLanguageHandler();
    }

    public CommandQueue getCommandQueue() {
        return commandQueue;
    }
    public LocrawUtil getLocrawUtil() { return locrawUtil;}
    public static gexp getGexp() {return gexp;}
    public static Config hyperLeagueConfig() {return config;}

}
