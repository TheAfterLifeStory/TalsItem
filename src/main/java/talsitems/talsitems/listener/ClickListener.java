package talsitems.talsitems.listener;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import talsitems.talsitems.TALSITEMS;
import talsitems.talsitems.manager.AttackSpeedManager;
import talsitems.talsitems.manager.ClassLevelManager;

public class ClickListener implements Listener {

    private AttackSpeedManager asm;
    private ClassLevelManager clm;

    public ClickListener()
    {
        asm = new AttackSpeedManager();
        clm = new ClassLevelManager();
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {

        Player p = e.getPlayer();
        ItemStack itemStack = p.getInventory().getItemInMainHand();

        if(TALSITEMS.ItemCoolDwon.containsKey(p.getUniqueId().toString()+itemStack))
        {
            return;
        }

        //クリックタイプ
        if(e.getAction() != Action.LEFT_CLICK_AIR)
        {
            return;
        }

        //空気か
        if(itemStack.getType() == Material.AIR)
        {
            return;
        }

        //Loreが存在するか
        if(!itemStack.getItemMeta().hasLore())
        {
            return;
        }

        //Ｔｙｐｅ作成
        String type = "素材",speed ="",classes = "";
        //damageを作成
        double damage = 1;
        int level = 0;

        //Ｌｏｒｅにする
        for(String lore : itemStack.getItemMeta().getLore())
        {
            e.setCancelled(true);

            //type
            if(lore.startsWith("§6§o§6§r§7 アイテムタイプ§a:"))
            {
                type = lore.replace("§6§o§6§r§7 アイテムタイプ§a: §b", "");

                continue;
            }

            //speed
            if(lore.startsWith("§6§o§6§r§7 攻撃スピード§a:"))
            {
                speed = lore.replace("§6§o§6§r§7 攻撃スピード§a: §3", "");

                continue;
            }

            //ダメージ
            if(lore.startsWith("§6§o§6§r§7 ダメージ§a:"))
            {
                //リプレース
                lore = lore.replace("§6§o§6§r§7 ダメージ§a: §c", "");
                //double
                damage = Double.parseDouble(lore);
                continue;
            }

            //クラス
            if(lore.startsWith("§6§o§6§r§7 クラス§a: "))
            {
                lore = lore.replace("§6§o§6§r§7 クラス§a: ","");

                classes = lore;
                break;
            }

            //レベル
            if(lore.startsWith("§6§o§6§r§7 レベル§a: "))
            {
                lore = lore.replace("§6§o§6§r§7 レベル§a: ","");

                level = Integer.parseInt(lore);
                break;
            }
        }

        clm.setConditions(p,level,classes);

        if(TALSITEMS.ClancelWand.get(p) != null)
        {
            TALSITEMS.ClancelWand.remove(p);
            e.setCancelled(true);
            return;
        }

        //魔法の杖
        if(type.equals("魔法の杖"))
        {
            e.setCancelled(true);

            //ロケーション取得
            Location loc = p.getLocation();

            //ダメージを与える１
            for(Entity entity : loc.getWorld().getNearbyEntities(loc,0.28,1.5,0.28))
            {
                //entityを変える
                if(!(entity instanceof LivingEntity))
                {
                    continue;
                }

                if(entity == p)
                {
                    continue;
                }

                LivingEntity le = (LivingEntity) entity;
                le.damage(damage,p);
                TALSITEMS.ItemDamage.put(p,true);
            }
            loc = loc.add(loc.getDirection().getX(),loc.getDirection().getY()+1.2,loc.getDirection().getZ());

            //ダメージを与える２
            for(Entity entity : loc.getWorld().getNearbyEntities(loc,0.25,1.5,0.25))
            {
                //entityを変える
                if(!(entity instanceof LivingEntity))
                {
                    continue;
                }

                if(entity == p)
                {
                    continue;
                }

                LivingEntity le = (LivingEntity) entity;
                le.damage(damage,p);
                TALSITEMS.ItemDamage.put(p,true);
            }

            //パーティクル
            p.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 0, 1, 0, 0, 0);
            p.playSound(loc,Sound.BLOCK_ENCHANTMENT_TABLE_USE,8,0.8f);
            p.playSound(loc,Sound.ENTITY_ILLUSION_ILLAGER_MIRROR_MOVE,1.5f,2.5f);

            for(int i = 0; i < 20; i++ )
            {
                //ロケーションを追加
                loc = loc.add(loc.getDirection().getX(),loc.getDirection().getY(),loc.getDirection().getZ());

                //ブロックがあるか
                if(loc.getBlock().getType() != Material.AIR)
                {
                    p.getWorld().spawnParticle(Particle.CRIT_MAGIC, loc, 0, 5, 0, 0, 2);
                    break;
                }

                //パーティクル
                p.getWorld().spawnParticle(Particle.CRIT_MAGIC, loc, 0, 0.5f, 0, 0, 0);
                p.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 0, 3, 0, 0, 0);
                //ダメージを与える
                //for文
                for(Entity entity : loc.getWorld().getNearbyEntities(loc,0.25,1.5,0.25))
                {
                    //entityを変える
                    if(!(entity instanceof LivingEntity))
                    {
                        continue;
                    }

                    if(entity == p)
                    {
                        continue;
                    }

                    LivingEntity le = (LivingEntity) entity;
                    le.damage(damage,p);
                    TALSITEMS.ItemDamage.put(p,true);
                }
            }
            TALSITEMS.ClancelDamage.remove(p);
        }

        //魔法の本
        if(type.equals("魔法の書"))
        {
            e.setCancelled(true);

            Location loc = p.getLocation();
            loc = loc.add(+loc.getDirection().getX(),0,+loc.getDirection().getZ())
                    .add(+loc.getDirection().getX(),0,+loc.getDirection().getZ())
                    .add(+loc.getDirection().getX(),0,+loc.getDirection().getZ());

            p.playSound(loc,Sound.ENTITY_EVOCATION_ILLAGER_CAST_SPELL,1,4);
            p.playSound(loc,Sound.ENTITY_PLAYER_BREATH,0.f,0.5f);
            p.playSound(loc,Sound.ENTITY_EVOCATION_ILLAGER_PREPARE_SUMMON,2,0.8f);
            particle(loc, p);
            loc.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE,loc.add(0,0.1,0),360,0.8,0,0.8,0);

            for(Entity entity : loc.getWorld().getNearbyEntities(loc,2,1,2))
            {
                //entityを変える
                if(!(entity instanceof LivingEntity))
                {
                    continue;
                }

                if(entity == p)
                {
                    continue;
                }

                LivingEntity le = (LivingEntity) entity;
                le.damage(damage,p);
                TALSITEMS.ItemDamage.put(p,true);
            }
        }

        asm.setCoolDown(e,itemStack,speed);//クールダウン
    }

    private void particle(Location loc, Player player) {
        for (double t = 0; t < 50; t += 0.5) {
            float x = (float) 2 * (float) Math.sin(t);
            float z = (float) 2 * (float) Math.cos(t);
            Location l = new Location(loc.getWorld(),loc.getX()+x,loc.getY(),loc.getZ()+z);

            float r = (float) 185/255;
            float g = (float) 152/255;
            float b = (float) 255/255;

            player.getWorld().spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, r, g, b, 1, 0, 30);
        }
    }
}
