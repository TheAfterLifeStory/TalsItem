package talsitems.talsitems.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import talsitems.talsitems.TALSITEMS;
import talsitems.talsitems.manager.AttackChanceManager;
import talsitems.talsitems.manager.CriticalManager;
import talsitems.talsitems.manager.PenetrateManager;

public class DamageEntityListener implements Listener {

    private AttackChanceManager acm;
    private CriticalManager cm;
    private PenetrateManager pm;

    public DamageEntityListener()
    {
        acm = new AttackChanceManager();
        cm = new CriticalManager();
        pm = new PenetrateManager();
    }

    @EventHandler (priority = EventPriority.LOW)//早い
    public void onDamage(EntityDamageByEntityEvent e)
    {

        //プレイヤーか
        if(!(e.getDamager() instanceof Player))
        {
            return;
        }

        //プレイヤー
        Player p = (Player) e.getDamager();
        //アイテム
        ItemStack itemStack = p.getInventory().getItemInMainHand();
        int chance = 3,atttack = 95;
        double damage = 1.5,penetrate = 0.0;
        String type = "素材";

        if(TALSITEMS.ItemCoolDwon.containsKey(p.getUniqueId().toString()+itemStack))
        {
            e.setCancelled(true);
            return;
        }

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

        //loreを拡張
        for(String lore : itemStack.getItemMeta().getLore())
        {
            //type
            if(lore.startsWith("§6§o§6§r§7 アイテムタイプ§a:"))
            {
                type = lore.replace("§6§o§6§r§7 アイテムタイプ§a: §b", "");

                continue;
            }

            //確率
            if(lore.startsWith("§6§o§6§r§7 クリティカル発生率§a:"))
            {
                //リプレースで数字だけに
                lore = lore.replace("§6§o§6§r§7 クリティカル発生率§a: §e","").replace("%","");
                //数字を取得して代入
                chance = Integer.parseInt(lore);
                continue;
            }

            //倍率
            if(lore.startsWith("§6§o§6§r§7 クリティカル倍率§a:"))
            {
                //リプレースで数字だけに
                lore = lore.replace("§6§o§6§r§7 クリティカル倍率§a: §6","").replace("倍","");
                //数字を取得して代入
                damage = Double.parseDouble(lore);
                continue;
            }

            //命中率
            if(lore.startsWith("§6§o§6§r§7 命中率§a:"))
            {
                //リプレースで数字だけに
                lore = lore.replace("§6§o§6§r§7 命中率§a: §e","").replace("%","");
                //数字を取得して代入
                atttack = Integer.parseInt(lore);
                continue;
            }

            //貫通ダメージ
            if(lore.startsWith("§6§o§6§r§7 防具貫通ダメージ§a:"))
            {
                //リプレースで数字だけに
                lore = lore.replace("§6§o§6§r§7 防具貫通ダメージ§a: §c","");
                //数字を取得して代入
                penetrate = Double.parseDouble(lore);
                break;//最後だから
            }
        }

        if(type.equals("魔法の書")
                ||type.equals("魔法の杖"))
        {
            if(!TALSITEMS.ItemDamage.containsKey(p))//無かったらキャンセル
            {
                e.setCancelled(true);
                return;
            }
            else//あったら消す
            {
                TALSITEMS.ItemDamage.remove(p);
            }
        }

        //命中率
        acm.setCritical(e,atttack,p);

        //クリティカル設定
        cm.setCritical(e,chance,damage,p);

        //防具貫通
        pm.setPenetrate(e,penetrate,p);
    }

    @EventHandler (priority = EventPriority.HIGHEST)//すごく遅い
    public void onDamageDebug(EntityDamageByEntityEvent e)
    {
        //プレイヤーか
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        if(e.isCancelled())
        {
            return;
        }

        //プレイヤー
        Player p = (Player) e.getDamager();

        //デバッグログ
        if(TALSITEMS.PlayerDebug.containsKey(p)) {
            if (TALSITEMS.PlayerDebug.get(p)) {
                p.sendMessage("§6§l≪§eデバッグ§6§l≫§f" + e.getDamage() + "ダメージ");
            }
        }
    }

    /*
    @EventHandler
    public void onDamages(PlayerInteractEntityEvent e)
    {
        LivingEntity le = (LivingEntity) e.getRightClicked();
        le.damage(5,e.getPlayer());
    }
    */

}
