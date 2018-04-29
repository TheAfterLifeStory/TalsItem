package talsitems.talsitems.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import talsitems.talsitems.TALSITEMS;

public class PenetrateManager {

    public void setPenetrate(EntityDamageByEntityEvent e,double damage, Player p)
    {

        //ダメージが0だった時
        if(damage <= 0)
        {
            return;
        }

        //キャンセルしているか
        if(e.isCancelled())
        {
            return;
        }

        //ダメージを追加
        e.setDamage(e.getDamage()+damage);

        //デバッグログ
        if(TALSITEMS.PlayerDebug.containsKey(p)) {
            if (TALSITEMS.PlayerDebug.get(p)) {
                p.sendMessage("§6§l≪§eデバッグ§6§l≫§f防具貫通: "+damage+" ダメージ");
            }
        }
    }
}
