package com.broxus.blockchainexplorer

object ShardHelper {

  def bits_negate64(x: Long): Long =
    ~x + 1;

  def lower_bit64(x: Long): Long =
    x & bits_negate64(x);

  def is_right_child(x: Long): Boolean =
    (x & (lower_bit64(x) << 1)) != 0

  def is_left_child(x: Long): Boolean =
    !is_right_child(x)

  def shard_childs(shard: Long): (Long, Long) = {
    val x = lower_bit64(shard) >> 1;
    ((shard - x), (shard + x))
  }
}
