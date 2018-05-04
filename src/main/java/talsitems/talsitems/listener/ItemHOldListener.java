package talsitems.talsitems.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemHOldListener implements Listener {

    @EventHandler
    public void onHold(PlayerItemHeldEvent e)
    {
        Player p = e.getPlayer();
        ItemStack itemStack = e.getPlayer().getInventory().getItem(e.getNewSlot());

        String speed = "a";

        //空気だった場合
        if(itemStack.getType() == Material.AIR)
        {
            if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
                p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            }
            return;
        }

        //Loreがあるか
        if(!itemStack.getItemMeta().hasLore())
        {
            if(p.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
                p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            }
            return;
        }

        //loreを拡張
        for(String lore : itemStack.getItemMeta().getLore())
        {
            //type
            if(lore.startsWith("§6§o§6§r§7 攻撃スピード§a:"))
            {
                speed = lore.replace("§6§o§6§r§7 攻撃スピード§a: §3", "");
                break;
            }
        }
        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);

        switch (speed)
        {
            case "とても早い":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,1,false,false));
                break;
            case "早い":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,2,false,false));
                break;
            case "少し早い":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,3,false,false));
                break;
            case "普通":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,4,false,false));
                break;
            case "少し遅い":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,6,false,false));
                break;
            case "遅い":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,8,false,false));
                break;
            case "とても遅い":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,99999 ,8,false,false));
        }
    }
}
