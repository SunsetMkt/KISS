package fr.neamar.kiss.utils;

import androidx.annotation.NonNull;

public enum IconShape {

    SHAPE_SYSTEM(0),
    SHAPE_CIRCLE(1),
    SHAPE_SQUARE(2),
    SHAPE_SQUIRCLE(3),
    SHAPE_ROUND_RECT(4),
    SHAPE_TEARDROP_BR(5),
    SHAPE_TEARDROP_BL(6),
    SHAPE_TEARDROP_TL(7),
    SHAPE_TEARDROP_TR(8),
    SHAPE_TEARDROP_RND(9),
    SHAPE_HEXAGON(10),
    SHAPE_OCTAGON(11);

    private final int id;

    IconShape(int id) {
        this.id = id;
    }

    @NonNull
    public static IconShape valueById(int shapeId) {
        for (IconShape shape : values()) {
            if (shape.getId() == shapeId) {
                return shape;
            }
        }
        return SHAPE_SYSTEM;
    }

    public int getId() {
        return id;
    }

    public IconShape getFinalShape(int hash) {
        switch (this) {
            case SHAPE_SYSTEM:
                if (!DrawableUtils.hasDeviceConfiguredMask()) {
                    return IconShape.SHAPE_CIRCLE;
                }
                return this;
            case SHAPE_TEARDROP_RND:
                IconShape[] shapes = {SHAPE_TEARDROP_BR, SHAPE_TEARDROP_BL, SHAPE_TEARDROP_TL, SHAPE_TEARDROP_TR};
                return shapes[Math.abs(hash % 4)];
            default:
                return this;
        }
    }
}
