package talsitems.talsitems.manager;

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

            e.setDamage(e.getDamage()*(block/100));
            p.sendMessage("§e§lブロック");
        }
    }
}
