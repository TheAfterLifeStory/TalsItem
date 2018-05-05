package talsitems.talsitems.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import talsapi.talsapi.TALSAPI;
import talsapi.talsapi.api.classes.MainClass;
import talsitems.talsitems.TALSITEMS;

public class ClassLevelManager {

    public void setConditions(Player p,int level,String classes)
    {
        MainClass mc = TALSAPI.getPlayerDeta(p).getMainClass();

        //クラスあっている
        if(classes.equals(""))
        {
            return;
        }

        if(!classes.equals(mc.getPrefix()))
        {
            p.sendMessage("職業が違うため使えません");
            TALSITEMS.ClancelWand.put(p,true);
            return;
        }

        //レベルがあっている
        if(mc.getLevel() < level)
        {
            p.sendMessage("レベルが低いため使えません");
            TALSITEMS.ClancelWand.put(p,true);
        }
    }

    public void setConditions(EntityDamageByEntityEvent e, Player p, int level, String classes)
    {
        MainClass mc = TALSAPI.getPlayerDeta(p).getMainClass();

        //クラスあっている
        if(classes.equals(""))
        {
            return;
        }

        if(!classes.equals(mc.getPrefix()))
        {
            e.setCancelled(true);
            TALSITEMS.ClancelDamage.put(p,true);
            return;
        }

        //レベルがあっている
        if(mc.getLevel() < level)
        {
            e.setCancelled(true);
            TALSITEMS.ClancelDamage.put(p,true);
            return;
        }
    }
}
