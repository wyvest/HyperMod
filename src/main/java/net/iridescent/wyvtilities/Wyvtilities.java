package net.iridescent.wyvtilities;

import club.sk1er.mods.core.universal.ChatColor;
import club.sk1er.mods.core.util.MinecraftUtils;
import net.iridescent.wyvtilities.GUI.GUIMain;
import net.iridescent.wyvtilities.chat.ChatHandler;
import net.iridescent.wyvtilities.commands.HyperLeagueCMDs;
import net.iridescent.wyvtilities.commands.cmds;
import net.iridescent.wyvtilities.files.FileHandler;
import net.iridescent.wyvtilities.files.HyperLeagueConfig;
import net.iridescent.wyvtilities.hud.elements.ElementManager;
import net.iridescent.wyvtilities.listener.GUIListener;
import net.iridescent.wyvtilities.listener.PlayerListener;
import net.iridescent.wyvtilities.others.Language;
import net.iridescent.wyvtilities.others.References;
import net.iridescent.wyvtilities.others.VersionChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
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
    private boolean toggled = true;
    private boolean latestVersion;
    private final HyperLeagueConfig config = new HyperLeagueConfig();

    public final KeyBinding openGuiKeyBinding = new KeyBinding("Open GUI", Keyboard.KEY_M, "Wyvtilities");
    public GuiScreen configGui;


    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {

        if(VERSION_CHECKER.getEmergencyStatus()) throw new RuntimeException("Please update to the latest version of " + References.NAME + ", so you can get the latest features and bug fixes.");
        this.latestVersion = VERSION_CHECKER.getVersion().equals(References.VER);

        final ModMetadata modMetadata = event.getModMetadata();
        modMetadata.description = "A utility mod that adds epic stuff to the game.";

        boolean isConfigFileNull = Wyvtilities.getFileHandler().load("main", Wyvtilities.getFileHandler().modDir) == null;
        if (!isConfigFileNull)
            this.toggled = (boolean) getFileHandler().load("main", getFileHandler().modDir).get("full_toggle");
        final JSONObject object = new JSONObject();
        object.put("full_toggle", this.toggled);
        object.put("pause_button", GUIListener.getInstance().mustAddPauseButton());
        getFileHandler().save("main", getFileHandler().modDir, object);
    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        final EventBus eventBus = MinecraftForge.EVENT_BUS;
        final ClientCommandHandler commandRegister = ClientCommandHandler.instance;
        this.configGui = new GUIMain();
        getFileHandler().init();



        eventBus.register(this);
        eventBus.register(GUIListener.getInstance());
        eventBus.register(new PlayerListener());
        eventBus.register(chatHandler);

        ClientRegistry.registerKeyBinding(openGuiKeyBinding);
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

    public Language getLanguageHandler() {
        return getLanguageHandler();
    }
}
