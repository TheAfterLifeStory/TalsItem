package talsitems.talsitems.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import talsitems.talsitems.TALSITEMS;

public class DefenseManager {

    public void setDefense(EntityDamageEvent e,double defense,Player p)
    {
        //0かどうか
        if(defense <= 0)
        {
            return;
        }

        //キャンセル確認
        if(e.isCancelled())
        {
            return;
        }

        defense = (defense*0.6);
        double damage = e.getDamage();

        //回復させない
        if(damage < 0)
        {
            damage = 0;
        }

        e.setDamage(damage-defense);

        //デバッグログ
        if(TALSITEMS.PlayerDebug.containsKey(p)) {
            if (TALSITEMS.PlayerDebug.get(p)) {
                p.sendMessage("§6§l≪§eデバッグ§6§l≫§f防御: "+defense+" 防いだ(元ダメージ :"+damage+")");
            }
        }
    }
}
