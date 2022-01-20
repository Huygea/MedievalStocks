package com.slide.a.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.slide.a.word.AdsController;
import com.slide.a.word.EntryPoint;

public class SplashScreen implements Screen {

    private OrthographicCamera camera;

    public static final float WORLD_HEIGHT = 240;
    public static final float WORLD_WIDTH = 135;
    private Viewport viewport;
    private Stage stage;
    private Image splasher;
    private float passTimer = 0;

    boolean stagePass = false;
    boolean alphaPass = false;

    private com.slide.a.word.EntryPoint game;
    private com.slide.a.word.AdsController adsController;

    float fx = 0;

    public  SplashScreen(final EntryPoint game, final AdsController adsController){
        this.game = game;
        this.adsController = adsController;

        adsController.hideBannerAd();

        float aspectRatio = (float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        camera = new OrthographicCamera(aspectRatio * WORLD_WIDTH, WORLD_HEIGHT);
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT,camera );
        stage = new Stage(viewport, game.batch);
        stage.getRoot().setColor(1,1,1,fx);


        splasher = new Image(game.splashScreen);
        stage.getRoot().setColor(1,1,1,0);
        splasher.setSize(135,240);
        splasher.setPosition(0,0);
        stage.addActor(splasher);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act();

        stage.getRoot().setColor(1,1,1,fx);


        if(!alphaPass){
            fx += delta;
        }

        if(fx>=2){
            alphaPass = true;
        }



        if(alphaPass)
        {
            passTimer += delta;
        }
        if(passTimer>=2){
            stagePass = true;
        }
        if(stagePass){
            fx -= delta;
        }
        if(fx<=0 && stagePass){



            if(adsController.prefGetbool("tutorial")){
                adsController.showOrLoadInterstital();
            game.setScreen(new MenuScreenClass(game, adsController));}
            else{
                game.setScreen(new PolicyScreen(game, adsController));

            }
        }


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
