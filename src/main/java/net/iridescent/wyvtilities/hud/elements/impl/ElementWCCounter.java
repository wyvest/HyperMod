package net.iridescent.wyvtilities.hud.elements.impl;


import net.iridescent.wyvtilities.hud.elements.Elements;
import net.iridescent.wyvtilities.hud.elements.ElementPosition;


public class ElementWCCounter extends Elements{
    public static int wcCounter = 0;

    public ElementWCCounter() {
        super("WC");
        if (this.prefix == null) prefix = "";
    }

    @Override
    public void onRendered(ElementPosition position) {
        this.setRenderedValue(wcCounter + " WCs");
        this.height = (int) (10 * this.getPosition().getScale());
        super.onRendered(position);
    }
}
