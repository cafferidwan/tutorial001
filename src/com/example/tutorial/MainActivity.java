package com.example.tutorial;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
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
	
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas, mBitmapTextureAtlas1, mBitmapTextureAtlas2;
	public static ITextureRegion mAamTextureRegion, mMaTextureRegion;
	ITiledTextureRegion mParrotTextureRegion;
	public static ITextureRegion mMoTextureRegion;
	public static ITextureRegion mLetter1TextureRegion, mLetter2TextureRegion, mLetter3TextureRegion, mLetter4TextureRegion;
	
	public static ITextureRegion mbackGroundTextureRegion, mbackGround2TextureRegion;
	
	private BuildableBitmapTextureAtlas mAnimatedBitmapTextureAtlas;
	
	public static Sprite backGround, backGround2;
	public static Sprite aam, ma;
	public static Parrot parrot; 
	public static Letter letter1, letter2, letter3, letter4;
	public static Sprite mo;
	
	public static MainActivity MainActivityInstace;
	public static VertexBufferObjectManager vertexBufferObjectManager;
	
	public static float parrotHeight, letter1Pos, letter2Pos, letter3Pos, letter4Pos;
	public static int parrotWay;
	
	public static MainActivity getSharedInstances()
	{
		return MainActivityInstace; 
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		// TODO Auto-generated method stub
		MainActivityInstace = this;
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_HEIGHT = display.getHeight();
		CAMERA_WIDTH = display.getWidth();
		
		parrotHeight = MainActivity.CAMERA_HEIGHT / 2 -200;
		letter1Pos = MainActivity.CAMERA_WIDTH/2 - MainActivity.CAMERA_WIDTH/4 - 100;
		letter2Pos = letter1Pos + 100;
		letter3Pos = letter2Pos + 300;
		letter4Pos = letter3Pos + 100; 
		
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
		mBitmapTextureAtlas2 = new BuildableBitmapTextureAtlas(
				this.getTextureManager(), 1600, 1200);
		

		mbackGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "JungleBG.png");
		mbackGround2TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "JungleBG.png");
		
		mMoTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png"); 
		
		mLetter1TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "shorea.png");
		mLetter2TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png");
		mLetter3TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "mo.png");
		mLetter4TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas1, this, "akar.png");

		mAamTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas2, this, "aam.png");
		mMaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas2, this, "ma.png");

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
			mBitmapTextureAtlas2.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
					BitmapTextureAtlas>(0, 0, 0));
			mBitmapTextureAtlas2.load();
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
		
		letter1 = new Letter(CAMERA_WIDTH , parrotHeight, MainActivity.mLetter1TextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter1);
		mScene.attachChild(letter1);
		
		letter2 = new Letter(MainActivity.CAMERA_WIDTH +100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mLetter2TextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter2);
		mScene.attachChild(letter2);
		
		letter3 = new Letter(MainActivity.CAMERA_WIDTH +100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mLetter3TextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter3);
		mScene.attachChild(letter3);
		
		letter4 = new Letter(MainActivity.CAMERA_WIDTH +100,MainActivity.CAMERA_HEIGHT / 18, MainActivity.mLetter4TextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(letter4);
		mScene.attachChild(letter4);
		
		parrot = new Parrot(CAMERA_WIDTH, parrotHeight, mParrotTextureRegion, getVertexBufferObjectManager());
		parrot.animate(new long[]{80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80}, 0, 11, true);
		parrot.setFlippedHorizontal(true);
		mScene.registerTouchArea(parrot); 
		mScene.attachChild(parrot);
		
		aam = new Letter(MainActivity.letter1Pos + 50, MainActivity.CAMERA_HEIGHT/2 , MainActivity.mAamTextureRegion,
				MainActivity.vertexBufferObjectManager); 
		mScene.registerTouchArea(aam);
		aam.setWidth(130);
		aam.setHeight(130);
		mScene.attachChild(aam);
		aam.setVisible(false);
		
		ma = new Letter(MainActivity.letter3Pos + 50,MainActivity.CAMERA_HEIGHT/2 , MainActivity.mMaTextureRegion,
				MainActivity.vertexBufferObjectManager);
		mScene.registerTouchArea(ma);
		ma.setWidth(130);
		ma.setHeight(130);
		mScene.attachChild(ma);
		ma.setVisible(false);
		
		mScene.registerUpdateHandler(new TimerHandler(5, new ITimerCallback() 
		{
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) 
			{
				// TODO Auto-generated method stub
				
				SoundFunction.audioPlay = true;
				SoundFunction.playAudio(R.raw.first);
				 
				Functions.parrotWithLetterPath(1, 8, 
						0, 6,  letter1, parrot, MainActivity.CAMERA_WIDTH  , letter1Pos,
						parrotHeight, parrotHeight);
			}
		}));
		
		return mScene;
	}

	public void setCurrentScene(Scene scene)
	{
		mScene = scene;
		getEngine().setScene(mScene);
	}

}
