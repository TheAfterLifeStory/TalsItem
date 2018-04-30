package talsitems.talsitems.manager;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import talsitems.talsitems.TALSITEMS;

import java.util.Random;

public class AttackChanceManager {

    public void setCritical(EntityDamageByEntityEvent e, int chance, Player p)
    {
        //ランダム作成
        Random random = new Random();
        int r = random.nextInt(100);
        //確率
        if(chance <= r)
        {
            //デバッグログ
            if(TALSITEMS.PlayerDebug.containsKey(p)) {
                if (TALSITEMS.PlayerDebug.get(p)) {
                    p.sendMessage("§6§l≪§eデバッグ§6§l≫§f攻撃を与えられなかった'(Cancelled)");
                }
            }
            //ダメージを与えない
            e.setCancelled(true);
            p.sendMessage("§a§lMiss!");

            //音
            p.playSound(p.getLocation(), Sound.ENTITY_WITCH_THROW,0.8f, 4);
        }
    }
}
