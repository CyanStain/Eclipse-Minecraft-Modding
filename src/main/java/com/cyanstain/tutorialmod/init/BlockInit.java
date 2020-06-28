package com.cyanstain.tutorialmod.init;

import com.cyanstain.tutorialmod.TutorialMod;
import com.cyanstain.tutorialmod.TutorialMod.TutorialItemGroup;
import com.cyanstain.tutorialmod.objects.blocks.SpecalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TutorialMod.MOD_ID)
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD)
public class BlockInit {
	// basic blocks
	public static final Block example_block = null;
	public static final Block example_ore = null;
	// advanced blocks
	public static final Block specal_block = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		// basic blocks
		event.getRegistry()
				.register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
						.sound(SoundType.SAND).harvestLevel(1).harvestTool(ToolType.SHOVEL))
								.setRegistryName("example_block"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
				.harvestLevel(1).harvestTool(ToolType.PICKAXE)).setRegistryName("example_ore"));
		// advanced blocks
		event.getRegistry()
				.register(new SpecalBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 10.0F)
						.harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).lightValue(4)
						.slipperiness(1.2F).speedFactor(0.7F).noDrops()).setRegistryName("specal_block"));
	}

	@SubscribeEvent
	public static void registerBlockItem(final RegistryEvent.Register<Item> event) {
		// basic blocks
		event.getRegistry().register(
				new BlockItem(example_block, new Item.Properties().maxStackSize(64).group(TutorialItemGroup.instance))
						.setRegistryName("example_block"));
		event.getRegistry().register(
				new BlockItem(example_ore, new Item.Properties().maxStackSize(64).group(TutorialItemGroup.instance))
						.setRegistryName("example_ore"));
		// advanced blocks
		event.getRegistry()
				.register(new BlockItem(specal_block, new Item.Properties().group(TutorialItemGroup.instance))
						.setRegistryName("specal_block"));
	}
}
