package net.iridescent.wyvtilities.GUI;



import ga.matthewtgm.lib.gui.GuiTransButton;
import net.iridescent.wyvtilities.Wyvtilities;
import net.iridescent.wyvtilities.listener.GUIListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.json.simple.JSONObject;

import java.io.IOException;


public class GUIMain extends GuiScreen {


    @Override
    public void initGui() {
        this.buttonList.add(new GuiTransButton(0, this.width / 2 - 50, this.height - 20, 100, 20, "Close"));
        this.buttonList.add(new GuiTransButton(1, this.width - 80, 0, 80, 20, "HUD Editor"));
        this.buttonList.add(new GuiTransButton(2, this.width / 2 - 50, this.height / 2 - 60, 100, 20, "Toggle: " + (Wyvtilities.getInstance().isToggled() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        this.buttonList.add(new GuiTransButton(3, this.width / 2 - 50, this.height / 2, 100, 20, "Credits"));
        this.buttonList.add(new GuiTransButton(4, this.width / 2 - 50, this.height / 2 - 40, 100, 20, "Pause button: " + (GUIListener.getInstance().mustAddPauseButton() ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF")));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        final JSONObject mainConfigObj = new JSONObject();
        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
            case 1:
                Minecraft.getMinecraft().displayGuiScreen(new GUIConfiguration(this));
                break;
            case 2:
                Wyvtilities.getInstance().setToggled(!Wyvtilities.getInstance().isToggled());

                mainConfigObj.put("full_toggle", Wyvtilities.getInstance().isToggled());
                mainConfigObj.put("pause_button", GUIListener.getInstance().mustAddPauseButton());
                Wyvtilities.getFileHandler().save("main", Wyvtilities.getFileHandler().modDir, mainConfigObj);

                Minecraft.getMinecraft().displayGuiScreen(this);
                break;
            case 3:
                Minecraft.getMinecraft().displayGuiScreen(new GUICredits(this));
                break;
            case 4:
                GUIListener.getInstance().setAddPauseButton(!GUIListener.getInstance().mustAddPauseButton());

                mainConfigObj.put("full_toggle", Wyvtilities.getInstance().isToggled());
                mainConfigObj.put("pause_button", GUIListener.getInstance().mustAddPauseButton());
                Wyvtilities.getFileHandler().save("main", Wyvtilities.getFileHandler().modDir, mainConfigObj);

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
        this.renderHoverText(mouseX, mouseY);
        GlStateManager.pushMatrix();
        int scale = 3;
        GlStateManager.scale(scale, scale, 0);
        drawCenteredString(this.fontRendererObj, EnumChatFormatting.DARK_PURPLE + "Wyvtilities" , width / 2 / scale, 5 / scale + 10, -1);
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void renderHoverText(int mouseX, int mouseY) {
    }

}
