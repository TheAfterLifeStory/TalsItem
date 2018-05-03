package talsitems.talsitems.manager;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import talsitems.talsitems.TALSITEMS;

public class AttackSpeedManager {

    private TALSITEMS plugin = TALSITEMS.getPlugin(TALSITEMS.class);

    public void setCoolDown(PlayerInteractEvent e, ItemStack item, String speed)
    {
        if(TALSITEMS.ItemCoolDwon.containsKey(e.getPlayer().getUniqueId().toString()+item))
        {
            return;
        }

        //e.getPlayer().sendMessage(speed);
        switch (speed)
        {
            case "とても早い":

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 20, 1);

                return; //終了
            case "早い":
            case "少し早い":
            case "普通":
            case "少し遅い":
            case "遅い":
            case "とても遅い":
        }
    }
}
