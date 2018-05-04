package talsitems.talsitems.manager;

import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetCooldown;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
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

                Item itemd = Item.getById(item.getTypeId());
                PacketPlayOutSetCooldown packet = new PacketPlayOutSetCooldown(itemd,10);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 10, 1);

                return; //終了

            case "早い":

                itemd = Item.getById(item.getTypeId());
                packet = new PacketPlayOutSetCooldown(itemd,15);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 15, 1);

                return; //終了

            case "少し早い":

                itemd = Item.getById(item.getTypeId());
                packet = new PacketPlayOutSetCooldown(itemd,20);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 20, 1);

                return; //終了

            case "普通":

                itemd = Item.getById(item.getTypeId());
                packet = new PacketPlayOutSetCooldown(itemd,25);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 25, 1);

                return; //終了

            case "少し遅い":

                itemd = Item.getById(item.getTypeId());
                packet = new PacketPlayOutSetCooldown(itemd,30);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 30, 1);

                return; //終了

            case "遅い":

                itemd = Item.getById(item.getTypeId());
                packet = new PacketPlayOutSetCooldown(itemd,35);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 35, 1);

                return; //終了

            case "とても遅い":

                itemd = Item.getById(item.getTypeId());
                packet = new PacketPlayOutSetCooldown(itemd,40);
                ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);

                TALSITEMS.ItemCoolDwon.put(e.getPlayer().getUniqueId().toString()+item,e.getPlayer());//追加
                new BukkitRunnable(){//runで回す
                    @Override
                    public void run() {

                        TALSITEMS.ItemCoolDwon.remove(e.getPlayer().getUniqueId().toString()+item);//クールダウン後に抜く

                        cancel();
                    }
                }.runTaskTimer(plugin, 40, 1);

        }
    }
}
