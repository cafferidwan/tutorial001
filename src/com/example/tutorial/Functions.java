package com.example.tutorial;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.IModifier;

public class Functions 
{

	static void parrotWithLetterPath(final int number, int flip, float time, Sprite sprite, Sprite sprite1, float x1, final float x2, float y1, float y2) 
	{
		
		if(flip == 0)
		{
			sprite1.setFlippedHorizontal(true);
		}
		else if(flip == 1)
		{
			sprite1.setFlippedHorizontal(false);
		}
		
		MoveModifier mModParrot = new MoveModifier(time, x1, x2, y1, y2);
		MoveModifier mModLetter = new MoveModifier(time, x1 +20, x2 +20, y1 +120, y2 +120);
		
		DelayModifier dMod = new DelayModifier((float) time,new IEntityModifierListener()
		{

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) 
					{
					
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1)
					{
						MainActivity.parrotWay = number;
					}
				});
		
		SequenceEntityModifier parrot_sm = new SequenceEntityModifier(mModParrot,dMod);
		SequenceEntityModifier mLetter_sm = new SequenceEntityModifier(mModLetter,dMod);
		
		sprite1.registerEntityModifier(parrot_sm);
		sprite.registerEntityModifier(mLetter_sm);
		
	}
	
	static void letter(final int number, float time,final Sprite sprite, float x1, float x2, float y1, float y2)
	{
		MoveModifier mModLetter1 = new MoveModifier(time, x1, x2, y1, y2);
		
		DelayModifier dMod1 = new DelayModifier((float) time,new IEntityModifierListener()
		{

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) 
					{
					
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1)
					{
						MainActivity.parrotWay = number; 
					}
				});  
		
		SequenceEntityModifier mLetter_sm = new SequenceEntityModifier(mModLetter1,dMod1);
		sprite.registerEntityModifier(mLetter_sm);
	}
	
	protected static void parrotPath(final int number, float time, Sprite sprite, float x1, float x2, float y1, float y2)
	{
		MoveModifier mModParrot1 = new MoveModifier(time, x1, x2, y1, y2);
		
		DelayModifier dMod1 = new DelayModifier((float) time,new IEntityModifierListener()
		{

					@Override
					public void onModifierStarted(IModifier<IEntity> arg0,
							IEntity arg1) 
					{
					
					}

					@Override
					public void onModifierFinished(IModifier<IEntity> arg0,
							IEntity arg1)
					{
						
						MainActivity.parrotWay = number; 
					}
				});
		
		SequenceEntityModifier mLetter_sm1 = new SequenceEntityModifier(mModParrot1,dMod1);
		sprite.registerEntityModifier(mLetter_sm1);
	}
}
