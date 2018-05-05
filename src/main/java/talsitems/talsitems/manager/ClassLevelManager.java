package talsitems.talsitems.manager;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import talsapi.talsapi.TALSAPI;
import talsapi.talsapi.api.classes.MainClass;
import talsitems.talsitems.TALSITEMS;

public class ClassLevelManager {

    public void setConditions(PlayerInteractEvent e,int level,String classes)
    {
        MainClass mc = TALSAPI.getPlayerDeta(e.getPlayer()).getMainClass();

        //クラスが違う場合
        if(classes.equals(mc.getPrefix()))
        {
            TALSITEMS.ClancelDamage.put(e.getPlayer(), true);
            return;
        }

        //レベルが違う場合
        if(mc.getLevel() != level)
        {
            TALSITEMS.ClancelDamage.put(e.getPlayer(), true);
        }
    }
}
