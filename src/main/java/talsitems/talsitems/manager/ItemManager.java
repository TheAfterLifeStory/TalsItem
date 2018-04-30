package talsitems.talsitems.manager;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import talsitems.talsitems.TALSITEMS;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private TALSITEMS plugin = TALSITEMS.getPlugin(TALSITEMS.class);
    private String itemd= "§a§a§a§0§r";//対策

    public void itemLoad()
    {
        TALSITEMS.itemIdList.clear();
        TALSITEMS.itemNameList.clear();
        TALSITEMS.IdName.clear();
        TALSITEMS.itemIdNameList.clear();

        //ファイル取得
        File itemFile = new File((Bukkit.getServer().getPluginManager().getPlugin(plugin.getName()).getDataFolder()), File.separator +"Items");

        //ファイル作成2
        if(!itemFile.exists())
        {
            itemFile.mkdirs();
        }

        if(!new File(itemFile,"/items.yml").exists())
        {
            try {
                FileUtils.copyToFile(plugin.getResource("items.yml"),new File(itemFile,"/items.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //itemファイルの中のファイルを取得
        File[] ymls = itemFile.listFiles();

        //ファイルの中身がからじゃないか検知してリターン
        if(ymls == null) return;

        //アイテムyml取得
        for (File yml : ymls) {
            YamlConfiguration files = YamlConfiguration.loadConfiguration(yml);

            //題名取得
            for (String name : files.getKeys(false)) {
                files.get(name);
                setItemStackList(files,name);//アイテムを登録
            }
        }
    }

    private void setItemStackList(YamlConfiguration config, String name)
    {

        //値を制作
        boolean unbreakable;
        int id, level,tid,rank,data;
        String itemname,type,classes,Tid;
        double damage;
        List<String> list = new ArrayList<>();
        List<String> stats = new ArrayList<>();

        //デフォルトの値を設定
        id =1; //アイテムＩＤ
        tid=0;//TALS_ID
        Tid="0000";//Ｉｄ設定
        data = 0;//メタデータ
        unbreakable = false;//耐久
        itemname="";//アイテム名
        type="§b素材";//アイテムタイプ
        classes = null;//使用可能クラス
        level = 1;//使用可能レベル
        damage = 1.0;//ダメージでほぉると
        rank = 1;

        //値を入力

        //アイテムID
        if(config.get(name+".Item.Id") !=null) {
            id = config.getInt(name + ".Item.Id");
        }

        //メタデータ
        if(config.get(name+".Item.Data") !=null) {
            data= config.getInt(name + ".Item.Data");
        }

        //ＴＩＤ
        if(config.get(name+".Item.Tid") !=null) {
            tid = config.getInt(name + ".Item.Tid");
        }

        //耐久
        if(config.get(name+".Item.Unbreakable") !=null) {
            unbreakable = config.getBoolean(name + ".Item.Unbreakable");
        }

        //名前
        if(config.get(name+".Item.Name") !=null) {
            itemname = config.getString(name + ".Item.Name");
        }

        //タイプ
        if(config.get(name+".Data.Stats.Type") !=null)
        {
            type = getItemType(config.getString(name + ".Data.Stats.Type"));
        }

        //説明欄
        if(config.get(name+".Data.Description") !=null)
        {
            list = config.getStringList(name + ".Data.Description");
        }

        //クラス
        if(config.get(name+".Data.Conditions.Class") !=null)
        {
            classes = getClassName(config.getString(name + ".Data.Conditions.Class"));
        }

        //レベル
        if(config.get(name+".Data.Conditions.Level") !=null)
        {
            level = config.getInt(name + ".Data.Conditions.Level");
        }


        Tid=tid+"";//元の値設定
        //TIDの設定
        if(Tid.length() <4)
        {

            for(int i = 0; Tid.length() < 4; i++)//四桁になるまで繰り返す
            {
                Tid="0"+Tid;//0を追加する
            }

        }

        //ランクセット
        if(config.get(name+".Data.Stats.Rank") !=null)
        {
            rank = config.getInt(name + ".Data.Stats.Rank");
        }

        //ステータス作成

        //ダメージ
        if(config.get(name+".Data.Stats.Damage") !=null)
        {
            stats.add("§6§o§6§r§7 ダメージ§a: §c"+config.getDouble(name + ".Data.Stats.Damage"));
            damage = config.getDouble(name + ".Data.Stats.Damage");
        }

        //クリティカル倍率
        if(config.get(name+".Data.Stats.CriticalDamage") !=null)
        {
            stats.add("§6§o§6§r§7 クリティカル倍率§a: §6"+config.getDouble(name + ".Data.Stats.CriticalDamage")+"倍");
        }

        //クリティカル発生率
        if(config.get(name+".Data.Stats.CriticalChance") !=null)
        {
            stats.add("§6§o§6§r§7 クリティカル発生率§a: §e"+config.getInt(name + ".Data.Stats.CriticalChance")+"%");
        }

        //命中率
        if(config.get(name+".Data.Stats.AttackChance") !=null)
        {
            stats.add("§6§o§6§r§7 命中率§a: §e"+config.getInt(name + ".Data.Stats.AttackChance")+"%");
        }

        //防具貫通ダメージ
        if(config.get(name+".Data.Stats.Penetrate") !=null)
        {
            stats.add("§6§o§6§r§7 防具貫通ダメージ§a: §c"+config.getDouble(name + ".Data.Stats.Penetrate"));
        }

        //マナドレイン（あとで）

        //ヘルスドレイン（あとで）

        //防御力
        if(config.get(name+".Data.Stats.Defense") !=null)
        {
            stats.add("§6§o§6§r§7 防御力§a: §6"+config.getDouble(name + ".Data.Stats.Defense"));
        }

        //ブロック割合
        if(config.get(name+".Data.Stats.BlockPercent") !=null)
        {
            stats.add("§6§o§6§r§7 ブロック割合§a: §6"+config.getInt(name + ".Data.Stats.BlockPercent")+"%");
        }

        //ブロックチャンス
        if(config.get(name+".Data.Stats.BlockChance") !=null)
        {
            stats.add("§6§o§6§r§7 ブロックチャンス§a: §e"+config.getInt(name + ".Data.Stats.BlockChance")+"%");
        }

        //回避率
        if(config.get(name+".Data.Stats.AvoidanceChance") !=null)
        {
            stats.add("§6§o§6§r§7 回避率§a: §d"+config.getInt(name + ".Data.Stats.AvoidanceChance")+"%");
        }

        //ヘルスの回復量増加
        if(config.get(name+".Data.Stats.HealthAmount") !=null)
        {
            stats.add("§6§o§6§r§7 ヘルス回復量§a: §6+"+config.getDouble(name + ".Data.Stats.AvoidanceChance"));
        }

        //マナの回復量増加
        if(config.get(name+".Data.Stats.ManaAmount") !=null)
        {
            stats.add("§6§o§6§r§7 マナ回復量§a: §6+"+config.getInt(name + ".Data.Stats.ManaAmount"));
        }

        //ヘルスの回復速度増加
        if(config.get(name+".Data.Stats.HealthRegeneration") !=null)
        {
            stats.add("§6§o§6§r§7 ヘルス回復速度§a: §e+"+config.getInt(name + ".Data.Stats.HealthRegeneration")+"%");
        }

        //マナの回復速度増加
        if(config.get(name+".Data.Stats.ManaRegeneration") !=null)
        {
            stats.add("§6§o§6§r§7 マナ回復速度§a: §e+"+config.getInt(name + ".Data.Stats.ManaRegeneration")+"%");
        }

        //ヘルス増加
        if(config.get(name+".Data.Stats.AddHealth") !=null)
        {
            stats.add("§6§o§6§r§7 ヘルス§a: §c+"+config.getDouble(name + ".Data.Stats.ManaRegeneration"));
        }

        //マナ増加
        if(config.get(name+".Data.Stats.AddMana") !=null)
        {
            stats.add("§6§o§6§r§7 マナ§a: §b+"+config.getInt(name + ".Data.Stats.AddMana"));
        }

        //歩くスピード
        if(config.get(name+".Data.Stats.WalkSpeed") !=null)
        {
            stats.add("§6§o§6§r§7 歩くスピード§a: §b+"+config.getInt(name + ".Data.Stats.WalkSpeed"));
        }

        //魔法ダメージ
        if(config.get(name+".Data.Stats.MagicDamage") !=null)
        {
            stats.add("§6§o§6§r§7 魔法ダメージ増加§a: §d"+config.getDouble(name + ".Data.Stats.MagicDamage"));
        }

        //銃弾のスピード
        if(config.get(name+".Data.Stats.AmmoSpeed") !=null)
        {
            stats.add("§6§o§6§r§7 弾速§a: §b"+config.getInt(name + ".Data.Stats.AmmoSpeed"));
        }

        //リロード時間
        if(config.get(name+".Data.Stats.ReloadTime") !=null)
        {
            stats.add("§6§o§6§r§7 リロード時間§a: §e"+config.getInt(name + ".Data.Stats.ReloadTime"));
        }

        //打つまでの時間
        if(config.get(name+".Data.Stats.ShootingSpeed") !=null)
        {
            stats.add("§6§o§6§r§7 クールダウン§a: §6"+config.getDouble(name + ".Data.Stats.ShootingSpeed"));
        }

        //銃弾の最大値
        if(config.get(name+".Data.Stats.MaxAmmo") !=null)
        {
            stats.add("§6§o§6§r§7 銃弾の最大値§a: §e"+config.getInt(name + ".Data.Stats.MaxAmmo"));
        }

        //銃弾のタイプ (あとで)

        //銃のタイプ (あとで)

        //撃つ方法 (あとで)

        //ブレ
        if(config.get(name+".Data.Stats.Blur") !=null)
        {
            stats.add("§6§o§6§r§7 ブレ§a: §e"+config.getInt(name + ".Data.Stats.Blur"));
        }

        //ヘッドショットの倍率
        if(config.get(name+".Data.Stats.HedShotDamage") !=null)
        {
            stats.add("§6§o§6§r§7 ヘッドショット倍率§a: §6"+config.getDouble(name + ".Data.Stats.HedShotDamage")+"倍");
        }

        //ポーションエフェクト
        if(config.get(name+".Data.Stats.PotionEffect") !=null)
        {
            stats.add("§6§o§6§r§7 ポーション効果§a: §f"+config.getString(name + ".Data.Stats.PotionEffect"));
        }

        //アイテムスキル
        if(config.get(name+".Data.Stats.ItemSkill") !=null)
        {
            stats.add("§6§o§6§r§7 アイテムスキル§a: §f"+config.getString(name + ".Data.Stats.ItemSkill"));
        }

        //お金
        if(config.get(name+".Data.Stats.Money") !=null)
        {
            stats.add("§6§o§6§r§7 お金§a: §e"+config.getDouble(name + ".Data.Stats.Money"));
        }

        //耐久力
        if(config.get(name+".Data.Stats.Durability") !=null)
        {
            stats.add("§6§o§6§r§7 耐久力§a: §c"+config.getDouble(name + ".Data.Stats.Durability")+"§f/§c"+config.getDouble(name + ".Data.Stats.Durability"));
        }

        //食べ物回復量
        if(config.get(name+".Data.Stats.FoodLevel") !=null)
        {
            stats.add("§6§o§6§r§7 回復量§a: §6"+config.getDouble(name + ".Data.Stats.Durability")+"§f/§c"+config.getDouble(name + ".Data.Stats.FoodLevel"));
        }

        //アイテムを制作
        ItemStack item = new ItemStack(Material.AIR, 1,(short) data);

        //アイテムに値を設定
        item.setTypeId(id);//Ｔｙｐｅ

        ItemMeta itemmeta = item.getItemMeta();//アイテムmeta作成

        itemmeta.setUnbreakable(unbreakable);//耐久
        itemmeta.setDisplayName(itemd+itemname+" §8(T:"+Tid+")");//名前設定

        //フラグの追加
        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemmeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemmeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        //Loreのリスト定義
        ArrayList<String> lore = new ArrayList<>();

        lore.add("§6§l《§fステータス§6§l》");
        lore.add("§6§o§6§r§7 アイテムタイプ§a: "+type);

        //ランク
        String steer = "§f✩§f✩§f✩§f✩§f✩§f✩";
        String substeer = "§e✯§e✯§e✯§e✯§e✯§e✯";
        steer = steer.replaceFirst(steer.substring(0,rank*3),substeer.substring(0,rank*3));

        lore.add("§6§o§6§r§7"+" ランク§a: "+steer);

        //ステータス

        lore.addAll(stats);

        //使用条件
        if(config.get(name+".Data.Conditions") !=null)
        {
            lore.add("§6§l《§f使用条件§6§l》");

            //クラス
            if(config.get(name+".Data.Conditions.Class") != null)
            {
                lore.add("§6§o§6§r§7 クラス§a: "+ classes);
            }

            //レベル
            if(config.get(name+".Data.Conditions.Level") != null)
            {
                lore.add("§6§o§6§r§7 レベル§a: "+level);
            }

        }

        //説明欄
        if(config.get(name+".Data.Description") !=null)
        {
            lore.add("§6§l《§f説明§6§l》");

            //拡張
            for(String lores : list)
            {
                lore.add(" §7"+lores);
            }
        }

        //Loreのリストを設定
        itemmeta.setLore(lore);

        item.setItemMeta(itemmeta);//アイテムmeta置き換え

        //ステータスの設定
        item = setDamage(item,damage,type);

        TALSITEMS.itemIdList.put(tid, item);
        TALSITEMS.itemNameList.put(name, item);
        TALSITEMS.IdName.put(tid,name);
        TALSITEMS.itemIdNameList.put(item.getItemMeta().getDisplayName(),tid);
    }

    public int getItemId(ItemStack item)
    {
        //アイテムに名前があるかどうか
        if(!item.getItemMeta().hasDisplayName())
        {
            return 0;
        }

        //名前取得
        String name = item.getItemMeta().getDisplayName();

        //IDに変更
        String id = name.substring(name.length()-6,name.length()-1);

        //０を取り除く
        for(int i=0; !id.startsWith("0"); i++)
        {
            id =id.replaceFirst("0","");
        }
        if(!isInt(id))
        {
            return 0;
        }

        return Integer.parseInt(id);
    }

    private boolean isInt(String str)
    {
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    private String getClassName(String classes)
    {
        switch (classes){
            case "SOLDIER":
                return "§7§l⚔§f§lソルジャー";//ソルジャー
            case "WIZARD":
                return "§4§l۞§c§lウィザード";//ウィザード
            case "GUARDIAN":
                return "§1§l◆§3§lガーディアン";//ガーディアン
            case "THIEF":
                return "§2§l⚷§a§lシーフ";//チーフ
            case "HUNTER":
                return "§6✛§e§lハンター";//ハンター
            case "ASSASSIN":
                return "§8§l§m━]§7§l§m━§f§l§m━ §4§lアサシン";//アサシン
            case "NECROMANCER":
                return "§5§l☪§d§lネクロマンサー";//ネクロマンサー
            case "PRIEST":
                return "§3§l✞§b§lプリースト";//プリースト
        }

        return null;
    }

    private String getItemType(String type)
    {
        switch (type)
        {
            case "SWORD":
                return "§b剣";//剣
            case "BOW":
                return "§b弓";//弓
            case "AXE":
                return "§b斧";//斧
            case "MAGIC_WAND":
                return "§b魔法の杖";//魔法の杖
            case "KNIFE":
                return "§bナイフ";//ナイフ
            case "SCYTHE":
                return "§b大鎌";//大鎌
            case "MAGIC_BOOK":
                return "§b魔法の書";//魔法の書
            case "GUN":
                return "§b銃";//随意
            case "HELMET":
                return "§bヘルメット";//ヘルメット
            case "CHESTPLATE":
                return "§bチェストプレート";//チェストプレート
            case "LEGGINGS":
                return "§bレギンス";//レギンス
            case "BOOT":
                return "§bブーツ";//ブーツ
            case "POTION":
                return "§bポーション";//ポーション
            case "MAGIC_ITEM":
                return "§b魔法のアイテム";//魔法のアイテム
            case "QUEST_ITEM":
                return "§bクエストアイテム";//クエストアイテム
            case "EVENT_ITEM":
                return "§bイベントアイテム";//イベントアイテム
            case "STORY_ITEM":
                return "§bストーリーアイテム";//ストーリーアイテム
            case "MATERIAL":
                return "§b素材";//素材
            case "MONEY":
                return "§bお金";//お金
            case "ORE":
                return "§b鉱石";//鉱石
            case "FOOD":
                return "§b食べ物";//食べ物
        }
        return null;
    }

    private ItemStack setDamage(ItemStack item,double damage,String type)
    {
        if(item.getType() == Material.BOW
                || type.equals("§b魔法の杖")
                || type.equals("§b魔法の書")
                || type.equals("§b銃")
                )
        {
            return item;
        }

        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        NBTTagList modifiers = new NBTTagList();
        NBTTagCompound damages = new NBTTagCompound();
        damages.set("AttributeName", new NBTTagString("generic.attackDamage"));
        damages.set("Name", new NBTTagString("generic.attackDamage"));
        damages.set("Amount", new NBTTagInt((int) damage));
        damages.set("Operation", new NBTTagInt(0));
        damages.set("UUIDLeast", new NBTTagInt(894654));
        damages.set("UUIDMost", new NBTTagInt(2872));
        damages.set("Slot", new NBTTagString("mainhand"));
        modifiers.add(damages);
        assert compound != null;
        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsStack);

        return item;
    }
}
