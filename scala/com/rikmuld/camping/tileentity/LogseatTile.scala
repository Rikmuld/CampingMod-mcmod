package com.rikmuld.camping.tileentity

import com.rikmuld.camping.entity.Mountable
import com.rikmuld.corerm.tileentity.TileEntitySimple
import net.minecraft.block.state.IBlockState
import net.minecraft.util.ITickable
import net.minecraft.util.math.{AxisAlignedBB, BlockPos}
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.{Side, SideOnly}


class TileLogseat extends TileEntitySimple with ITickable {
  var mountable: Mountable =
    _

  @SideOnly(Side.CLIENT)
  override def getRenderBoundingBox: AxisAlignedBB =
    new AxisAlignedBB(pos.getX, pos.getY, pos.getZ, pos.getX+ 1, pos.getY + 1, pos.getZ + 1)

  override def shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newSate: IBlockState): Boolean =
    oldState.getBlock != newSate.getBlock

  override def update(): Unit = {
    if(Option(mountable).isEmpty){
      mountable = new Mountable(world)
      mountable.setPos(pos)

      world.spawnEntity(mountable)
    }
  }
}