package com.infinityraider.agricraft.content.core;

import com.infinityraider.agricraft.content.AgriBlockRegistry;
import com.infinityraider.agricraft.content.AgriItemRegistry;
import com.infinityraider.agricraft.reference.Names;
import com.infinityraider.agricraft.util.FluidPredicates;
import com.infinityraider.infinitylib.block.BlockBase;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;

import java.util.function.Predicate;
import java.util.function.Supplier;

public enum CropStickVariant {
    WOOD(
            Material.PLANT,
            3,
            SoundType.WOOD,
            () -> () -> AgriItemRegistry.getInstance().crop_sticks_wood.get(),
            () -> () -> AgriBlockRegistry.getInstance().crop_sticks_wood.get(),
            FluidPredicates.NOT_LAVA
    ),

    IRON(
            Material.PLANT,
            7,
            SoundType.ANVIL,
            () -> () -> AgriItemRegistry.getInstance().crop_sticks_iron.get(),
            () -> () -> AgriBlockRegistry.getInstance().crop_sticks_iron.get(),
            FluidPredicates.ANY_FLUID
    ),

    OBSIDIAN(Material.PLANT,
            7,
            SoundType.BASALT,
            () -> () -> AgriItemRegistry.getInstance().crop_sticks_obsidian.get(),
            () -> () -> AgriBlockRegistry.getInstance().crop_sticks_obsidian.get(),
            FluidPredicates.ANY_FLUID
    );

    private final String id;
    private final int strength;
    private final SoundType sound;
    private final Material material;
    private final Supplier<Supplier<ItemCropSticks>> itemSupplier;
    private final Supplier<Supplier<BlockBase>> blockSupplier;
    private final Predicate<Fluid> fluidPredicate;

    CropStickVariant(Material material, int strength, SoundType sound,
                     Supplier<Supplier<ItemCropSticks>> itemSupplier,
                     Supplier<Supplier<BlockBase>> blockSupplier,
                     Predicate<Fluid> fluidPredicate) {
        this.id = Names.Blocks.CROP_STICKS + "_" + this.name().toLowerCase();
        this.strength = strength;
        this.sound = sound;
        this.material = material;
        this.itemSupplier = itemSupplier;
        this.blockSupplier = blockSupplier;
        this.fluidPredicate = fluidPredicate;
    }

    public final String getId() {
        return this.id;
    }

    public final int getStrength() {
        return this.strength;
    }

    public final SoundType getSound() {
        return this.sound;
    }

    public final Material getMaterial() {
        return this.material;
    }

    public final ItemCropSticks getItem() {
        return this.itemSupplier.get().get();
    }

    public final BlockBase getBlock() {
        return this.blockSupplier.get().get();
    }

    public final boolean canExistInFluid(Fluid fluid) {
        return this.fluidPredicate.test(fluid);
    }
}
