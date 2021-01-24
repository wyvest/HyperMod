package net.iridescent.wyvtilities.GUI;

import ga.matthewtgm.lib.gui.GuiTransButton;
import ga.matthewtgm.lib.gui.GuiTransSlider;
import net.iridescent.wyvtilities.Wyvtilities;
import net.iridescent.wyvtilities.hud.elements.Elements;
import net.iridescent.wyvtilities.hud.elements.ElementPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiSlider;
import org.json.simple.JSONObject;

import java.io.IOException;

public class GUIElement extends GuiScreen {

    protected final Elements elements;
    protected final GuiScreen parent;

    public GUIElement(GuiScreen parent, Elements elements) {
        this.parent = parent;
        this.elements = elements;
    }

    public GUIElement(Elements elements) {
        this.parent = null;
        this.elements = elements;
    }

    protected GuiButton showBrackets;
    protected GuiButton showPrefix;
    protected GuiButton chromaToggle;

    protected GuiSlider scaleSlider;

    protected GuiSlider rSlider;
    protected GuiSlider gSlider;
    protected GuiSlider bSlider;

    protected GuiSlider bgRSlider;
    protected GuiSlider bgGSlider;
    protected GuiSlider bgBSlider;
    protected GuiSlider bgASlider;


    @Override
    public void initGui() {
        this.buttonList.add(new GuiTransButton(0, this.width / 2 - 50, this.height - 20, 100, 20, "Save and go back"));
        this.buttonList.add(new GuiTransButton(1, this.width / 2 - 105, this.height / 2 - 100, 100, 20, "Toggle: " + (this.elements.isToggled() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(showBrackets = new GuiTransButton(2, this.width / 2 + 5, this.height / 2 - 100, 100, 20,"Show Brackets: " + (this.elements.shouldRenderBrackets() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(new GuiTransButton(3, this.width / 2 - 105, this.height / 2 - 70, 100, 20, "Text Shadow: " + (this.elements.getTextShadow() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(scaleSlider = new GuiTransSlider(4, this.width / 2 + 5, this.height / 2 - 70, 100, 20, "Scale: ", "", 1, 5, this.elements.getPosition().getScale(), true, true));
        this.buttonList.add(rSlider = new GuiTransSlider(5, this.width / 2 - 105, this.height / 2 - 40, 100, 20, "Red: ", "", 1, 255, this.elements.colour.getR(), false, true));
        this.buttonList.add(gSlider = new GuiTransSlider(6, this.width / 2 + 5, this.height / 2 - 40, 100, 20, "Green: ", "", 1, 255, this.elements.colour.getG(), false, true));
        this.buttonList.add(bSlider = new GuiTransSlider(7, this.width / 2 - 105, this.height / 2 - 10, 100, 20, "Blue: ", "", 1, 255, this.elements.colour.getB(), false, true));
        this.buttonList.add(new GuiTransButton(8, this.width / 2 + 5, this.height / 2 - 10, 100, 20, "Background: " + (this.elements.getBackground() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(bgRSlider = new GuiTransSlider(9, this.width / 2 - 105, this.height / 2 + 20, 100, 20, "BG Red: ", "", 1, 255, this.elements.backgroundColor.getR(), false, true));
        this.buttonList.add(bgGSlider = new GuiTransSlider(10, this.width / 2 + 5, this.height / 2 + 20, 100, 20, "BG Green: ", "", 1, 255, this.elements.backgroundColor.getG(), false, true));
        this.buttonList.add(bgBSlider = new GuiTransSlider(11, this.width / 2 - 105, this.height / 2 + 50, 100, 20, "BG Blue: ", "", 1, 255, this.elements.backgroundColor.getB(), false, true));
        this.buttonList.add(bgASlider = new GuiTransSlider(12, this.width / 2 + 5, this.height / 2 + 50, 100, 20, "BG Alpha: ", "", 1, 255, this.elements.backgroundColor.getA(), false, true));
        this.buttonList.add(showPrefix = new GuiTransButton(13, this.width / 2 - 105, this.height / 2 + 80, 100, 20, "Show Prefix: " + (this.elements.shouldShowPrefix() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(chromaToggle = new GuiTransButton(14, this.width / 2 + 5, this.height / 2 + 80, 100, 20, "Chroma: " + (this.elements.isChroma() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) Minecraft.getMinecraft().displayGuiScreen(this.parent);
        if (button.id == 1) {
            this.elements.setToggle(!this.elements.isToggled());
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        if (button.id == 2) {
            this.elements.setRenderBrackets(!this.elements.shouldRenderBrackets());
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        if (button.id == 3) {
            this.elements.setTextShadow(!this.elements.getTextShadow());
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        if(button.id == 4) this.elements.getPosition().setScale((float) this.scaleSlider.getValue());
        if(button.id == 5) this.elements.colour.setR(this.rSlider.getValueInt());
        if(button.id == 6) this.elements.colour.setG(this.gSlider.getValueInt());
        if(button.id == 7) this.elements.colour.setB(this.bSlider.getValueInt());
        if (button.id == 8) {
            this.elements.setBackgroundToggle(!this.elements.getBackground());
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        if(button.id == 9) this.elements.backgroundColor.setR(this.bgRSlider.getValueInt());
        if(button.id == 10) this.elements.backgroundColor.setG(this.bgGSlider.getValueInt());
        if(button.id == 11) this.elements.backgroundColor.setB(this.bgBSlider.getValueInt());
        if(button.id == 12) this.elements.backgroundColor.setA(this.bgASlider.getValueInt());
        if(button.id == 13) {
            this.elements.setShowPrefix(!this.elements.shouldShowPrefix());
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        if(button.id == 14) {
            this.elements.setChroma(!this.elements.isChroma());
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        this.elements.onRendered(new ElementPosition((int) (this.width / 2 - 30 / this.elements.getPosition().getScale()), 0, this.elements.getPosition().getScale()));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        for (Elements elements : Wyvtilities.getInstance().getElementManager().getElements()) {
            elements.onSave(new JSONObject());
            elements.onLoad();
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        this.elements.getPosition().setScale((float) this.scaleSlider.getValue());
        this.elements.colour.setR(this.rSlider.getValueInt());
        this.elements.colour.setG(this.gSlider.getValueInt());
        this.elements.colour.setB(this.bSlider.getValueInt());
        this.elements.backgroundColor.setR(this.bgRSlider.getValueInt());
        this.elements.backgroundColor.setG(this.bgGSlider.getValueInt());
        this.elements.backgroundColor.setB(this.bgBSlider.getValueInt());
        this.elements.backgroundColor.setA(this.bgASlider.getValueInt());
    }

}
