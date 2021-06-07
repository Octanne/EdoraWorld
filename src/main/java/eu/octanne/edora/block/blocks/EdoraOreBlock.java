package eu.octanne.edora.block.blocks;

import net.minecraft.block.OreBlock;

public class EdoraOreBlock extends OreBlock {

    public EdoraOreBlock(Settings settings) {
        super(settings.requiresTool());
    }
    
}
