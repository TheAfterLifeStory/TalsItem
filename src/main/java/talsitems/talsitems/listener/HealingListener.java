package talsitems.talsitems.listener;

import net.minecraft.server.v1_12_R1.PacketPlayOutUpdateTime;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import talsitems.talsitems.manager.HealingManager;

public class HealingListener implements Listener {

    private HealingManager hm;

    public HealingListener() {
        hm = new HealingManager();
    }

    @EventHandler
    public void onHeol(EntityRegainHealthEvent e)
    {
        //プレイヤーか
        if(!(e.getEntity() instanceof Player))
        {
            return;
        }

        //プレイヤー
        Player p = (Player) e.getEntity();
        //アイテム
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        int heal = 1;


        //空気だった場合
        if(itemStack.getType() == Material.AIR)
        {
            return;
        }

        //Loreがあるか
        if(!itemStack.getItemMeta().hasLore())
        {
            return;
        }

        //ヒーリングloreを取得
        for(String lore : itemStack.getItemMeta().getLore())
        {
            if(lore.startsWith("§6§o§6§r§7 ヘルス回復量§a:"))
            {
                lore = lore.replace("§6§o§6§r§7 ヘルス回復量§a: §6+","");
                heal = Integer.parseInt(lore);
                p.sendMessage(lore);
                break;
            }
        }

        //設定
        hm.setHealing(e,heal);
    }
}
