package com.example.tutorial;

import org.andengine.entity.IEntity;
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
import android.media.MediaPlayer;

public class Functions 
{

	static Boolean audioPlay = false;
	static MediaPlayer mediaPlayer = new MediaPlayer();
	
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
		
		DelayModifier dMod = new DelayModifier((float) 1,new IEntityModifierListener()
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
							Functions.letter(11, 1, MainActivity.letter4, MainActivity.letter4Pos, 
									MainActivity.letter4Pos,
									MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
						}
						else if(MainActivity.parrotWay == 7)
						{
							Functions.letter(8, 1, MainActivity.letter3, MainActivity.letter3Pos, 
									MainActivity.letter3Pos,MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
						}
						else if(MainActivity.parrotWay == 4)
						{
							Functions.letter(5, 2, MainActivity.letter2, MainActivity.letter2Pos, 
									MainActivity.letter2Pos,
									MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
						}
						else
						{
							Functions.letter(2, 1, MainActivity.letter1, MainActivity.letter1Pos +20, 
									MainActivity.letter1Pos +20,
									MainActivity.CAMERA_HEIGHT / 2 -80, 
									MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
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
									MainActivity.letter1Pos, MainActivity.letter1.getY(),
									-200, MainActivity.parrotHeight
									);
						}
						else if(MainActivity.parrotWay == 8)
						{
							Functions.parrotPath(9, 4, MainActivity.parrot, 
									MainActivity.letter3Pos, -400,
									MainActivity.parrotHeight,
									MainActivity.parrotHeight
									);
						}
						else if(MainActivity.parrotWay == 5)
						{
							Functions.parrotPath(6, 3, MainActivity.parrot, MainActivity.letter2Pos,
									MainActivity.CAMERA_WIDTH+400,
									MainActivity.parrotHeight,
									MainActivity.parrotHeight
									);
						}
						else
						{
						
						Functions.parrotPath(3, 2, MainActivity.parrot, MainActivity.letter1Pos,
								-400, 
								MainActivity.parrotHeight,
								MainActivity.parrotHeight
								);
						}
					}
				});  
		
		SequenceEntityModifier mLetter_sm = new SequenceEntityModifier(mModLetter1,dMod1);
		sprite.registerEntityModifier(mLetter_sm);
	}
	
	
	protected static void parrotPath(final int number, float time, Sprite sprite, float x1, float x2, float y1, float y2)
	{
		MoveModifier mModParrot1 = new MoveModifier(time, x1, x2, y1, y2);
		DelayModifier dMod1 = new DelayModifier((float) 0,new IEntityModifierListener()
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
							Functions.parrotWithLetterPath(10,
									1, 7, MainActivity.letter4, MainActivity.parrot, -600, 
									MainActivity.letter4Pos, 
									MainActivity.parrotHeight, MainActivity.parrotHeight);
						}
						else if(MainActivity.parrotWay == 6)
						{
							Functions.parrotWithLetterPath(7, 
									0, 4, MainActivity.letter3, MainActivity.parrot, 
									MainActivity.CAMERA_WIDTH+400, MainActivity.letter3Pos, 
									MainActivity.parrotHeight, MainActivity.parrotHeight);
						} 
						else
						{
						Functions.parrotWithLetterPath(4, 
								1, 4, MainActivity.letter2, MainActivity.parrot, -400, 
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
				
				if(MainActivity.parrotWay == 14)
				{
					Functions.path(15, MainActivity.parrot, MainActivity.letter1Pos,
							MainActivity.parrotHeight,
							MainActivity.letter2Pos, MainActivity.letter2.getY(),
							MainActivity.letter2Pos, MainActivity.parrotHeight
							);
				}
				else if(MainActivity.parrotWay == 13)
				{
					Functions.path(14, MainActivity.parrot, -200, MainActivity.parrotHeight,
							MainActivity.letter1Pos, MainActivity.letter1.getY(),
							MainActivity.letter1Pos, MainActivity.parrotHeight
							);
				}
				else
				{
					MainActivity.parrot.setFlippedHorizontal(false);
					
					Functions.path(13, MainActivity.parrot, -200, MainActivity.parrotHeight,
							MainActivity.letter1Pos, MainActivity.letter1.getY(),
							MainActivity.letter1Pos, MainActivity.parrotHeight
							);
				}
				
			}
		}, EaseSineInOut.getInstance()));
	}
	
	//Audio play Function
	public static void playAudio(int val)
	{
		if(audioPlay)
		{
			if(!mediaPlayer.isPlaying())
			{
				mediaPlayer.reset();
				mediaPlayer = MediaPlayer.create(MainActivity.MainActivityInstace, val);
				
				try 
				{
					mediaPlayer.start();
					mediaPlayer.setLooping(false);
				} 
				catch (IllegalStateException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			audioPlay = true;
		}
	}
}
