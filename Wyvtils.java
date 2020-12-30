package net.iridescent;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Wyvtils.MODID, name = Wyvtils.MOD_NAME, version = Wyvtils.VERSION)
public class Wyvtils {

    public static final String MODID = "wyvtils";
    public static final String MOD_NAME = "Wyvtilities";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // install (if not found) & initialize ModCore on startup
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
    }
}
