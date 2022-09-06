package org.aurorus.bloom.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class BloomOnClick implements Listener {
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = p.getInventory().getItemInOffHand();
        Block clickedBlock = e.getClickedBlock();
        ArrayList<Material> flowersList = new ArrayList<>(Arrays.asList(Material.POPPY, Material.DANDELION, Material.BLUE_ORCHID, Material.ALLIUM, Material.AZURE_BLUET, Material.RED_TULIP, Material.ORANGE_TULIP, Material.WHITE_TULIP, Material.PINK_TULIP, Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY));

        if(e.getHand() != EquipmentSlot.HAND) return;
        if(clickedBlock == null) return;
        if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(!itemInMainHand.getType().equals(Material.BONE_MEAL) && !itemInOffHand.getType().equals(Material.BONE_MEAL)) return;
        if(!flowersList.contains(clickedBlock.getType())) return;

        Location loc = clickedBlock.getLocation();

        p.getWorld().dropItemNaturally(loc, new ItemStack(clickedBlock.getType()));
        p.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 20, 1, 1, 1);

        if (itemInMainHand.getType().equals(Material.BONE_MEAL)) {
            itemInMainHand.subtract();
            p.swingMainHand();
        }
        else {
            itemInOffHand.subtract();
            p.swingOffHand();
        }
    }
}
