package eu.octanne.edora.item;

import java.util.UUID;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.items.BackpackItem;
import eu.octanne.edora.item.items.EcuItem;
import eu.octanne.edora.item.items.MineralItem;
import eu.octanne.edora.item.items.Settings.BackpackSettings;
import eu.octanne.edora.materials.ModularQuantumArmorMaterial;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
    @Environment(EnvType.CLIENT)
    public static ItemGroup BLOCK_GROUP;

    /**************Mineral Items**************/
    public static MineralItem SULFUR_CRYSTAL;
    public static MineralItem SILVER;
    public static MineralItem LEAD;
    public static MineralItem TRANSFORMIUM;
    public static MineralItem LITHIUM;
    public static MineralItem MAGNESIUM;
    public static MineralItem CALCIUM;
    public static MineralItem RADIUM;
    public static MineralItem URANIUM;
    public static MineralItem PLUTONIUM;
    public static MineralItem RHODIUM;
    public static MineralItem TITANE;
    public static MineralItem MANGANESE;
    public static MineralItem NICKEL;
    public static MineralItem PALLADIUM;
    public static MineralItem COPPER;
    public static MineralItem ORICALC;
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
    public static ArmorMaterial MODULAR_QUANTUM_ARMOR_MATERIAL;
    public static Item MODULAR_QUANTUM_HELMET;
    public static Item MODULAR_QUANTUM_CHESTPLATE;
    public static Item MODULAR_QUANTUM_LEGGINGS;
    public static Item MODULAR_QUANTUM_BOOTS;
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
        BLOCK_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "blocks"))
            .icon(() -> new ItemStack(Items.HAY_BLOCK))
            .build();
    }

    public static void registryItems(){
        /**
         * Registry Items
         */

        /**************Mineral Items**************/
        SULFUR_CRYSTAL = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM,  new Identifier(EdoraMain.MOD_ID, "sulfur_crystal"), SULFUR_CRYSTAL);
        SILVER = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"silver"), SILVER);
        LEAD = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"lead"), LEAD);
        TRANSFORMIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"transformium"), TRANSFORMIUM);
        LITHIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"lithium"), LITHIUM);
        MAGNESIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"magnesium"), MAGNESIUM);
        CALCIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"calcium"), CALCIUM);
        RADIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"radium"), RADIUM);
        URANIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"uranium"), URANIUM);
        PLUTONIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"plutonium"), PLUTONIUM);
        RHODIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"rhodium"), RHODIUM);
        TITANE = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"titane"), TITANE);
        MANGANESE = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"manganese"), MANGANESE);
        NICKEL = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"nickel"), NICKEL);
        PALLADIUM = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"palladium"), PALLADIUM);
        COPPER = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"copper"), COPPER);
        ORICALC = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID,"oricalc"), ORICALC);
        
        /**************Faction : Any**************/
        NYLUS_ECU = new EcuItem(new Settings().group(ECONOMY_GROUP).rarity(Rarity.RARE));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "nylus_ecu"), NYLUS_ECU);
        OANNES_ECU = new EcuItem(new Settings().group(ECONOMY_GROUP).rarity(Rarity.UNCOMMON));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "oannes_ecu"), OANNES_ECU);
        POUCH = new BackpackItem(new Settings().group(ECONOMY_GROUP).maxCount(1), new BackpackSettings(9)); 
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "pouch"), POUCH);

        /**************Faction : Kawan**************/
        KAWAN_ITEM = new Item(new Settings());
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kawan_item"), KAWAN_ITEM);
        KAWAN_BACKPACK = new BackpackItem(new Settings().group(KAWAN_GROUP).maxCount(1), new BackpackSettings(27, UUID.fromString("e73edf1a-1242-4455-bcbd-6dff2ce87089"))); //TODO: changer le nom
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kawan_backpack"), KAWAN_BACKPACK);
        
        /**************Faction : Kallana**************/
        KALLANA_ITEM = new Item(new Settings());
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kallana_item"), KALLANA_ITEM);
        QUANTUMPACK = new BackpackItem(new Settings().group(KALLANA_GROUP).maxCount(1), new BackpackSettings(54, UUID.fromString("23c6761c-443f-42c4-a5db-02ad7f85fc4b")));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "quantumpack"), QUANTUMPACK);
        MODULAR_QUANTUM_ARMOR_MATERIAL = new ModularQuantumArmorMaterial();
        MODULAR_QUANTUM_HELMET = new ArmorItem(MODULAR_QUANTUM_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(KALLANA_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "modular_quantum_helmet"), MODULAR_QUANTUM_HELMET);
        MODULAR_QUANTUM_CHESTPLATE = new ArmorItem(MODULAR_QUANTUM_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(KALLANA_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "modular_quantum_chestplate"), MODULAR_QUANTUM_CHESTPLATE);
        MODULAR_QUANTUM_LEGGINGS = new ArmorItem(MODULAR_QUANTUM_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(KALLANA_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "modular_quantum_leggings"), MODULAR_QUANTUM_LEGGINGS);
        MODULAR_QUANTUM_BOOTS = new ArmorItem(MODULAR_QUANTUM_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(KALLANA_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "modular_quantum_boots"), MODULAR_QUANTUM_BOOTS);

        /**************Faction : Othala**************/
        OTHALA_ITEM = new Item(new Settings());
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "othala_item"), OTHALA_ITEM);
        OTHALA_BACKPACK = new BackpackItem(new Settings().group(OTHALA_GROUP).maxCount(1), new BackpackSettings(27, UUID.fromString("c12d2130-52ae-43c5-bd42-642de372e4c8"))); //TODO: changer le nom
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "othala_backpack"), OTHALA_BACKPACK);
    }
}