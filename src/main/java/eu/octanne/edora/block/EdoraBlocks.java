package eu.octanne.edora.block;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.block.blocks.EdoraSlabBlock;
import eu.octanne.edora.item.EdoraItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EdoraBlocks {

    public static final EdoraSlabBlock EDORA_SLAB = new EdoraSlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE_SLAB));

    /**
     * Registry Blocks
     */
    public static void registryBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "edora_slab"), EDORA_SLAB);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "edora_slab"), new BlockItem(EDORA_SLAB, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
    }

}
