package org.bukkit.event.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when a player interacts with a Bucket
 */
public abstract class PlayerBucketEvent extends PlayerEvent implements Cancellable {

    private final Block block;
    private final Block blockClicked;
    private final BlockFace blockFace;
    private final Material bucket;
    private final EquipmentSlot hand;
    private ItemStack itemStack;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.14.4", forRemoval = true)
    public PlayerBucketEvent(@NotNull final Player player, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        this(player, null, blockClicked.getRelative(blockFace), blockFace, bucket, itemInHand, EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.19.2", forRemoval = true)
    public PlayerBucketEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        this(player, block, blockClicked, blockFace, bucket, itemInHand, EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    public PlayerBucketEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand, @NotNull final EquipmentSlot hand) {
        super(player);
        this.block = block;
        this.blockClicked = blockClicked;
        this.blockFace = blockFace;
        this.itemStack = itemInHand;
        if (bucket != null && bucket.isLegacy()) {
            this.bucket = Bukkit.getUnsafe().fromLegacy(new MaterialData(bucket), true);
        } else {
            this.bucket = bucket;
        }
        this.hand = hand;
    }

    /**
     * Gets the block involved in this event.
     *
     * @return The Block which block is involved in this event
     */
    @NotNull
    public final Block getBlock() {
        return this.block;
    }

    /**
     * Return the block clicked
     *
     * @return the clicked block
     */
    @NotNull
    public Block getBlockClicked() {
        return this.blockClicked;
    }

    /**
     * Get the face on the clicked block
     *
     * @return the clicked face
     */
    @NotNull
    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    /**
     * Returns the bucket used in this event
     *
     * @return the used bucket
     */
    @NotNull
    public Material getBucket() {
        return this.bucket;
    }

    /**
     * Get the hand that was used in this event.
     *
     * @return the hand
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * Get the resulting item in hand after the bucket event
     *
     * @return ItemStack hold in hand after the event.
     */
    @Nullable
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * Set the item in hand after the event
     *
     * @param itemStack the new held ItemStack after the bucket event.
     */
    public void setItemStack(@Nullable ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
