package com.cyanstain.tutorialmod.util.helpers;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardHelper 

// The point of this class is to help find out when the player is holding keys like shift or control
{
	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	//checks to see if the player is holding right or left SHIFT
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingShift() 
	{
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
	}
	
	//checks to see if the player is holding right or left CTRL
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingCtrl() 
	{
		return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
	}
}
