package talsitems.talsitems.manager;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import talsitems.talsitems.TALSITEMS;

import java.util.Random;

public class BlockManager {

    public void setBlock(EntityDamageEvent e,int block,int chance,Player p)
    {
        if(e.isCancelled())
        {
            return;
        }

        //ダメージケース
        if(e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK)
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
                    p.sendMessage("§6§l≪§eデバッグ§6§l≫§fブロック: "+block+"% 攻撃を受けなかった");
                }
            }

            //計算
            e.setDamage(e.getDamage()*(block/100));
            //メッセージ
            p.sendMessage("§c§l攻撃を防いだ！");
            //音
            p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1.2f,1.5f);
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR,0.5f,0.3f);
        }
    }
}
