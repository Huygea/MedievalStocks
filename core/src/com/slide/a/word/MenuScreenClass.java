package com.slide.a.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.slide.a.word.AdsController;
import com.slide.a.word.GameScreenClass;

import java.util.TimerTask;



public class MenuScreenClass implements Screen {
    final com.slide.a.word.AdsController adsController;
    final EntryPoint game;
    private OrthographicCamera camera;
    public static final float WORLD_HEIGHT = 240;
    public static final float WORLD_WIDTH = 135;
    private Viewport viewport;
    private Stage stage;

    private GlyphLayout gl;
    private java.util.Timer autoModeTimer;
    boolean transitBool = false;
    boolean stagePass = false;
    boolean stagePassTut = false;
    float fx=0.01f;
    
    public int exitCounter = 0;
    float exitNumerator=1f;
    boolean exiter;
    float exitTimer = 1f;
    GlyphLayout timerGlyph;



    private Image qMarkti;
    Label label;




    Image playImg,bgim,exitim, soundOnim,shareim,logoim,musicim,up,down;


    public MenuScreenClass(final EntryPoint game, final com.slide.a.word.AdsController adsController){



        this.adsController = adsController;
        this.game = game;
        adsController.hideBannerAd();
        float aspectRatio = (float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        camera = new OrthographicCamera(aspectRatio * WORLD_WIDTH, WORLD_HEIGHT);
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT,camera );
        stage = new Stage(viewport, game.batch);
        stage.getRoot().setColor(1,1,1,fx);
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = game.foundWordsBit;
        label = new Label("-",ls);
        label.setWrap(true);







        bgim = new Image(game.ta.createSprite("bgmenu"));
        bgim.setSize(135,240);
        bgim.setPosition(0,0);


        logoim = new Image(game.logotex);
        logoim.setSize(74.1f*1.2f,45f*1.2f);
        logoim.setPosition(135/2-logoim.getWidth()/2,160);


        qMarkti = new Image(game.qMarkt);
        qMarkti.setSize(15,15);




        playImg = new Image(game.ta.createSprite("playButns"));
        playImg.setSize(59.25f,15f);
        playImg.setPosition(10,120);

        up = new Image(game.upArrow);
        down = new Image(game.downArrow);

        up.setSize(10,10);
        down.setSize(10,10);

        up.setPosition(75,122.5f);
        down.setPosition(115,122.5f);

        label.setPosition(91.5f,122.5f);
        label.setSize(30,15);




        timerGlyph = new GlyphLayout();






        exitim = new Image(game.ta.createSprite("exit"));
        exitim.setSize(59.28f, 15f);
        exitim.setPosition(135/2 - exitim.getWidth()/2, playImg.getY()-100);

        shareim = new Image(game.ta.createSprite("share"));
        shareim.setSize(74.1f, 15f);
        shareim.setPosition(135/2-shareim.getWidth()/2, playImg.getY()-25);

        soundOnim = new Image(game.ta.createSprite("soundOn"));
        soundOnim.setSize(111f,15f);
        soundOnim.setPosition(135/2 - soundOnim.getWidth()/2,playImg.getY()-50);


        musicim = new Image( game.musicOn);
        musicim.setSize(111,15);
        musicim.setPosition(135/2-musicim.getWidth()/2,playImg.getY()-75);

        qMarkti.setPosition(135-30,exitim.getY());

        if(!adsController.prefGetbool("music")) {
            musicim.setDrawable(new SpriteDrawable(new Sprite( game.musicOff)));
            musicim.setSize(125.71f, musicim.getHeight());
            musicim.setPosition(135/2 - musicim.getWidth()/2,musicim.getY());

        }
        else {

            musicim.setDrawable(new SpriteDrawable(new Sprite( game.musicOn)));
            musicim.setSize(111,musicim.getHeight());
            musicim.setPosition(135/2 - musicim.getWidth()/2,musicim.getY());

        }


        if(!adsController.prefGetbool("sound")) {
            soundOnim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("soundOff"))));
            soundOnim.setSize(125.71f, soundOnim.getHeight());
            soundOnim.setPosition(135/2 - soundOnim.getWidth()/2,soundOnim.getY());
            adsController.prefSetbool("sound",false);
        }
        else {

            soundOnim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("soundOn"))));
            soundOnim.setSize(111,soundOnim.getHeight());
            soundOnim.setPosition(135/2 - soundOnim.getWidth()/2,soundOnim.getY());
            adsController.prefSetbool("sound",true);
        }

        gl = new GlyphLayout();
        gl.setText(game.foundWordsBit,game.foundWordsBit.toString());
        game.foundWordsBit.getData().setScale(0.08f);

        musicim.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.butClick.play();

                if(adsController.prefGetbool("music")) {
                    musicim.setDrawable(new SpriteDrawable(new Sprite( game.musicOff)));
                    musicim.setSize(125.71f, musicim.getHeight());
                    musicim.setPosition(135/2 - musicim.getWidth()/2,musicim.getY());
                    adsController.prefSetbool("music",false);
                    
                }
                else {

                    musicim.setDrawable(new SpriteDrawable(new Sprite( game.musicOn)));
                    musicim.setSize(111,musicim.getHeight());
                    musicim.setPosition(135/2 - musicim.getWidth()/2,musicim.getY());
                    adsController.prefSetbool("music",true);
                }



                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        playImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.butClick.play();
                if(adsController.prefGetbool("tutorial"))   {
                if (adsController.prefGetinteger("life") > 0) {
                    if (!transitBool) {
                        transitBool = true;
                        adsController.prefSetint("life", adsController.prefGetinteger("life") - 1);
                        stagePass = true;


                    }
                } else {


                    if (!transitBool) {
                        adsController.showVideoAd();


                        if (adsController.prefGetinteger("life") > 0) {
                            transitBool = true;

                            adsController.prefSetint("life", adsController.prefGetinteger("life") - 1);
                            stagePass = true;

                        }


                    }

                }

//To Start a Task:


            }

            else
                {

                    if(!transitBool) {

                        transitBool = true;
                        adsController.prefSetbool("tutorial", true);


                        stagePassTut = true;


                    }
                }



                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


                super.touchUp(event, x, y, pointer, button);
            }
        });


        qMarkti.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(!transitBool) {

                    transitBool = true;



                    stagePassTut = true;


                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        soundOnim.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();
                soundOnim.setOrigin(Align.center);

                if(adsController.prefGetbool("sound")) {
                    soundOnim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("soundOff"))));
                    soundOnim.setSize(125.71f, soundOnim.getHeight());
                    soundOnim.setPosition(135/2 - soundOnim.getWidth()/2,soundOnim.getY());
                    adsController.prefSetbool("sound",false);
                }
                else {

                    soundOnim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("soundOn"))));
                    soundOnim.setSize(111,soundOnim.getHeight());
                    soundOnim.setPosition(135/2 - soundOnim.getWidth()/2,soundOnim.getY());
                    adsController.prefSetbool("sound",true);
                }

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {



                super.touchUp(event, x, y, pointer, button);
            }
        });


        up.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(game.timer<900)
                    game.timer = game.timer+60;


                super.touchUp(event, x, y, pointer, button);
            }
        });

        down.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                if(game.timer>=120)
                    game.timer = game.timer-60;


                super.touchUp(event, x, y, pointer, button);
            }
        });



        Gdx.input.setCatchBackKey(true);


        shareim.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                game.butClick.play();
                adsController.shareGame();



                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        exitim.addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.butClick.play();
                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                exitCounter++;
                exitNumerator = 1;
                super.touchUp(event, x, y, pointer, button);
            }


        });



        gl.setText(game.foundWordsBit,game.foundWordsBit.toString());
        stage.addActor(bgim);
        stage.addActor(playImg);
        stage.addActor(qMarkti);
        stage.addActor(exitim);
        stage.addActor(soundOnim);
        stage.addActor(musicim);
        stage.addActor(shareim);
        stage.addActor(logoim);
        stage.addActor(up);
        stage.addActor(down);
        stage.addActor(label);


        Gdx.input.setInputProcessor(stage);




    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        if(!adsController.prefGetbool("music")){

            game.music.setVolume(0);

        }
        else{
            game.music.setVolume(0.2f);
        }

        if(!adsController.prefGetbool("sound")){

            game.stone.setVolume(0);
            game.explode.setVolume(0);
            game.butClick.setVolume(0);
            game.endBeep.setVolume(0);
            game.startBeep.setVolume(0);
            game.gameOver.setVolume(0);
        }

        else{
            game.stone.setVolume(0.4f);
            game.explode.setVolume(0.6f);
            game.butClick.setVolume(1f);
            game.endBeep.setVolume(1f);
            game.startBeep.setVolume(1f);
            game.gameOver.setVolume(1f);

        }



        if(exitCounter==0)
            exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("exit"))));
        if(exitCounter==1)
            exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("exit1"))));
        if(exitCounter==2)
            exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("exit2"))));
        if(exitCounter==3)
            exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("exit3"))));
        if(exitCounter==4)
            exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("exit4"))));
        if(exitCounter==5) {
            exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("goodbye"))));
            exitim.setSize(103.74f, 15);
            exitim.setPosition(135/2-exitim.getWidth()/2,exitim.getY());

        }


        if(exitCounter == 5){

            exitTimer -= delta;
            if(exitTimer<0){

                Gdx.app.exit();
            }

        }

        if(exitCounter>0 && exitCounter<5)
        {

            exitNumerator -= delta;

            if(exitNumerator<=0){
                exitNumerator = 1f;
                exiter = true;
            }


            if(exiter){
                exiter = false;
                exitCounter--;
            }

        }





        if(stage.getRoot().getColor().a<=0 && stagePass){
            passScreen();
        }
        if(stage.getRoot().getColor().a<=0 && stagePassTut){
            passScreenTut();
        }

        if(stagePass){
            stage.getRoot().setColor(1,1,1,fx);

            fx-=delta*2;
        }
        if(stagePassTut){
            stage.getRoot().setColor(1,1,1,fx);

            fx-=delta*2;
        }


        if(stage.getRoot().getColor().a<1 && !stagePass){
            stage.getRoot().setColor(1,1,1,fx);
            fx+=delta/2;
        }

        timerGlyph.setText(game.foundWordsBit,"TIME\n"+game.timer);
        game.batch.begin();



        game.foundWordsBit.setColor(1,1,1,stage.getRoot().getColor().a);

       // game.foundWordsBit.draw(game.batch,"TIME\n"+game.timer,90,130.5f);
        label.setText("TIME\n"+game.timer);


        if(adsController.prefGetinteger("life")>0){
            gl.setText( game.foundWordsBit,"LIFE: "+String.valueOf(adsController.prefGetinteger("life")));
            game.foundWordsBit.draw(game.batch,"LIFE: "+String.valueOf(adsController.prefGetinteger("life")),WORLD_WIDTH/2-gl.width/2,150);}
        else{
            gl.setText( game.foundWordsBit,"HIT PLAY TO FILL 5 LIVES WITH ADS");
            game.foundWordsBit.draw(game.batch,"HIT PLAY TO FILL 5 LIVES WITH ADS",WORLD_WIDTH/2-gl.width/2,150);}
        game.batch.end();



    }

    public void passScreen(){




                game.setScreen(new GameScreenClass(game,adsController));



    }



    public void passScreenTut(){




                game.setScreen(new TutorialScreen(game,adsController));



    }

    public AdsController getAdsController() {
        return adsController;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
