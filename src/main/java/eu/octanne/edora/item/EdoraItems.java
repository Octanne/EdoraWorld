package eu.octanne.edora.item;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.items.BackpackItem;
import eu.octanne.edora.item.items.EcuItem;
import eu.octanne.edora.item.items.MineralItem;
import eu.octanne.edora.item.items.RingItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Settings;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class EdoraItems {

    /**
     * Item Groups
     */
    @Environment(EnvType.CLIENT)
    public static ItemGroup MINERAL_GROUP;
    @Environment(EnvType.CLIENT)
    public static ItemGroup ECONOMY_GROUP;
    @Environment(EnvType.CLIENT)
    public static ItemGroup KAWAN_GROUP;
    @Environment(EnvType.CLIENT)
    public static ItemGroup KALLANA_GROUP;
    @Environment(EnvType.CLIENT)
    public static ItemGroup OTHALA_GROUP;

    /**
     * Mineral Items
     */
    public static MineralItem SULFUR_CRYSTAL;
    /**************Faction : Any**************/
    public static EcuItem NYLUS_ECU;
    public static EcuItem OANNES_ECU;
    public static BackpackItem POUCH;
    /**************Faction : Kawan**************/
    public static Item KAWAN_ITEM;
    public static BackpackItem KAWAN_BACKPACK;
    /**************Faction : Kallana**************/
    public static Item KALLANA_ITEM;
    public static BackpackItem QUANTUMPACK;
    /**************Faction : Othala**************/
    public static Item OTHALA_ITEM;
    public static BackpackItem OTHALA_BACKPACK;

    @Environment(EnvType.CLIENT)
    public static void registryItemGroups(){
        MINERAL_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "mineral"))
            .icon(() -> new ItemStack(SULFUR_CRYSTAL))
            .build();
        ECONOMY_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "economy"))
            .icon(() -> new ItemStack(NYLUS_ECU))
            .build();
        KAWAN_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "kawan"))
            .icon(() -> new ItemStack(KAWAN_ITEM))
            .build();
        KALLANA_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "kallana"))
            .icon(() -> new ItemStack(KALLANA_ITEM))
            .build();
        OTHALA_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "othala"))
            .icon(() -> new ItemStack(OTHALA_ITEM))
            .build();
    }

    public static void registryItems(){
        /**
         * Registry Items
         */
        SULFUR_CRYSTAL = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "sulfur_crystal"), SULFUR_CRYSTAL);

        /**************Faction : Any**************/
        NYLUS_ECU = new EcuItem(new Settings().group(ECONOMY_GROUP).rarity(Rarity.RARE));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "nylus_ecu"), NYLUS_ECU);
        OANNES_ECU = new EcuItem(new Settings().group(ECONOMY_GROUP).rarity(Rarity.UNCOMMON));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "oannes_ecu"), OANNES_ECU);
        POUCH = new BackpackItem(new Settings().group(MINERAL_GROUP).maxCount(1), 9); //TODO: changer le group
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "pouch"), POUCH);

        /**************Faction : Kawan**************/
        KAWAN_ITEM = new Item(new Settings());
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kawan_item"), KAWAN_ITEM);
        KAWAN_BACKPACK = new BackpackItem(new Settings().group(KAWAN_GROUP).maxCount(1), 27); //TODO: changer le nom
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kawan_backpack"), KAWAN_BACKPACK);

        /**************Faction : Kallana**************/
        KALLANA_ITEM = new Item(new Settings());
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kallana_item"), KALLANA_ITEM);
        QUANTUMPACK = new BackpackItem(new Settings().group(KALLANA_GROUP).maxCount(1), 54);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "quantumpack"), QUANTUMPACK);

        /**************Faction : Othala**************/
        OTHALA_ITEM = new Item(new Settings());
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "othala_item"), OTHALA_ITEM);
        OTHALA_BACKPACK = new BackpackItem(new Settings().group(OTHALA_GROUP).maxCount(1), 27); //TODO: changer le nom
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "othala_backpack"), OTHALA_BACKPACK);
    }
}