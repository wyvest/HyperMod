package net.iridescent.wyvtilities.hud.elements.impl;


import net.iridescent.wyvtilities.hud.elements.Elements;
import net.iridescent.wyvtilities.hud.elements.ElementPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ElementComboDisplay extends Elements {

    private long lastHit = 0L;
    private int combo = 0;

    public ElementComboDisplay() {
        super("Combo Counter");
        if (this.prefix == null) prefix = "Combo Counter";
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    protected void onAttack(AttackEntityEvent event) {
        if (event.entity instanceof EntityPlayerSP) {
            lastHit = System.currentTimeMillis();
            combo += 1;
        }
    }

    @SubscribeEvent
    protected void onDamage(LivingHurtEvent event) {
        if(event.entity instanceof EntityPlayerMP && event.entity.getDisplayName().getUnformattedText().equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getDisplayName().getUnformattedText())) {
            lastHit = 0;
            combo = 0;
        }
    }

    @Override
    public void onRendered(ElementPosition position) {
        this.setToggle(false);
        if (System.currentTimeMillis() - lastHit > 3000)
            combo = 0;
        this.setRenderedValue(combo == 0 ? "none" : combo + " Combo");
        this.height = 10 * this.getPosition().getScale();
        super.onRendered(position);
    }

}