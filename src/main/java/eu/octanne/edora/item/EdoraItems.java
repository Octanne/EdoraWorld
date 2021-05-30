package eu.octanne.edora.item;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.items.EcuItem;
import eu.octanne.edora.item.items.MineralItem;
import eu.octanne.edora.item.items.RingItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Settings;
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
    /**************Faction : Othala**************/
    public static RingItem X_ILLUMINATION; // TODO: le nom n'est pas définif

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
        KAWAN_GROUP = FabricItemGroupBuilder.create( // TODO: icon
            new Identifier(EdoraMain.MOD_ID, "kawan"))
            .build();
        KALLANA_GROUP = FabricItemGroupBuilder.create( // TODO: icon
            new Identifier(EdoraMain.MOD_ID, "kallana"))
            .build();
        OTHALA_GROUP = FabricItemGroupBuilder.create( // TODO: icon
            new Identifier(EdoraMain.MOD_ID, "othala"))
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

        /**************Faction : Othala**************/

    }
}