package com.example.tutorial;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.ease.EaseSineInOut;


public class Functions 
{

	static void parrotWithLetterPath(final int number, float delay, int flip, float time, Sprite sprite, Sprite sprite1, float x1, final float x2, float y1, float y2) 
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
		
		DelayModifier dMod = new DelayModifier((float) delay,new IEntityModifierListener()
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
						
						if(MainActivity.parrotWay == 10)
						{
							Functions.letter(11, 2, MainActivity.letter4, MainActivity.letter4Pos, 
									MainActivity.letter4Pos,
									MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
							
							SoundFunction.audioPlay = true;
							SoundFunction.playAudioLoop2(R.raw.fiftht, R.raw.sixth);
						}
						else if(MainActivity.parrotWay == 7)
						{
							Functions.letter(8, 2, MainActivity.letter3, MainActivity.letter3Pos, 
									MainActivity.letter3Pos,MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
							
							SoundFunction.audioPlay = true;
							SoundFunction.playAudio(R.raw.fiftho);
							
						}
						else if(MainActivity.parrotWay == 4)
						{
							Functions.letter(5, 2, MainActivity.letter2, MainActivity.letter2Pos, 
									MainActivity.letter2Pos,
									MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
							
							SoundFunction.audioPlay = true;
							SoundFunction.playAudioLoop3(R.raw.thirdo ,R.raw.thirds ,R.raw.thirdt);
						}
						else
						{
							Functions.letter(2, (float) 1.5, MainActivity.letter1, MainActivity.letter1Pos, 
									MainActivity.letter1Pos,
									MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
							
							SoundFunction.audioPlay = true;
							SoundFunction.playAudio(R.raw.sec);
						}
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
						
						if(MainActivity.parrotWay == 11)
						{
							MainActivity.parrot.setFlippedHorizontal(true);
							
							Functions.path(12, MainActivity.parrot, MainActivity.letter4Pos,
									MainActivity.parrotHeight,
									MainActivity.letter1.getX(), MainActivity.letter1.getY() - 100,
									-200, MainActivity.parrotHeight
				 					);
						}
						else if(MainActivity.parrotWay == 8)
						{
							Functions.parrotPath(9, 1, 2, MainActivity.parrot, 
									MainActivity.letter3Pos, -150,
									MainActivity.parrotHeight,
									MainActivity.parrotHeight
									);
						}
						else if(MainActivity.parrotWay == 5)
						{
							Functions.parrotPath(6, 11, 3, MainActivity.parrot, MainActivity.letter2Pos,
									MainActivity.CAMERA_WIDTH+150,
									MainActivity.parrotHeight,
									MainActivity.parrotHeight
									);
							MainActivity.mScene.registerUpdateHandler(new TimerHandler((float) 0.85, new ITimerCallback() {
								
								@Override
								public void onTimePassed(TimerHandler pTimerHandler) 
								{
									// TODO Auto-generated method stub
									SoundFunction.audioPlay = true;
									SoundFunction.playAudio(R.raw.forth);
								}
							}));
							
						}
						else
						{
							Functions.parrotPath(3, 5, 2, MainActivity.parrot, MainActivity.letter1Pos,
									-150, 
									MainActivity.parrotHeight,
									MainActivity.parrotHeight
									);
						}
					}
				});  
		
		SequenceEntityModifier mLetter_sm = new SequenceEntityModifier(mModLetter1,dMod1);
		sprite.registerEntityModifier(mLetter_sm);
	}
	
	
	protected static void parrotPath(final int number, float delay, float time, Sprite sprite, float x1, float x2, float y1, float y2)
	{
		MoveModifier mModParrot1 = new MoveModifier(time, x1, x2, y1, y2);
		DelayModifier dMod1 = new DelayModifier((float) delay , new IEntityModifierListener()
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
						
						if(MainActivity.parrotWay == 9)
						{
							Functions.parrotWithLetterPath(10, (float) 0.5,
									1, 2, MainActivity.letter4, MainActivity.parrot, -150, 
									MainActivity.letter4Pos, 
									MainActivity.parrotHeight, MainActivity.parrotHeight);
							
							SoundFunction.audioPlay = true;
							SoundFunction.playAudio(R.raw.fifths);
						}
						else if(MainActivity.parrotWay == 6)
						{
						
							Functions.parrotWithLetterPath(7, 1,
									0, 3, MainActivity.letter3, MainActivity.parrot, 
									MainActivity.CAMERA_WIDTH+150, MainActivity.letter3Pos, 
									MainActivity.parrotHeight, MainActivity.parrotHeight);
						} 
						else
						{
							Functions.parrotWithLetterPath(4, 1,
									1, 3, MainActivity.letter2, MainActivity.parrot, -150, 
									MainActivity.letter2Pos,
									MainActivity.parrotHeight, MainActivity.parrotHeight);
						}
					}
				});
		
		SequenceEntityModifier mLetter_sm1 = new SequenceEntityModifier(mModParrot1,dMod1);
		sprite.registerEntityModifier(mLetter_sm1);
	}
	
	public static void path(final int number, Sprite sprite, float x1, float y1, float x2, float y2, float x3, float y3)
	{
		final Path path = new Path(3).to(x1, y1).to(x2, y2)
				.to(x3, y3); 
		sprite.registerEntityModifier(new PathModifier((float) 4.1, path, null, new IPathModifierListener() 
		{
			@Override
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
			{
				//Debug.d("onPathStarted");
			}

			@Override
			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
			{
				//Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
				
			}

			@Override
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
			{
				//Debug.d("onPathWaypointFinished: " + pWaypointIndex);
			}

			@Override
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity)
			{
				//Debug.d("onPathFinished");
				MainActivity.parrotWay = number; 
				
				if(MainActivity.parrotWay == 16)
				{
					MainActivity.ma.setVisible(true);
					SoundFunction.audioPlay = true;
					SoundFunction.playAudioLoop2(R.raw.thirteen, R.raw.forteen);
					
					MainActivity.mScene.registerUpdateHandler(new TimerHandler((float) 7.85, new ITimerCallback() 
					{
						
						@Override
						public void onTimePassed(TimerHandler pTimerHandler) 
						{
							// TODO Auto-generated method stub
							SoundFunction.audioPlay = true;
							SoundFunction.playAudioLoop3(R.raw.fifteen, R.raw.sixteen , R.raw.seventeen);
						} 
					}));
					
					MainActivity.mScene.registerUpdateHandler(new TimerHandler((float) 9.85, new ITimerCallback() 
					{
						
						@Override
						public void onTimePassed(TimerHandler pTimerHandler) 
						{
							// TODO Auto-generated method stub
							Functions.fadeOut(MainActivity.letter1);
						} 
					}));
					
					MainActivity.mScene.registerUpdateHandler(new TimerHandler((float) 10.85, new ITimerCallback() 
					{
						
						@Override
						public void onTimePassed(TimerHandler pTimerHandler) 
						{
							// TODO Auto-generated method stub
							Functions.fadeOut(MainActivity.letter4);
						} 
					}));
					
				}
				else if(MainActivity.parrotWay == 15)
				{
					Functions.path(16, MainActivity.parrot, MainActivity.letter3Pos-30,
							MainActivity.parrotHeight,
							MainActivity.letter4Pos-30, MainActivity.letter4.getY() -100,
							MainActivity.letter4Pos-30, MainActivity.parrotHeight
							);
					SoundFunction.audioPlay = true;
					SoundFunction.playAudio(R.raw.twelve);
				}
				else if(MainActivity.parrotWay == 14)
				{
					
					MainActivity.mScene.registerUpdateHandler(new TimerHandler(9, new ITimerCallback()
					{
						@Override
						public void onTimePassed(TimerHandler pTimerHandler) 
						{
							// TODO Auto-generated method stub
							Functions.path(15, MainActivity.parrot, MainActivity.letter2Pos-30,
									MainActivity.parrotHeight,
									MainActivity.letter3Pos-30, MainActivity.letter3.getY() -100,
									MainActivity.letter3Pos-30, MainActivity.parrotHeight
									);
							SoundFunction.audioPlay = true;
							SoundFunction.playAudio(R.raw.eleven);
						}
					}));
					
					MainActivity.aam.setVisible(true);
					
					SoundFunction.audioPlay = true;
					SoundFunction.playAudioLoop2(R.raw.nine, R.raw.ten);
				}
				else if(MainActivity.parrotWay == 13)
				{
					
					Functions.path(14, MainActivity.parrot, MainActivity.letter1Pos-30,
							MainActivity.parrotHeight,
							MainActivity.letter2Pos-30, MainActivity.letter2.getY() -100,
							MainActivity.letter2Pos-30, MainActivity.parrotHeight
							);
					SoundFunction.audioPlay = true;
					SoundFunction.playAudio(R.raw.eight);
 				}
				else if(MainActivity.parrotWay == 12)
				{
					MainActivity.parrot.setFlippedHorizontal(false);
					
					Functions.path(13, MainActivity.parrot, -150, MainActivity.parrotHeight,
							MainActivity.letter1.getX()-30, MainActivity.letter1.getY() - 100,
							MainActivity.letter1.getX()-30, MainActivity.parrotHeight
							);
					SoundFunction.audioPlay = true;
					SoundFunction.playAudio(R.raw.seven);
				}
				
			}
		}, EaseSineInOut.getInstance()));
	}
	
	public static void fadeOut(final Sprite sprite)
	{
		if(sprite!= null)
		{
		AlphaModifier yourModifier = new AlphaModifier(1f, 0.5f, 0f)
		{
		        @Override
		        protected void onModifierStarted(IEntity pItem)
		        {
		                super.onModifierStarted(pItem);
		                // Your action after starting modifier
		        }
		       
		        @Override
		        protected void onModifierFinished(IEntity pItem)
		        {
		                super.onModifierFinished(pItem);
		                // Your action after finishing modifier
		                sprite.setAlpha(1);
		        }
		};
		 
		sprite.registerEntityModifier(yourModifier);
		}
	}
	
}
