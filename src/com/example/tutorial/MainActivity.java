package com.example.tutorial;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import android.view.Display;

public class MainActivity extends SimpleBaseGameActivity
{

	static int CAMERA_WIDTH;
	static int CAMERA_HEIGHT;
	public Camera mCamera;
	public static Scene mScene;
	
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas, mBitmapTextureAtlas1;
	public static ITextureRegion mKolomTextureRegion, mBoardTextureRegion;
	ITiledTextureRegion mParrotTextureRegion;
	public static ITextureRegion mMoTextureRegion;
	public static ITextureRegion mLetter1TextureRegion, mLetter2TextureRegion, mLetter3TextureRegion;
	
	public static ITextureRegion mbackGroundTextureRegion, mbackGround2TextureRegion;
	
	private BuildableBitmapTextureAtlas mAnimatedBitmapTextureAtlas;
	
	public static Sprite backGround, backGround2;
	public static Sprite board, monkey;
	public static Parrot parrot; 
	public static Letter letter1, letter2, letter3;
	public static Sprite mo;
	
	public static MainActivity MainActivityInstace;
	public static VertexBufferObjectManager vertexBufferObjectManager;
	
	public static float parrotHeight, letter1Pos, letter2Pos, letter3Pos;
	public static int parrotWay;
	
	public static MainActivity getSharedInstances()
	{
		return MainActivityInstace;
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HEIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
		
		parrotHeight = MainActivity.CAMERA_HEIGHT / 2 -200;
		letter1Pos = MainActivity.CAMERA_WIDTH/2 - MainActivity.CAMERA_WIDTH/4;
		letter2Pos = letter1Pos + 100;
		letter3Pos = letter2Pos + 200;
		
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}

	@Override
	protected void onCreateResources()
	{
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("GameMenuGfx/");

		mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(
				this.getTextureManager(), 1600, 1200);
		mBitmapTextureAtlas1 = new BuildableBitmapTextureAtlas(
				this.getTextureManager(), 1600, 1200);

		mbackGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "bg3.png");
		mbackGround2TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "bg.png");
		
		
		mMoTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png");
		
		mLetter1TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png");
		mLetter2TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png");
		mLetter3TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png");
		
		mAnimatedBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 1000, 334, TextureOptions.NEAREST);
		mParrotTextureRegion = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(this.mAnimatedBitmapTextureAtlas, this, "parrot-4.png", 6, 2);
		
		try 
		{
			mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
					BitmapTextureAtlas>(0, 0, 0));
			mBitmapTextureAtlas.load();
		} 
		catch (TextureAtlasBuilderException e)
		{
			Debug.e(e);
		}
		try 
		{
			mBitmapTextureAtlas1.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
					BitmapTextureAtlas>(0, 0, 0));
			mBitmapTextureAtlas1.load();
		} 
		catch (TextureAtlasBuilderException e)
		{
			Debug.e(e);
		}
		
		try 
		{
			this.mAnimatedBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			this.mAnimatedBitmapTextureAtlas.load();
		} 
		catch (TextureAtlasBuilderException e) 
		{
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene()
	{
		// TODO Auto-generated method stub
		mScene = new Scene();
		mScene.setBackground(new Background(Color.WHITE));
		mScene.setTouchAreaBindingOnActionDownEnabled(true);
		
		vertexBufferObjectManager = getVertexBufferObjectManager();
		
		backGround = new Sprite(0, 0, mbackGroundTextureRegion, getVertexBufferObjectManager());
		backGround.setHeight(CAMERA_HEIGHT);
		backGround.setWidth(CAMERA_WIDTH);
		mScene.attachChild(backGround);
		
		letter1 = new Letter(MainActivity.CAMERA_WIDTH / 18+100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mMoTextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter1);
		mScene.attachChild(letter1);
		
		letter2 = new Letter(MainActivity.CAMERA_WIDTH +100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mMoTextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter2);
		mScene.attachChild(letter2);
		
		letter3 = new Letter(MainActivity.CAMERA_WIDTH +100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mMoTextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter3);
		mScene.attachChild(letter3);
		
//		letter2 = new Letter(MainActivity.CAMERA_WIDTH / 18,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mMoTextureRegion,
//				MainActivity.vertexBufferObjectManager);
//		mScene.registerTouchArea(letter2);
//		mScene.attachChild(letter2);
//		
//		letter3 = new Letter(MainActivity.CAMERA_WIDTH / 18-100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mMoTextureRegion,
//				MainActivity.vertexBufferObjectManager); 
//		mScene.registerTouchArea(letter3); 
//		mScene.attachChild(letter3);
		
		parrot = new Parrot(20, 30, mParrotTextureRegion, getVertexBufferObjectManager());
		parrot.animate(new long[]{80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80}, 0, 11, true);
		parrot.setFlippedHorizontal(true);
		mScene.registerTouchArea(parrot); 
		mScene.attachChild(parrot);
		
		mScene.registerUpdateHandler(new TimerHandler(1/100f, true, new ITimerCallback()
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) 
			{
				// TODO Auto-generated method stub
				if(parrotWay == 1)
				{
					Functions.letter(2, 1, MainActivity.letter1, letter1Pos +20, letter1Pos +20,
							MainActivity.CAMERA_HEIGHT / 2 -80, 
							MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
				}
				else if(parrotWay == 2)
				{
					Functions.parrotPath(3, 2, parrot, MainActivity.letter1Pos,
							-150,
							MainActivity.parrotHeight,
							MainActivity.parrotHeight
							);
				}
				else if(parrotWay == 3)
				{ 
					Functions.parrotWithLetterPath(4, 
							1, 4, MainActivity.letter2, parrot, -250, letter2Pos, 
							MainActivity.parrotHeight, MainActivity.parrotHeight);
				}
				else if(parrotWay == 4)
				{
					Functions.letter(5, 2, MainActivity.letter2, letter2Pos, letter2Pos,
							MainActivity.CAMERA_HEIGHT / 2 -80, 
							MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
				} 
				else if(parrotWay == 5)
				{
					Functions.parrotPath(6, 2, parrot, MainActivity.letter2Pos,
							MainActivity.CAMERA_WIDTH,
							MainActivity.parrotHeight,
							MainActivity.parrotHeight
							);
				}
				else if(parrotWay == 6)
				{
					Functions.parrotWithLetterPath(7, 
							0, 2, MainActivity.letter3, parrot, CAMERA_WIDTH, letter3Pos, 
							MainActivity.parrotHeight, MainActivity.parrotHeight);
				}
				else if(parrotWay == 7)
				{
					Functions.letter(8, 1, MainActivity.letter3, letter3Pos, letter3Pos,
							MainActivity.CAMERA_HEIGHT / 2 -80, 
							MainActivity.CAMERA_HEIGHT-MainActivity.CAMERA_HEIGHT/5);
				}
				else if(parrotWay == 8)
				{
					Functions.parrotPath(9, 2, parrot, letter3Pos,
							-150,
							MainActivity.parrotHeight,
							MainActivity.parrotHeight
							);

				}
				else if(parrotWay == 9)
				{
					
				}
				else if(parrotWay == 10)
				{
					
				}
				else if(parrotWay == 11)
				{
					
				}
			}
		}));
		
		Functions.parrotWithLetterPath(1, 0, 4,  letter1, parrot, MainActivity.CAMERA_WIDTH  , letter1Pos,
				parrotHeight, parrotHeight);
		
		return mScene;
	}

	public void setCurrentScene(Scene scene)
	{
		mScene = scene;
		getEngine().setScene(mScene);
	}

}
