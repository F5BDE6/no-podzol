package com.github.f5bde6.podzol.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.function.Consumer;

@Debug(export = true)
@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
    @WrapOperation(method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    void generate(List<?> instance, Consumer<?> consumer, Operation<Void> original, @Local Random random, @Local TreeFeatureConfig treeFeatureConfig, @Local TreeDecorator.Generator generator) {
        treeFeatureConfig.decorators.forEach(decorator -> {
            var isPodzol =false;
            if (decorator instanceof AlterGroundTreeDecorator){
                if (((AlterGroundTreeDecoratorAccessor)decorator).getProvider() instanceof SimpleBlockStateProvider simpleBlockStateProvider){
                    if (simpleBlockStateProvider.get(random, new BlockPos(0,0,0)).isOf(Blocks.PODZOL)){
                        isPodzol =true;
                    }
                }
            }
            if (!isPodzol){
                original.call(instance, consumer);
            }
        });
    }
}
