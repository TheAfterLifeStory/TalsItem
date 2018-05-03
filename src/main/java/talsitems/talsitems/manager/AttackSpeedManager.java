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

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);

                        cancel();
                    }
                }.runTaskTimer(plugin, 20, 1);
                return; //終了
            case "早い":

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);

                        cancel();
                    }
                }.runTaskTimer(plugin, 30, 1);
                return;//終了
            case "普通":

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);

                        cancel();
                    }
                }.runTaskTimer(plugin, 40, 1);
                return;//終了
            case "遅い":

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);

                        cancel();
                    }
                }.runTaskTimer(plugin, 50, 1);
                return;//終了
            case "とても遅い":

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);

                        cancel();
                    }
                }.runTaskTimer(plugin, 60, 1);
        }
    }
}
