package eu.octanne.edora.item;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.items.EcuItem;
import eu.octanne.edora.item.items.MineralItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
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

    /**
     * Mineral Items
     */
    public static final MineralItem SULFUR_CRYSTAL = new MineralItem(new Settings().group(MINERAL_GROUP));;
    /**************Faction : Any**************/
    public static final EcuItem NYLUS_ECU = new EcuItem(new Settings().group(ECONOMY_GROUP).rarity(Rarity.RARE));
    public static final EcuItem OANNES_ECU = new EcuItem(new Settings().group(ECONOMY_GROUP).rarity(Rarity.UNCOMMON));
    /**************Faction : Kawan**************/
    public static final Item KAWAN_ITEM = new Item(new Settings());
    /**************Faction : Kallana**************/
    public static final Item KALLANA_ITEM = new Item(new Settings());
    /**************Faction : Othala**************/
    public static final Item OTHALA_ITEM = new Item(new Settings());
    //public static final RingItem X_ILLUMINATION; // TODO: le nom n'est pas définif

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
            new Identifier(EdoraMain.MOD_ID, "othala"))
            .icon(() -> new ItemStack(Items.HAY_BLOCK))
            .build();
    }

    public static void registryItems(){
        /**
         * Registry Items
         */

        /**************Faction : Any**************/
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "nylus_ecu"), NYLUS_ECU);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "oannes_ecu"), OANNES_ECU);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "sulfur_crystal"), SULFUR_CRYSTAL);
        /**************Faction : Kawan**************/
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kawan_item"), KAWAN_ITEM);
        /**************Faction : Kallana**************/
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "kallana_item"), KALLANA_ITEM);
        /**************Faction : Othala**************/
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "othala_item"), OTHALA_ITEM);
    }
}