package com.github.f5bde6.podzol.mixin;

import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AlterGroundTreeDecorator.class)
public interface AlterGroundTreeDecoratorAccessor {
    @Accessor
    BlockStateProvider getProvider();
}
