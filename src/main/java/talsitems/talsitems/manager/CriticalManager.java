package talsitems.talsitems.manager;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import talsitems.talsitems.TALSITEMS;

import java.util.Random;

public class CriticalManager {
    public void setCritical(EntityDamageByEntityEvent e,int chance,double damage,Player p)
    {
        //キャンセルしているか
        if(e.isCancelled())
        {
            return;
        }

        //ランダム作成
        Random random = new Random();
        int r = random.nextInt(100);
        //確率
        if(chance >= r)
        {
            //デバッグログ
            if(TALSITEMS.PlayerDebug.containsKey(p)) {
                if (TALSITEMS.PlayerDebug.get(p)) {
                    p.sendMessage("§6§l≪§eデバッグ§6§l≫§fクリティカル");
                }
            }

            //ダメージを変更
            e.setDamage(e.getDamage()*damage);
            //p.sendMessage("会心の一撃");
            //音
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR,2f,1f);
            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.5f,1f);

            Location l = e.getEntity().getLocation();
            e.getEntity().getWorld().spawnParticle(Particle.VILLAGER_ANGRY,l,2,0.3,0.2,0.3,0);
            e.getEntity().getWorld().spawnParticle(Particle.CLOUD,l,4,0.5,1.5,0.5,0);
            e.getEntity().getWorld().spawnParticle(Particle.CRIT,l,10,0.5,1.5,0.5,0);
        }
    }
}
