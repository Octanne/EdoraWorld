package eu.octanne.edora.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import eu.octanne.edora.block.blocks.EdoraSlabBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.registry.Registry;

@Mixin(Blocks.class)
public class MixinBlocks {
    
    @Redirect( method = "<clinit>()V", at = @At(value = "INVOKE", 
        target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;"))
    private static Block reRegister(String id, Block block) {
        if(block instanceof SlabBlock) {
            return Registry.register(Registry.BLOCK, id, new EdoraSlabBlock(AbstractBlock.Settings.copy(block)));
        }else return Registry.register(Registry.BLOCK, id, block);
    }

}
