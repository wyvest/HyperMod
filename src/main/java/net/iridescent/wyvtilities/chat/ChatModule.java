/*
 * Hytilities - Hypixel focused Quality of Life mod.
 * Copyright (C) 2020  Sk1er LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.iridescent.wyvtilities.chat;


import club.sk1er.mods.core.universal.ChatColor;
import net.iridescent.wyvtilities.Wyvtilities;
import net.iridescent.wyvtilities.others.LanguageData;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.jetbrains.annotations.NotNull;

interface ChatModule {


    default int getPriority() {
        return 0;
    }

    default boolean isEnabled() {
        return true;
    }


    /**
     * Default pedantically static utility method to allow {@link ChatModule}s to color messages
     * without a long line of code.
     */
    @NotNull
    default IChatComponent colorMessage(@NotNull String message) {
        return new ChatComponentText(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Get the user's Hypixel language setting.
     */
    @NotNull
    default LanguageData getLanguage() {
        return Wyvtilities.INSTANCE.getLanguageHandler().getCurrent();
    }

}
