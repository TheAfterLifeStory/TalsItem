package talsitems.talsitems.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import talsitems.talsitems.TALSITEMS;

import java.util.Random;

public class AvoidanceChanceManager {

    public void setAvoidance(EntityDamageEvent e, int chance, Player p)
    {
        //ランダム作成
        Random random = new Random();
        int r = random.nextInt(100);
        //確率
        if(chance >= r)
        {
            //デバッグログ
            if(TALSITEMS.PlayerDebug.containsKey(p)) {
                if (TALSITEMS.PlayerDebug.get(p)) {
                    p.sendMessage("§6§l≪§eデバッグ§6§l≫§f攻撃をかわした'(Cancelled)");
                }
            }

            //ダメージを与えない
            e.setCancelled(true);
            p.sendMessage("§6攻撃をかわした");
        }
    }
}
