package fun.iridescent.hypermod.chat.modules;

//mmmmmmmmmmmmmm

import club.sk1er.mods.core.util.Multithreading;
import fun.iridescent.hypermod.files.Config;
import ga.matthewtgm.json.objects.JsonObject;
import ga.matthewtgm.json.parsing.JsonParser;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class gexp {

    JsonObject apiObj;
    public static int gexp;
    long lastRequest = System.currentTimeMillis();

    {
        try {
            apiObj = JsonParser.parseObj(new BufferedReader(new InputStreamReader(new URL("https://api.hypixel.net/guild?key=" + Config.apiKey + "&player=" + Minecraft.getMinecraft().thePlayer.getUniqueID()).openStream())).readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getCurrentESTTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        return simpleDateFormat.format(new Date());
    }
    public int getGEXP() {
        try {
            long diff = System.currentTimeMillis() - lastRequest;
            if(diff < 500){
                Multithreading.runAsync(() -> {
                    try {
                        wait((long) 0.5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            gexp = (Integer) apiObj.get("response.guild.members." + Minecraft.getMinecraft().thePlayer.getUniqueID() + ".expHistory." + getCurrentESTTime());
            return gexp;
        } catch(Exception e) {
            e.printStackTrace();
            return new Integer(gexp);
        }
    }

}
