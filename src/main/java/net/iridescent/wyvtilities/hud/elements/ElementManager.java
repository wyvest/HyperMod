package net.iridescent.wyvtilities.hud.elements;


import net.iridescent.wyvtilities.Wyvtilities;
import net.minecraft.client.gui.GuiChat;
import net.iridescent.wyvtilities.hud.elements.impl.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ElementManager {

    private final List<Elements> elements = new ArrayList<>();

    private boolean showInChat;
    public boolean isShowInChat() {
        return showInChat;
    }
    public void setShowInChat(boolean showInChat) {
        this.showInChat = showInChat;
    }


    public void init() {
        this.getElements().add(new ElementWCCounter());
        this.getElements().add(new ElementComboDisplay());

        MinecraftForge.EVENT_BUS.register(this);
        for(Elements elements : this.getElements()) {

            if(elements.isToggled()) elements.onEnabled();
            else elements.onDisabled();
        }

    }

    @SubscribeEvent
    protected void onGameOverlayRendered(RenderGameOverlayEvent.Post event) {
        if(event.type == RenderGameOverlayEvent.ElementType.ALL) {
            for (Elements elements : this.getElements()) {
                if (Wyvtilities.getInstance().isToggled() && (Minecraft.getMinecraft().currentScreen == null || this.showInChat()) && elements.isToggled() && Minecraft.getMinecraft().thePlayer != null)
                    elements.onRendered(elements.getPosition());
            }
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
        }
    }

    private boolean showInChat() {
        return (this.isShowInChat() && Minecraft.getMinecraft().currentScreen instanceof GuiChat);
    }


    public <T extends Elements> T getElement(Class<T> elementClass) {
        for(Elements elements : this.getElements()) {
            if(elementClass.isAssignableFrom(elements.getClass())) {
                return (T) elements;
            }
        }
        return null;
    }

    public List<Elements> getElements() {
        return elements;
    }

}