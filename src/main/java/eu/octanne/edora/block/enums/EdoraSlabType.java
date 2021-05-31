package eu.octanne.edora.block.enums;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public enum EdoraSlabType implements StringIdentifiable{
    TOP("top"), BOTTOM("bottom"), DOUBLE("double"), WEST("west"), EAST("east"), NORTH("north"), SOUTH("south");

    public static final EnumProperty<EdoraSlabType> SLAB_TYPE;

    private final String name;

    private EdoraSlabType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }

    static {
        SLAB_TYPE = EnumProperty.of("type", EdoraSlabType.class);
    }
}
