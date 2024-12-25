package net.alminoris.jamandjelly.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class JellyBlock extends FallingBlock
{
    public static final MapCodec<JellyBlock> CODEC = createCodec(JellyBlock::new);

    public JellyBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec()
    {
        return CODEC;
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
    {
        Vec3d vec3d = new Vec3d(0.5, 0.25F, 0.5);
        if (entity instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(StatusEffects.WEAVING))
        {
            vec3d = new Vec3d(0.75, 0.5, 0.75);
        }

        entity.slowMovement(state, vec3d);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (!(world instanceof World realWorld))
        {
            return VoxelShapes.empty();
        }

        if (context instanceof EntityShapeContext entityContext && entityContext.getEntity() instanceof LivingEntity livingEntity)
        {
           if (livingEntity.isSprinting())
               return VoxelShapes.fullCube();
        }

        return VoxelShapes.empty();
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity.bypassesLandingEffects())
        {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        }
        else
        {
            entity.handleFallDamage(fallDistance, 0.0F, world.getDamageSources().fall());
        }
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity)
    {
        if (entity.bypassesLandingEffects())
        {
            super.onEntityLand(world, entity);
        }
        else
        {
            this.bounce(entity);
        }
    }

    private void bounce(Entity entity)
    {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0)
        {
            double d = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
    {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1 && !entity.bypassesSteppingEffects())
        {
            double e = 0.4 + d * 0.2;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0, e));
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
