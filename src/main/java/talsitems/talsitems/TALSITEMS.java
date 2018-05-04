package talsitems.talsitems;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import talsitems.talsitems.listener.*;
import talsitems.talsitems.manager.ItemManager;

import java.util.HashMap;
import java.util.TreeMap;

public final class TALSITEMS extends JavaPlugin {

    /***************************************************
     *                                                 *
     *   Copyright                                     *
     *           The After Life Story                  *
     *                              Rights Reserved    *
     *                                                 *
     ***************************************************/

    private String prefix = "§e≪§cT§6A§aL§bS §9Items§e≫ ";

    public static HashMap<Integer, ItemStack> itemIdList = new HashMap<>();
    public static HashMap<String, Integer> itemIdNameList = new HashMap<>();
    public static HashMap<String, ItemStack> itemNameList = new HashMap<>();
    public static TreeMap<Integer, String> IdName = new TreeMap<>();
    public static HashMap<Player, Boolean> PlayerDebug = new HashMap<>();
    public static HashMap<String, Player> ItemCoolDwon = new HashMap<>();
    public static HashMap<Player, Boolean> ItemDamage = new HashMap<>();

    public Manager manager;

    @Override
    public void onEnable() {

        //LOADメッセージ開始
        Bukkit.getConsoleSender().sendMessage(prefix+"§aLoading items");

        //アイテム読み込み
        new ItemManager().itemLoad();
        manager = new Manager();

        //リスナー読み込み
        getServer().getPluginManager().registerEvents(new DamageEntityListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        getServer().getPluginManager().registerEvents(new HealingListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new ItemHOldListener(), this);

        //LOADメッセージ終了
        Bukkit.getConsoleSender().sendMessage(prefix+"§aFinish loading items");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
}
