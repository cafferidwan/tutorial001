package com.example.tutorial;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class TutorialPage extends Scene
{
	MainActivity MainActivityInstance;

	public TutorialPage()
	{
		setBackground(new Background(0.09804f, 0.6274f, 0));
		MainActivity.backGround2 = new Sprite(0, 0,MainActivity.mbackGround2TextureRegion,
				MainActivity.vertexBufferObjectManager);
		MainActivity.backGround2.setHeight(MainActivity.CAMERA_HEIGHT);
		MainActivity.backGround2.setWidth(MainActivity.CAMERA_WIDTH);
		attachChild(MainActivity.backGround2);
		MainActivityInstance = MainActivity.getSharedInstances();

		MainActivity.mo = new Sprite(MainActivity.CAMERA_WIDTH / 18,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mMoTextureRegion,
				MainActivity.vertexBufferObjectManager) 
		{
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY)
			{
				switch (pSceneTouchEvent.getAction()) 
				{
				case TouchEvent.ACTION_DOWN:

					this.setScale((float) 1.2);

					break;
				case TouchEvent.ACTION_UP:

					this.setScale((float) 1.0);
					break;
				}

				return true;
			}
		};
		MainActivity.mo.setHeight(MainActivity.CAMERA_HEIGHT / 5);
		MainActivity.mo.setWidth(MainActivity.CAMERA_WIDTH / 9);
		registerTouchArea(MainActivity.mo);
		attachChild(MainActivity.mo);
	}
}
