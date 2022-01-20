package com.slide.a.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.slide.a.word.AdsController;

import java.util.ArrayList;

public class TutorialScreen implements Screen





{
    private OrthographicCamera camera;

    public static final float WORLD_HEIGHT = 240;
    public static final float WORLD_WIDTH = 135;
    private Viewport viewport;
    private Stage stage;

    private EntryPoint game;
    private com.slide.a.word.AdsController adsController;
    float fx = 0;
    boolean passer = false;
    ArrayList<Image> imList;

    float imListHolder, imListPlaceholder;
    ArrayList<Float> imagePos;
    int imageCarousel = 0 ;
    float image1x=0,image2x=140,image3x=140,image4x=140,image5x=140;




    public TutorialScreen(final EntryPoint game, final AdsController adsController){

        this.adsController = adsController;
        this.game = game;

        adsController.hideBannerAd();

        float aspectRatio = (float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        camera = new OrthographicCamera(aspectRatio * WORLD_WIDTH, WORLD_HEIGHT);
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT,camera );
        stage = new Stage(viewport, game.batch);
        stage.getRoot().setColor(1,1,1,fx);

        imList = new ArrayList<Image>();
        imagePos = new ArrayList<Float>();


        imList.add(new Image(game.tut1));
        imList.add(new Image(game.tut2));
        imList.add(new Image(game.tut3));
        imList.add(new Image(game.tut4));
        imList.add(new Image(game.tut5));

        imagePos.add(0f);
        imagePos.add(140f);
        imagePos.add(140f);
        imagePos.add(140f);
        imagePos.add(140f);

        for(Image image : imList){
            image.setSize(135,240);
            image.setPosition(500,0);
            stage.addActor(image);









        }

        imList.get(0).setPosition(0,0);


        stage.addListener(new ActorGestureListener(){


            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {



                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


                if(imageCarousel<4)
                    imageCarousel++;
                else
                    passer  =true;




                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean longPress(Actor actor, float x, float y) {

                passer  =true;

                return super.longPress(actor, x, y);
            }
        });







        Gdx.input.setCatchBackKey(true);
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {


                passer  =true;



                return false;



            }


        };



        InputMultiplexer mp = new InputMultiplexer();
        mp.addProcessor(stage);
        mp.addProcessor(backProcessor);
        Gdx.input.setInputProcessor(mp);


    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act();


        if(fx<=1 && !passer) {
            fx += delta;


        }

        if(passer)
        {fx -= delta*2;}


        if(fx<=0 && passer){
            game.setScreen(new MenuScreenClass(game,adsController));
        }


        stage.getRoot().setColor(1, 1, 1, fx);
        for(int i = 0 ; i<imList.size();i++){




            if(i != imageCarousel)
           imagePos.set(i,140f);
            else
                imagePos.set(i,0f);







        }





        image1x += (imagePos.get(0)  - image1x)*4*delta;
        image2x += (imagePos.get(1)  - image2x)*4*delta;
        image3x += (imagePos.get(2)  - image3x)*4*delta;
        image4x += (imagePos.get(3)  - image4x)*4*delta;
        image5x += (imagePos.get(4)  - image5x)*4*delta;


        imList.get(0).setX(image1x);
        imList.get(1).setX(image2x);
        imList.get(2).setX(image3x);
        imList.get(3).setX(image4x);
        imList.get(4).setX(image5x);






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
