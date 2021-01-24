package net.iridescent.wyvtilities;

import club.sk1er.mods.core.universal.ChatColor;
import club.sk1er.mods.core.util.MinecraftUtils;
import ga.matthewtgm.lib.util.guiscreens.GuiAppendingManager;
import ga.matthewtgm.lib.util.keybindings.KeyBind;
import ga.matthewtgm.lib.util.keybindings.KeyBindManager;
import net.iridescent.wyvtilities.GUI.GUIMain;
import net.iridescent.wyvtilities.chat.ChatHandler;
import net.iridescent.wyvtilities.chat.CommandQueue;
import net.iridescent.wyvtilities.commands.HyperLeagueCMDs;
import net.iridescent.wyvtilities.commands.cmds;
import net.iridescent.wyvtilities.exceptions.OutOfDateException;
import net.iridescent.wyvtilities.files.FileHandler;
import net.iridescent.wyvtilities.files.HyperLeagueConfig;
import net.iridescent.wyvtilities.hud.elements.ElementManager;
import net.iridescent.wyvtilities.listener.GUIListener;
import net.iridescent.wyvtilities.listener.PlayerListener;
import net.iridescent.wyvtilities.modcore.ModCoreInstaller;
import net.iridescent.wyvtilities.others.LanguageHandler;
import net.iridescent.wyvtilities.others.LocrawUtil;
import net.iridescent.wyvtilities.others.References;
import net.iridescent.wyvtilities.others.VersionChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.json.simple.JSONObject;
import org.lwjgl.input.Keyboard;


@Mod(name = References.NAME, version = References.VER, modid = References.MODID, clientSideOnly = true)
public class Wyvtilities {


    @Mod.Instance(References.MODID)

    public static Wyvtilities INSTANCE;
    public static Wyvtilities getInstance() {
        return INSTANCE;
    }


    private static final FileHandler FILE_HANDLER = new FileHandler();
    private static final ElementManager ELEMENT_MANAGER = new ElementManager();
    private static final VersionChecker VERSION_CHECKER = new VersionChecker();
    private final ChatHandler chatHandler = new ChatHandler();
    private final CommandQueue commandQueue = new CommandQueue();
    private final LocrawUtil locrawUtil = new LocrawUtil();
    private boolean toggled = true;
    private boolean latestVersion;
    private final HyperLeagueConfig config = new HyperLeagueConfig();
    private static final LanguageHandler languageHandler = new LanguageHandler();


    public GuiScreen configGui;


    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {

        if (VERSION_CHECKER.getEmergencyStatus())
            throw new OutOfDateException("PLEASE UPDATE TO THE NEW VERSION OF " + References.NAME + "\nTHIS IS AN EMERGENCY!");
        this.latestVersion = VERSION_CHECKER.getVersion().equals(References.VER);

        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);


        boolean isConfigFileNull = Wyvtilities.getFileHandler().load("main", Wyvtilities.getFileHandler().modDir) == null;
        if (!isConfigFileNull) {
            this.toggled = (boolean) getFileHandler().load("main", getFileHandler().modDir).get("full_toggle");
            GUIListener.getInstance().setAddPauseButton((boolean) getFileHandler().load("main", getFileHandler().modDir).get("pause_button"));
            this.getElementManager().setShowInChat((boolean) getFileHandler().load("main", getFileHandler().modDir).get("show_in_chat"));
        }
        final JSONObject object = new JSONObject();
        object.put("full_toggle", this.toggled);
        object.put("pause_button", GUIListener.getInstance().mustAddPauseButton());
        object.put("show_in_chat", this.getElementManager().isShowInChat());
        getFileHandler().save("main", getFileHandler().modDir, object);
    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        GuiAppendingManager.getInstance().init();
        final EventBus eventBus = MinecraftForge.EVENT_BUS;
        final ClientCommandHandler commandRegister = ClientCommandHandler.instance;
        this.configGui = new GUIMain();
        getFileHandler().init();



        eventBus.register(this);
        eventBus.register(GUIListener.getInstance());
        eventBus.register(new PlayerListener());
        eventBus.register(chatHandler);
        eventBus.register(locrawUtil);
        eventBus.register(commandQueue);
        eventBus.register(languageHandler);


        KeyBindManager.getInstance().addKeyBind(new KeyBind("Open GUI", Keyboard.KEY_N) {
            @Override
            public void onPressed() {
                Minecraft.getMinecraft().displayGuiScreen(Wyvtilities.getInstance().configGui);
            }
        });
        KeyBindManager.getInstance().init(References.NAME);
        net.iridescent.wyvtilities.modcore.ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
        ClientCommandHandler.instance.registerCommand(new cmds());
        commandRegister.registerCommand(new HyperLeagueCMDs());
        this.getElementManager().init();
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public static FileHandler getFileHandler() {
        return FILE_HANDLER;
    }

    public ElementManager getElementManager() {
        return ELEMENT_MANAGER;
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
    public HyperLeagueConfig getConfig() {
        return config;
    }

    public LanguageHandler getLanguageHandler() {
        return getLanguageHandler();
    }

    public CommandQueue getCommandQueue() {
        return commandQueue;
    }
    public LocrawUtil getLocrawUtil() {
        return locrawUtil;
    }

}
