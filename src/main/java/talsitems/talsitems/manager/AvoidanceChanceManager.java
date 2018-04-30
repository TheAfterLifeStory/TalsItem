package talsitems.talsitems.manager;

import org.bukkit.Sound;
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
            p.sendMessage("§b§l攻撃を避けた！");

            p.setVelocity(p.getLocation().getDirection().multiply(-0.5));

            //音
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP,1.2f,0.4f);
            p.playSound(p.getLocation(), Sound.ENTITY_WITCH_THROW, 1.2f,2f);
        }
    }
}
