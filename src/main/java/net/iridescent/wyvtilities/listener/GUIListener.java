package net.iridescent.wyvtilities.listener;

import net.iridescent.wyvtilities.Wyvtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GUIListener {

    private static GUIListener INSTANCE = new GUIListener();
    public static GUIListener getInstance() {
        return INSTANCE;
    }

    private boolean addPauseButton;
    public boolean mustAddPauseButton() {
        return addPauseButton;
    }
    public void setAddPauseButton(boolean addPauseButton) {
        this.addPauseButton = addPauseButton;
    }

    @SubscribeEvent
    protected void onGuiInit(GuiScreenEvent.InitGuiEvent event) {
        if(!(event.gui instanceof GuiIngameMenu)) return;
        if(!this.addPauseButton) return;
        event.buttonList.add(new GuiButton(100001, event.gui.width / 2 - 50, event.gui.height - 25, 100, 20, "SimpleHUD"));
    }

    @SubscribeEvent
    protected void onGuiActionPerformed(GuiScreenEvent.ActionPerformedEvent event) {
        if(!(event.gui instanceof GuiIngameMenu)) return;
        if(event.button.id == 100001) Minecraft.getMinecraft().displayGuiScreen(Wyvtilities.getInstance().configGui);
    }

}
