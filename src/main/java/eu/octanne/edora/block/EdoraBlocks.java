package eu.octanne.edora.block;

import org.apache.logging.log4j.Level;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.block.blocks.EdoraGrassBlock;
import eu.octanne.edora.block.blocks.EdoraOreBlock;
import eu.octanne.edora.block.blocks.EdoraSlabBlock;
import eu.octanne.edora.item.EdoraItems;
import eu.octanne.edora.item.items.minerals.RawMineralItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.BlockSoundGroup;
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
    /** DEEPSLATE VERSION */
    public static EdoraOreBlock DEEPSLATE_ORE_BAUXYTE;
    public static EdoraOreBlock DEEPSLATE_ORE_GALENE;
    public static EdoraOreBlock DEEPSLATE_ORE_MALACHITE;
    public static EdoraOreBlock DEEPSLATE_ORE_PSILOMELANE;
    public static EdoraOreBlock DEEPSLATE_ORE_MANGANESE;
    public static EdoraOreBlock DEEPSLATE_ORE_SILVER;
    public static EdoraOreBlock DEEPSLATE_ORE_NICKEL_SULFUR;
    public static EdoraOreBlock DEEPSLATE_ORE_URANINYTE;
    public static EdoraOreBlock DEEPSLATE_ORE_TUNGSTEN;
    public static EdoraOreBlock DEEPSLATE_ORE_TRANSFORMIUM;
    public static EdoraOreBlock DEEPSLATE_ORE_ORICALC;
    public static EdoraOreBlock DEEPSLATE_ORE_TRINIUM;
    public static EdoraOreBlock DEEPSLATE_ORE_TIN;

    /** Grownd Block */
    public static Block DIRT_JUNGLE = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5f, 0.5f).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRAVEL));
    public static Block DIRT_ARID = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5f, 0.5f).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRAVEL));
    public static Block DIRT_DARK = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5f, 0.5f).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRAVEL));
    public static Block DIRT_LANTIA = new Block(FabricBlockSettings.of(Material.SOIL).strength(0.5f, 0.5f).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRAVEL));
    public static Block GRASS_BLOCK_JUNGLE = new EdoraGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK), EdoraBlocks.DIRT_JUNGLE);
    public static BlockItem GRASS_JUNGLE_ITEM = new BlockItem(GRASS_BLOCK_JUNGLE, new Item.Settings().group(EdoraItems.BLOCK_GROUP));
    public static Block GRASS_BLOCK_DARK = new EdoraGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRASS), EdoraBlocks.DIRT_DARK);
    public static BlockItem GRASS_DARK_ITEM = new BlockItem(GRASS_BLOCK_DARK, new Item.Settings().group(EdoraItems.BLOCK_GROUP));
    public static Block GRASS_BLOCK_ARID = new EdoraGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRASS), EdoraBlocks.DIRT_ARID);
    public static BlockItem GRASS_ARID_ITEM = new BlockItem(GRASS_BLOCK_ARID, new Item.Settings().group(EdoraItems.BLOCK_GROUP));
    public static Block GRASS_BLOCK_LANTIA = new EdoraGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).breakByTool(FabricToolTags.SHOVELS, 0).sounds(BlockSoundGroup.GRASS), EdoraBlocks.DIRT_LANTIA);
    public static BlockItem GRASS_LANTIA_ITEM = new BlockItem(GRASS_BLOCK_LANTIA, new Item.Settings().group(EdoraItems.BLOCK_GROUP));

    /**
     * Registry Blocks
     */
    public static void registryBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_slab"), DIRT_SLAB);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_slab"), new BlockItem(DIRT_SLAB, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
        /** Grownd Block */
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_jungle"), DIRT_JUNGLE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_jungle"), new BlockItem(DIRT_JUNGLE, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_dark"), DIRT_DARK);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_dark"), new BlockItem(DIRT_DARK, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_arid"), DIRT_ARID);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_arid"), new BlockItem(DIRT_ARID, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "dirt_lantia"), DIRT_LANTIA);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "dirt_lantia"), new BlockItem(DIRT_LANTIA, new Item.Settings().group(EdoraItems.BLOCK_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "grass_block_jungle"), GRASS_BLOCK_JUNGLE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "grass_block_jungle"), GRASS_JUNGLE_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "grass_block_dark"), GRASS_BLOCK_DARK);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "grass_block_dark"), GRASS_DARK_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "grass_block_arid"), GRASS_BLOCK_ARID);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "grass_block_arid"), GRASS_ARID_ITEM);
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "grass_block_lantia"), GRASS_BLOCK_LANTIA);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "grass_block_lantia"), GRASS_LANTIA_ITEM);
    }
    
    @Environment(EnvType.CLIENT)
    public static void registerBlockRenderLayerMap() {
        BlockRenderLayerMap.INSTANCE.putBlock(GRASS_BLOCK_JUNGLE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(GRASS_BLOCK_DARK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(GRASS_BLOCK_ARID, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(GRASS_BLOCK_LANTIA, RenderLayer.getTranslucent());
    }

    public static void registerColorBlocks() {
        registerGrassBlocks();
    }

    private static void registerGrassBlocks() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x33A91B, GRASS_JUNGLE_ITEM);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x275F2F, GRASS_DARK_ITEM);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xA9A900, GRASS_ARID_ITEM);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x756A3B, GRASS_LANTIA_ITEM);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D);
        }, GRASS_BLOCK_JUNGLE, GRASS_BLOCK_DARK, GRASS_BLOCK_ARID, GRASS_BLOCK_LANTIA);
    }

    public static void registryOreBlocks() {
        RAW_BAUXYTE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_bauxyte"), RAW_BAUXYTE);
        ORE_BAUXYTE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_bauxyte"), ORE_BAUXYTE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_bauxyte"), new BlockItem(ORE_BAUXYTE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_GALENE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_galene"), RAW_GALENE);
        ORE_GALENE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_galene"), ORE_GALENE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_galene"), new BlockItem(ORE_GALENE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_MALACHITE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_malachite"), RAW_MALACHITE);
        ORE_MALACHITE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_malachite"), ORE_MALACHITE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_malachite"), new BlockItem(ORE_MALACHITE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_PSILOMELANE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_psilomelane"), RAW_PSILOMELANE);
        ORE_PSILOMELANE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_psilomelane"), ORE_PSILOMELANE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_psilomelane"), new BlockItem(ORE_PSILOMELANE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_MANGANESE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_manganese"), RAW_MANGANESE);
        ORE_MANGANESE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_manganese"), ORE_MANGANESE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_manganese"), new BlockItem(ORE_MANGANESE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_SILVER = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_silver"), RAW_SILVER);
        ORE_SILVER = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_silver"), ORE_SILVER);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_silver"), new BlockItem(ORE_SILVER, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_NICKEL_SULFUR = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_nickel_sulfur"), RAW_NICKEL_SULFUR);
        ORE_NICKEL_SULFUR = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_nickel_sulfur"), ORE_NICKEL_SULFUR);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_nickel_sulfur"), new BlockItem(ORE_NICKEL_SULFUR, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_URANINYTE = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_uraninyte"), RAW_URANINYTE);
        ORE_URANINYTE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.0F, 4.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_uraninyte"), ORE_URANINYTE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_uraninyte"), new BlockItem(ORE_URANINYTE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TUNGSTEN = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_tungsten"), RAW_TUNGSTEN);
        ORE_TUNGSTEN = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_tungsten"), ORE_TUNGSTEN);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_tungsten"), new BlockItem(ORE_TUNGSTEN, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TRANSFORMIUM = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_transformium"), RAW_TRANSFORMIUM);
        ORE_TRANSFORMIUM = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.5F, 4.5F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_transformium"), ORE_TRANSFORMIUM);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_transformium"), new BlockItem(ORE_TRANSFORMIUM, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_ORICALC = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_oricalc"), RAW_ORICALC);
        ORE_ORICALC = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.5F, 4.5F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_oricalc"), ORE_ORICALC);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_oricalc"), new BlockItem(ORE_ORICALC, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TRINIUM = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_trinium"), RAW_TRINIUM);
        ORE_TRINIUM = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_trinium"), ORE_TRINIUM);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_trinium"), new BlockItem(ORE_TRINIUM, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        RAW_TIN = new RawMineralItem(new Item.Settings().group(EdoraItems.MINERAL_GROUP));
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "raw_tin"), RAW_TIN);
        ORE_TIN = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "ore_tin"), ORE_TIN);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "ore_tin"), new BlockItem(ORE_TIN, new Item.Settings().group(EdoraItems.ORE_GROUP)));

        /** DEEPSLATE VERSION */
        DEEPSLATE_ORE_BAUXYTE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_bauxyte"), DEEPSLATE_ORE_BAUXYTE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_bauxyte"), new BlockItem(DEEPSLATE_ORE_BAUXYTE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_GALENE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 1).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_galene"), DEEPSLATE_ORE_GALENE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_galene"), new BlockItem(DEEPSLATE_ORE_GALENE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_MALACHITE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_malachite"), DEEPSLATE_ORE_MALACHITE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_malachite"), new BlockItem(DEEPSLATE_ORE_MALACHITE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_PSILOMELANE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_psilomelane"), DEEPSLATE_ORE_PSILOMELANE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_psilomelane"), new BlockItem(DEEPSLATE_ORE_PSILOMELANE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_MANGANESE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_manganese"), DEEPSLATE_ORE_MANGANESE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_manganese"), new BlockItem(DEEPSLATE_ORE_MANGANESE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_SILVER = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_silver"), DEEPSLATE_ORE_SILVER);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_silver"), new BlockItem(DEEPSLATE_ORE_SILVER, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_NICKEL_SULFUR = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_nickel_sulfur"), DEEPSLATE_ORE_NICKEL_SULFUR);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_nickel_sulfur"), new BlockItem(DEEPSLATE_ORE_NICKEL_SULFUR, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_URANINYTE = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.0F, 4.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_uraninyte"), DEEPSLATE_ORE_URANINYTE);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_uraninyte"), new BlockItem(DEEPSLATE_ORE_URANINYTE, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_TUNGSTEN = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_tungsten"), DEEPSLATE_ORE_TUNGSTEN);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_tungsten"), new BlockItem(DEEPSLATE_ORE_TUNGSTEN, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_TRANSFORMIUM = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.5F, 4.5F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_transformium"), DEEPSLATE_ORE_TRANSFORMIUM);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_transformium"), new BlockItem(DEEPSLATE_ORE_TRANSFORMIUM, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_ORICALC = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(4.5F, 4.5F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_oricalc"), DEEPSLATE_ORE_ORICALC);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_oricalc"), new BlockItem(DEEPSLATE_ORE_ORICALC, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_TRINIUM = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_trinium"), DEEPSLATE_ORE_TRINIUM);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_trinium"), new BlockItem(DEEPSLATE_ORE_TRINIUM, new Item.Settings().group(EdoraItems.ORE_GROUP)));
        DEEPSLATE_ORE_TIN = new EdoraOreBlock(FabricBlockSettings.of(ORE_MATERIAL).strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool().sounds(BlockSoundGroup.DEEPSLATE));
        Registry.register(Registry.BLOCK, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_tin"), DEEPSLATE_ORE_TIN);
        Registry.register(Registry.ITEM, new Identifier(EdoraMain.MOD_ID, "deepslate_ore_tin"), new BlockItem(DEEPSLATE_ORE_TIN, new Item.Settings().group(EdoraItems.ORE_GROUP)));
    }
}
