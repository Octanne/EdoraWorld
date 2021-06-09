package eu.octanne.edora.block;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.block.blocks.EdoraOreBlock;
import eu.octanne.edora.block.blocks.EdoraSlabBlock;
import eu.octanne.edora.item.EdoraItems;
import eu.octanne.edora.item.items.minerals.RawMineralItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EdoraBlocks {

    public static final Material ORE_MATERIAL = new FabricMaterialBuilder(MapColor.STONE_GRAY).build();

    public static final EdoraSlabBlock DIRT_SLAB = new EdoraSlabBlock(Block.Settings.copy(Blocks.DIRT));

    /** Ore & Raw Mineral */
    public static RawMineralItem RAW_BAUXYTE;
    public static EdoraOreBlock ORE_BAUXYTE;
    public static RawMineralItem RAW_GALENE;
    public static EdoraOreBlock ORE_GALENE;
    public static RawMineralItem RAW_MALACHITE;
    public static EdoraOreBlock ORE_MALACHITE;
    public static RawMineralItem RAW_PSILOMELANE;
    public static EdoraOreBlock ORE_PSILOMELANE;
    public static RawMineralItem RAW_MANGANESE;
    public static EdoraOreBlock ORE_MANGANESE;
    public static RawMineralItem RAW_SILVER;
    public static EdoraOreBlock ORE_SILVER;
    public static RawMineralItem RAW_NICKEL_SULFUR;
    public static EdoraOreBlock ORE_NICKEL_SULFUR;
    public static RawMineralItem RAW_URANINYTE;
    public static EdoraOreBlock ORE_URANINYTE;
    public static RawMineralItem RAW_TUNGSTEN;
    public static EdoraOreBlock ORE_TUNGSTEN;
    public static RawMineralItem RAW_TRANSFORMIUM;
    public static EdoraOreBlock ORE_TRANSFORMIUM;
    public static RawMineralItem RAW_ORICALC;
    public static EdoraOreBlock ORE_ORICALC;
    public static RawMineralItem RAW_TRINIUM;
    public static EdoraOreBlock ORE_TRINIUM;
    public static RawMineralItem RAW_TIN;
    public static EdoraOreBlock ORE_TIN;

    /**
     * Registry Blocks
     */
    public static void registryBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_slab"), DIRT_SLAB);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_slab"), new BlockItem(DIRT_SLAB, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
    }

    public static void registryOreBlocks() {
        RAW_BAUXYTE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_bauxyte"), RAW_BAUXYTE);
        ORE_BAUXYTE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_bauxyte"), ORE_BAUXYTE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_bauxyte"), new BlockItem(ORE_BAUXYTE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_GALENE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_galene"), RAW_GALENE);
        ORE_GALENE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_galene"), ORE_GALENE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_galene"), new BlockItem(ORE_GALENE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_MALACHITE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_malachite"), RAW_MALACHITE);
        ORE_MALACHITE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_malachite"), ORE_MALACHITE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_malachite"), new BlockItem(ORE_MALACHITE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_PSILOMELANE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_psilomelane"), RAW_PSILOMELANE);
        ORE_PSILOMELANE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_psilomelane"), ORE_PSILOMELANE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_psilomelane"), new BlockItem(ORE_PSILOMELANE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_MANGANESE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_manganese"), RAW_MANGANESE);
        ORE_MANGANESE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_manganese"), ORE_MANGANESE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_manganese"), new BlockItem(ORE_MANGANESE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_SILVER = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_silver"), RAW_SILVER);
        ORE_SILVER = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_silver"), ORE_SILVER);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_silver"), new BlockItem(ORE_SILVER, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_NICKEL_SULFUR = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_nickel_sulfur"), RAW_NICKEL_SULFUR);
        ORE_NICKEL_SULFUR = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_nickel_sulfur"), ORE_NICKEL_SULFUR);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_nickel_sulfur"), new BlockItem(ORE_NICKEL_SULFUR, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_URANINYTE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_uraninyte"), RAW_URANINYTE);
        ORE_URANINYTE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.0F,4.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_uraninyte"), ORE_URANINYTE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_uraninyte"), new BlockItem(ORE_URANINYTE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TUNGSTEN = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_tungsten"), RAW_TUNGSTEN);
        ORE_TUNGSTEN = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_tungsten"), ORE_TUNGSTEN);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_tungsten"), new BlockItem(ORE_TUNGSTEN, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TRANSFORMIUM = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_transformium"), RAW_TRANSFORMIUM);
        ORE_TRANSFORMIUM = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.5F,4.5F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_transformium"), ORE_TRANSFORMIUM);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_transformium"), new BlockItem(ORE_TRANSFORMIUM, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_ORICALC = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_oricalc"), RAW_ORICALC);
        ORE_ORICALC = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.5F,4.5F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_oricalc"), ORE_ORICALC);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_oricalc"), new BlockItem(ORE_ORICALC, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TRINIUM = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_trinium"), RAW_TRINIUM);
        ORE_TRINIUM = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_trinium"), ORE_TRINIUM);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_trinium"), new BlockItem(ORE_TRINIUM, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TIN = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_tin"), RAW_TIN);
        ORE_TIN = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F,3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_tin"), ORE_TIN);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_tin"), new BlockItem(ORE_TIN, new Item.Settings().group(EdoraItems.ORE_GROUP)));
    }

}
