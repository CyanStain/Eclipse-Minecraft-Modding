package com.cyanstain.tutorialmod.objects.items;

import java.util.List;

import com.cyanstain.tutorialmod.init.BlockInit;
import com.cyanstain.tutorialmod.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class SpecialItem extends Item 
{

	public SpecialItem(Properties properties) 
	{
		super(properties);
	}
	
	//makes the item have "enchanted effect" or "glint" or "purply glowing effect"
	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return true;
	}
	
	//adds a tooltip that tells tells players to hold shift so that the tool can call their mum a hoe
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if(KeyboardHelper.isHoldingShift())
		{
			tooltip.add(new StringTextComponent("Your mum's a " + "\u00A74" + "hoe"));
		} else {
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" +  " SHIFT " + "\u00A77" + "for more information!"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		playerIn.addPotionEffect(new EffectInstance(Effects.NAUSEA, 3, 255));
		worldIn.setRainStrength(1.0F);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) 
	{
		entity.getEntityWorld().setBlockState(entity.getPosition().down(), BlockInit.example_block.getDefaultState());
		return super.onEntityItemUpdate(stack, entity);
	}
	
	//this turns the item into furnace fuel, the return variable is in ticks. So 1600 ticks is like one piece of coal which smelts 8 items.
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 1600;
	}
}
