package net.iridescent.wyvtilities.hud.elements.impl;

import net.iridescent.wyvtilities.hud.elements.ElementPosition;
import net.iridescent.wyvtilities.hud.elements.Elements;

public class HeightLimit extends Elements {
    public static int height = 0;

    public HeightLimit() {
        super("Height Limit");
        if (this.prefix == null) prefix = "";
    }

    @Override
    public void onRendered(ElementPosition position) {
        this.setRenderedValue(height + ""); //gui doesnt like int
        this.height = (int) (10 * this.getPosition().getScale());
        super.onRendered(position);
    }
}
