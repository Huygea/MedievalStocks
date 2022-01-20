package com.slide.a.word;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.TimeUtils;
import com.slide.a.word.AdsController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EntryPoint extends Game {
	SpriteBatch batch;
	final com.slide.a.word.AdsController adsController;

	public static ArrayList<String> dictionary4;
	public static ArrayList<String> dictionary5;
	public static ArrayList<String> dictionary6;

	public TextureAtlas ta;
	public  BitmapFont bitmapFont;
	public   BitmapFont foundWordsBit;
	public   BitmapFont beginFont;
	public   BitmapFont scoreText;
	public   BitmapFont timerFont;
	public   BitmapFont scoreFont;
	public   BitmapFont policyBmap;
	public Music music;
	public Music explode;
	public Music stone;
	public Music endBeep;
	public Music gameOver;
	public Music startBeep;
	public Music butClick;

	    public float timer = 240;

	AssetManager assetManager;

	Texture tut1,tut2,tut3,tut4,tut5,logotex,qMarkt,musicOn,musicOff,splashScreen,agree,disagree,blackdot,upArrow,downArrow;





	public EntryPoint(final AdsController adsController ){

		this.adsController = adsController;


	}

	@Override
	public void create () {

	    assetManager = new AssetManager();
	    assetManager.load("Merged.ogg",Music.class);
	    assetManager.load("explos.ogg",Music.class);
	    assetManager.load("stone.ogg",Music.class);
	    assetManager.load("endBeep.ogg",Music.class);
	    assetManager.load("gameOver.ogg",Music.class);
	    assetManager.load("startBeep.ogg",Music.class);
	    assetManager.load("buttonClick.ogg",Music.class);
	    assetManager.load("karma.fnt",BitmapFont.class);
	    assetManager.load("packedSheet.txt",TextureAtlas.class);
	    assetManager.load("tut1.png",Texture.class);
		assetManager.load("tut2.png",Texture.class);
		assetManager.load("tut3.png",Texture.class);
		assetManager.load("tut4.png",Texture.class);
		assetManager.load("tut5.png",Texture.class);
		assetManager.load("logo2d.png",Texture.class);
		assetManager.load("qMark.png",Texture.class);
		assetManager.load("musicOn.png",Texture.class);
		assetManager.load("musicOff.png",Texture.class);
		assetManager.load("splashScreen.png", Texture.class);
		assetManager.load("agree.png", Texture.class);
		assetManager.load("disagree.png", Texture.class);
		assetManager.load("blackdot.png",Texture.class);
		assetManager.load("upArrow.png", Texture.class);
		assetManager.load("downArrow.png", Texture.class);
	    assetManager.finishLoading();


		batch = new SpriteBatch();
		//music = assetManager.get("Merged.ogg"));
		music = (Music)assetManager.get("Merged.ogg");
		music.play();
		music.setVolume(0.2f);
		music.setLooping(true);
		explode = assetManager.get("explos.ogg");		
		stone = assetManager.get("stone.ogg");
		stone.setVolume(0.4f);
		stone.setLooping(true);
		explode.setVolume(0.6f);

		endBeep = assetManager.get("endBeep.ogg");
		gameOver = assetManager.get("gameOver.ogg");
		startBeep = assetManager.get("startBeep.ogg");
		butClick = assetManager.get("buttonClick.ogg");

		endBeep.setLooping(false);
		gameOver.setLooping(false);
		startBeep.setLooping(false);
		butClick.setLooping(false);

		agree = assetManager.get("agree.png");
		disagree = assetManager.get("disagree.png");
		blackdot = assetManager.get("blackdot.png");

		upArrow = assetManager.get("upArrow.png");
		downArrow = assetManager.get("downArrow.png");

		tut1 = assetManager.get("tut1.png");
		tut2 = assetManager.get("tut2.png");
		tut3 = assetManager.get("tut3.png");
		tut4 = assetManager.get("tut4.png");
		tut5 = assetManager.get("tut5.png");
		splashScreen = assetManager.get("splashScreen.png");
		logotex = assetManager.get("logo2d.png");
		qMarkt = assetManager.get("qMark.png");
		musicOn = assetManager.get("musicOn.png");
		musicOff = assetManager.get("musicOff.png");




		if(!adsController.prefGetbool("music")){

			music.setVolume(0);

		}
		else{
			music.setVolume(0.2f);
		}

		if(!adsController.prefGetbool("sound"))
		{

			stone.setVolume(0);
			explode.setVolume(0);
			butClick.setVolume(0);
			endBeep.setVolume(0);
			startBeep.setVolume(0);
			gameOver.setVolume(0);
		}

		else
		{

			stone.setVolume(0.4f);
			explode.setVolume(0.6f);
			butClick.setVolume(1f);
			endBeep.setVolume(1f);
			startBeep.setVolume(1f);
			gameOver.setVolume(1f);

		}

		if(!adsController.prefGetbool("firstplay")){
			adsController.prefSetbool("firstplay", true);
			adsController.prefSetint("life",5);
			adsController.prefSetbool("tutorial",false);
			adsController.prefSetbool("music",true);
			adsController.prefSetbool("sound",true);
		}

		
		bitmapFont = assetManager.get("karma.fnt");
		bitmapFont.setUseIntegerPositions(false);
		bitmapFont.getData().setScale(0.08f);


		beginFont= assetManager.get("karma.fnt");
		beginFont.setUseIntegerPositions(false);
		beginFont.getData().setScale(0.08f);

		policyBmap = assetManager.get("karma.fnt");
		policyBmap.setUseIntegerPositions(false);
		policyBmap.getData().setScale(10f);

		scoreText= assetManager.get("karma.fnt");
		scoreText.setUseIntegerPositions(false);
		scoreText.getData().setScale(0.08f);

		foundWordsBit = assetManager.get("karma.fnt");
		foundWordsBit.setUseIntegerPositions(false);
		foundWordsBit.getData().setScale(0.06f);

		timerFont = assetManager.get("karma.fnt");
		timerFont.setUseIntegerPositions(false);
		timerFont.getData().setScale(0.12f);


		scoreFont = assetManager.get("karma.fnt");
		scoreFont.setUseIntegerPositions(false);
		scoreFont.getData().setScale(0.12f);

		ta = assetManager.get("packedSheet.txt");

        System.out.println("DATE PAAAAAAAAAAAA:   "+ TimeUtils.millis());


		dictionary4 = new ArrayList<String>();
		dictionary5 = new ArrayList<String>();
		dictionary6 = new ArrayList<String>();


        FileHandle handle = Gdx.files.internal("dict4.txt");
        String text = handle.readString();
        String wordsArray[] = text.split("\\r?\\n");
        dictionary4.addAll(Arrays.asList(wordsArray));

        System.out.println(dictionary4.size()+" SIZE DICK4");

		FileHandle handles = Gdx.files.internal("dict5.txt");
		String texts = handles.readString();
		String wordsArrays[] = texts.split("\\r?\\n");
		dictionary5.addAll(Arrays.asList(wordsArrays));

		System.out.println(dictionary5.size()+" SIZE DICK5");

		FileHandle handler = Gdx.files.internal("dict6.txt");
		String textr = handler.readString();
		String wordsArrayr[] = textr.split("\\r?\\n");
		dictionary6.addAll(Arrays.asList(wordsArrayr));

		System.out.println(dictionary6.size()+" SIZE DICK6");

		while(!assetManager.update()) {
			float progress = assetManager.getProgress();
			System.out.println("Loading ... " + progress * 100 + "%");
		}




        this.setScreen(new SplashScreen(this,adsController));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);






		batch.begin();

		batch.end();
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();

	}
}
