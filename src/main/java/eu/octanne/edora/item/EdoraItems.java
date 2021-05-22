package eu.octanne.edora.item;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.item.mineral.MineralItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EdoraItems {

    /**
     * Item Groups
     */
    @Environment(EnvType.CLIENT)
    public static ItemGroup MINERAL_GROUP;

    /**
     * Mineral Items
     */
    public static MineralItem SULFUR_CRYSTAL;

    @Environment(EnvType.CLIENT)
    public static void registryItemGroups(){
        MINERAL_GROUP = FabricItemGroupBuilder.create(
            new Identifier(EdoraMain.MOD_ID, "mineral"))
            .icon(() -> new ItemStack(SULFUR_CRYSTAL))
            .build();
    }

    public static void registryItems(){
        /**
         * Registry Items
         */
        SULFUR_CRYSTAL = new MineralItem(new Settings().group(MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "sulfur_crystal"), SULFUR_CRYSTAL);
    }
}