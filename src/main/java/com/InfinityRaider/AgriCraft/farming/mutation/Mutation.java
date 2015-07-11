package com.InfinityRaider.AgriCraft.farming.mutation;

import com.InfinityRaider.AgriCraft.api.v1.IMutation;
import com.InfinityRaider.AgriCraft.farming.CropPlantHandler;
import net.minecraft.item.ItemStack;

public class Mutation implements IMutation {
    private ItemStack result;
    private ItemStack parent1;
    private ItemStack parent2;
    private double chance;

    public ItemStack getResult() {
        return result.copy();
    }

    public ItemStack[] getParents() {
        ItemStack[] parents = new ItemStack[2];
        parents[0] = parent1.copy();
        parents[1] = parent2.copy();
        return parents;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double d) {
        this.chance = d;
    }

    public Mutation(ItemStack result, ItemStack parent1, ItemStack parent2, int chance) {
        this(result, parent1, parent2, ((double) chance)/100);
    }

    public Mutation(ItemStack result, ItemStack parent1, ItemStack parent2, double chance) {
        this.result = result;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.chance = chance;
    }

    public Mutation(ItemStack result, ItemStack parent1, ItemStack parent2) {
        this(result, parent1, parent2, 100);
        this.chance = 1.00/ CropPlantHandler.getPlantFromStack(result).getTier();
    }

    //copy constructor
    public Mutation(Mutation mutation) {
        this.result = mutation.result;
        this.parent1 = mutation.parent1;
        this.parent2 = mutation.parent2;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        if(object instanceof Mutation) {
            Mutation mutation = (Mutation) object;
            if(this.chance==mutation.chance) {
                if(this.result.isItemEqual(mutation.result)) {
                    if(this.parent1.isItemEqual(mutation.parent1) && this.parent2.isItemEqual(mutation.parent2)) {
                        isEqual = true;
                    }
                    else if(this.parent1.isItemEqual(mutation.parent2) && this.parent2.isItemEqual(mutation.parent1)) {
                        isEqual = true;
                    }
                }
            }
        }
        return isEqual;
    }
}
