package talsitems.talsitems.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import talsitems.talsitems.TALSITEMS;
import talsitems.talsitems.manager.AvoidanceChanceManager;
import talsitems.talsitems.manager.BlockManager;
import talsitems.talsitems.manager.DefenseManager;

public class PlayerDamageListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onDamaged(EntityDamageEvent e)
    {
        //プレイヤーか
        if(!(e.getEntity() instanceof Player))
        {
            return;
        }

        //プレイヤー
        Player p = (Player) e.getEntity();

        //アイテム
        ItemStack[] itemStack = {p.getInventory().getItemInMainHand(),
                p.getInventory().getItemInOffHand(),
                p.getInventory().getHelmet(),
                p.getInventory().getChestplate(),
                p.getInventory().getLeggings(),
                p.getInventory().getBoots()
        };

        //ステータス
        int block = 30,chance = 3,achance = 3;
        double defense = 0.0;

        for(int i = 0; i < itemStack.length; i++)
        {
            //空気だった場合
            if(itemStack[i].getType() == Material.AIR)
            {
                continue;
            }

            //Loreがあるか
            if(!itemStack[i].getItemMeta().hasLore())
            {
                continue;
            }

            //loreを拡張
            for(String lore : itemStack[i].getItemMeta().getLore())
            {
                //防御率
                if(lore.startsWith("§6§o§6§r§7 防御力§a:"))
                {
                    //リプレースで数字だけに
                    lore = lore.replace("§6§o§6§r§7 防御力§a: §6","");
                    //数字を取得して代入
                    defense = defense + Double.parseDouble(lore);
                }

                //ブロック確率
                if(lore.startsWith("§6§o§6§r§7 ブロックチャンス§a:"))
                {
                    //リプレースで数字だけに
                    lore = lore.replace("§6§o§6§r§7 ブロックチャンス§a: §e","").replace("%","");
                    //数字を取得して代入
                    chance = chance + Integer.parseInt(lore);
                }

                //ブロック割合
                if(lore.startsWith("§6§o§6§r§7 ブロック割合§a:"))
                {
                    //リプレースで数字だけに
                    lore = lore.replace("§6§o§6§r§7 ブロック割合§a: §6","").replace("%","");
                    //数字を取得して代入
                    block = block + Integer.parseInt(lore);
                }

                //回避率
                if(lore.startsWith("§6§o§6§r§7 回避率§a:"))
                {
                    //リプレースで数字だけに
                    lore = lore.replace("§6§o§6§r§7 回避率§a: §d","").replace("%","");
                    //数字を取得して代入
                    achance = achance + Integer.parseInt(lore);
                }
            }
        }

        //回避
        new AvoidanceChanceManager().setAvoidance(e,achance,p);

        //ブロック
        new BlockManager().setBlock(e,block,chance,p);

        //防御力セット
        new DefenseManager().setDefense(e,defense,p);

    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onDamagedMessage(EntityDamageEvent e)
    {
        //プレイヤーか
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        if(e.isCancelled())
        {
            return;
        }

        //プレイヤー
        Player p = (Player) e.getEntity();

        //デバッグログ
        if(TALSITEMS.PlayerDebug.containsKey(p)) {
            if (TALSITEMS.PlayerDebug.get(p)) {
                p.sendMessage("§6§l≪§eデバッグ§6§l≫§f" + e.getDamage() + "ダメージ食らった");
            }
        }
    }
}
