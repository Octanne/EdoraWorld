package eu.octanne.edora.block;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.block.blocks.EdoraSlabBlock;
import eu.octanne.edora.item.EdoraItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EdoraBlocks {

    public static final Material ORE_MATERIAL = new FabricMaterialBuilder(MaterialColor.STONE).build();

    public static final EdoraSlabBlock DIRT_SLAB = new EdoraSlabBlock(Block.Settings.copy(Blocks.DIRT));

    /**
     * Registry Blocks
     */
    public static void registryBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_slab"), DIRT_SLAB);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_slab"), new BlockItem(DIRT_SLAB, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
    }

    public static void registryOreBlocks() {
        
    }

}
