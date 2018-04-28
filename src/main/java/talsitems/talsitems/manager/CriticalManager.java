package talsitems.talsitems.manager;

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
            p.sendMessage("会心の一撃");
        }
    }
}
