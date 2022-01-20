package com.slide.a.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.slide.a.word.AdsController;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import static com.slide.a.word.EntryPoint.dictionary4;
import static com.slide.a.word.EntryPoint.dictionary5;
import static com.slide.a.word.EntryPoint.dictionary6;


public class GameScreenClass implements Screen {



    private  int sitIndex = 0;

   final com.slide.a.word.AdsController adsController;
   final EntryPoint game;
   float fx = 0.01f;





    private OrthographicCamera camera;

    public static final float WORLD_HEIGHT = 240;
    public static final float WORLD_WIDTH = 135;
    private Viewport viewport;
    private Stage stage;

    private  float xhs = 0;
    private  float fa = 1f;
    private  LevelGetter lg;
    private  Vector2 v2;


    boolean stagePass = false;
    boolean replayBool = false;
    boolean menuBool = false;

    private  ArrayList<LetterBox> lettersList1;
    private  ArrayList<LetterBox> lettersList2;
    private ArrayList<LetterBox> lettersList3;
    private  ArrayList<LetterBox> lettersList4;
    private ArrayList<LetterBox> lettersList5;
    private  ArrayList<LetterBox> lettersList6;
    private  ArrayList<Boolean> goldenBoolList;

    private  ArrayList<String> tempStrings;

    private  Random random;
    public static Rectangle mainRect;


    private  ArrayList<String> row1;
    private  ArrayList<String> row2;
    private  ArrayList<String> row3;
    private  ArrayList<String> row4;
    private  ArrayList<String> row5;
    private  ArrayList<String> row6;

    private  ParticleEffect blueBloom;

    private   ArrayList<String> detectedLetter;




    private   Table table1;
    private  Table table2;
    private  Table table3;
    private  Table table4;
    private  Table table5;
    private  Table table6;


    private  float t1 = 1000;
    private  float th1;

    private  float t2 = 1200 ;
    private  float th2;

    private  float t3 =1400;
    private  float th3;

    private  float t4 =1000 ;
    private  float th4;



    private  float t5 =900;
    private  float th5;

    private  float t6 = 1600;
    private  float th6;


    private  String word="";
    private  int score=0;
    private  int shownScore = score;
    private   String foundWordString="";
    private float redder = 1f;





    private   Vector2 newTouch,lastTouchedPos,camMovementVector;

    private  float calculatedy;
    private  boolean moveBool1 = true;
    private  boolean moveBool2 = true;
    private  boolean moveBool3 = true;
    private  boolean moveBool4 = true;
    private  boolean moveBool5 = true;
    private   boolean moveBool6 = true;
    private  boolean scoreDimmer = true;
    private  boolean actorAdder = true;

    private   ArrayList<String> foundWords;



    private  boolean submitBool = false;


    private  float bitmapfontx= 100
            ;
    private  float bitmapfonty = 30;


    //TIMER


    private float timer = 0;



    private  int beginTimer = 5;
    private  float beginFl = 1;
    private  float beginFloat = 0.08f;
    private  boolean beginbool = false;

    private  float yh = 250;

    private   boolean isGold = false;

    private  GlyphLayout gl ;
    private GlyphLayout timerGlyph;
    private  int goldCounter =0;


    private   float pauseTargetY;
    private   float tempY = 500;








    private int exitCounter = 0;
    private  float quitCounter = 2;


    private  ArrayList<LetterBox> timeUp;
    private  int timeUpArr=0;

    private  float scoreScreenTimer = 3f;

    private  float moveUp = -500;
    private  float moveTimerPieces = 200;
    private  float moveUpTemp = 0;
    private  float moveUpTimers = 115;
    private  float scoreY = 180;




    private  float dividervertX = 94f, dividerhorY = 150f;
    private  float divtempx,divtempy;

    private  boolean scoreBool = false;
    private  Label levelLabel;
    private  Label.LabelStyle ls;





    private Table lvlTable;
    private boolean isLevel=false;







  private  Image frameim,bgim,dividerhorim,dividervertim,exitim,sharei,replayi,replaypi,menui, menupi,resButim,soundOnim, pauseIm,hudFrame,musicim;





    public GameScreenClass(final EntryPoint game, final AdsController adsController){


        this.adsController = adsController;
        this.game = game;
        //System.out.println("GAME CLASS''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");

        adsController.showBannerAd();

        lg =new LevelGetter();
        lg.arrayListSetter();



        v2 = new Vector2();

        gl = new GlyphLayout();

        goldenBoolList = new ArrayList<Boolean>();






        timerGlyph = new GlyphLayout();
        timerGlyph.setText(game.timerFont,String.valueOf(timer));
        timerGlyph = new GlyphLayout();
        timerGlyph.setText(game.scoreFont,String.valueOf(timer));



        EnumClass.state = EnumClass.State.STARTING;
        float aspectRatio = (float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        camera = new OrthographicCamera(aspectRatio * WORLD_WIDTH, WORLD_HEIGHT);
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT,camera );
        stage = new Stage(viewport, game.batch);
        stage.getRoot().setColor(1,1,1,fx);

        timer = game.timer;



        game.foundWordsBit.setColor(1,1,1,stage.getRoot().getColor().a);
        game.bitmapFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.beginFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.scoreFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.timerFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.scoreText.setColor(1,1,1,stage.getRoot().getColor().a);


        timeUp = new ArrayList<LetterBox>();

        timeUp.add(new LetterBox("t",game));
        timeUp.add(new LetterBox("i",game));
        timeUp.add(new LetterBox("m",game));
        timeUp.add(new LetterBox("e",game));
        timeUp.add(new LetterBox("u",game));
        timeUp.add(new LetterBox("p",game));



        random = new Random();
        mainRect = new Rectangle();
        mainRect.setSize(135,1);
        mainRect.setPosition(0,125);
        
        foundWords = new ArrayList<String>();
        blueBloom = new ParticleEffect();

        blueBloom = new ParticleEffect();
        blueBloom.load(Gdx.files.internal("blueBloom.party"), Gdx.files.internal(""));
        blueBloom.getEmitters().first().setPosition(stage.getWidth()/2,stage.getHeight()/2);
        blueBloom.scaleEffect(0.2f);
      //Texture and Image Decleration
        newTouch= new Vector2(0,0);
        lastTouchedPos = new Vector2(0,0);
        camMovementVector = new Vector2(0,0);



        frameim = new Image(game.ta.createSprite("frame"));
        frameim.setPosition(17f,500);
        frameim.setSize(102f,15);



        pauseIm = new Image(game.ta.createSprite("bgmenu"));
        pauseIm.setSize(138,240);
        pauseIm.setPosition(135/2 - pauseIm.getWidth()/2, 500);
        pauseTargetY = 240/2 - pauseIm.getHeight()/2;





        dividerhorim = new Image(game.ta.createSprite("dividerhor"));
        dividerhorim.setSize(96,3);
        dividerhorim.setPosition(0,-200);


        dividervertim = new Image(game.ta.createSprite("dividervert"));
        dividervertim.setSize(3,240);
        dividervertim.setPosition(-200,0);

        ls =new Label.LabelStyle();
        ls.font = game.bitmapFont;

        levelLabel = new Label("123",ls);










        lvlTable = new Table();
        lvlTable.add(levelLabel).center();
        lvlTable.setPosition(0,500);


        sharei = new Image(game.ta.createSprite("share"));
        sharei.setSize(49.4f,10);
        sharei.setPosition(0,-300);


        replayi = new Image(game.ta.createSprite("replay"));
        replayi.setSize(59.3f,10);
        replayi.setPosition(0,-300);


        replaypi = new Image(game.ta.createSprite("replay"));
        replaypi.setSize(88.95f,15);
        replaypi.setPosition(0,-300);




        menui = new Image(game.ta.createSprite("menu"));
        menui.setSize(39.6f,10);
        menui.setPosition(0,600);

        menupi = new Image(game.ta.createSprite("menu"));
        menupi.setSize(59.4f,15);
        menupi.setPosition(0,600);



        resButim = new Image(game.ta.createSprite("resumeButns"));
        resButim.setSize(90,15);
        resButim.setPosition(135/2-resButim.getWidth()/2,500);




        exitim = new Image();
        exitim.setSize(59.28f,15);
        exitim.setPosition(135/2-exitim.getWidth()/2,600);
        exitim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("exit"))));

        soundOnim = new Image();
        soundOnim.setDrawable(new SpriteDrawable(new Sprite(game.ta.createSprite("soundOn"))));
        soundOnim.setSize(111f,15f);
        soundOnim.setPosition(135/2-soundOnim.getWidth()/2, 600);


        musicim = new Image( game.musicOn);
        musicim.setSize(111,15);
        musicim.setPosition(135/2-musicim.getWidth()/2,600);



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




        hudFrame = new Image(game.ta.createSprite("hudFrame"));
        hudFrame.setSize(135,48.075f);
        hudFrame.setPosition(0,-5);


        bgim = new Image(game.ta.createSprite("bgmenu"));
        bgim.setSize(135,240);
        bgim.setPosition(0,0);



        pauseIm.setPosition(135/2 - pauseIm.getWidth()/2, tempY);
        resButim.setPosition(resButim.getX(),pauseIm.getY()+30);

        stage.addActor(bgim);











        table1 = new Table();
        table2 = new Table();
        table3 = new Table();
        table4 = new Table();
        table5 = new Table();
        table6 = new Table();





        lettersList1 = new ArrayList<LetterBox>();
        lettersList2 = new ArrayList<LetterBox>();
        lettersList3 = new ArrayList<LetterBox>();
        lettersList4 = new ArrayList<LetterBox>();
        lettersList5 = new ArrayList<LetterBox>();
        lettersList6 = new ArrayList<LetterBox>();

        detectedLetter = new ArrayList<String>();
        for(int i = 0;i<6;i++){
            detectedLetter.add("a");
        }

        row1 = new ArrayList<String>();
        row2 = new ArrayList<String>();
        row3 = new ArrayList<String>();
        row4 = new ArrayList<String>();
        row5 = new ArrayList<String>();
        row6 = new ArrayList<String>();

        tempStrings  = new ArrayList<String>();




        tempStrings.add(EntryPoint.dictionary4.get(random.nextInt(EntryPoint.dictionary4.size())));
        tempStrings.add(EntryPoint.dictionary4.get(random.nextInt(EntryPoint.dictionary4.size())));
        tempStrings.add(EntryPoint.dictionary4.get(random.nextInt(EntryPoint.dictionary4.size())));
        tempStrings.add(EntryPoint.dictionary4.get(random.nextInt(EntryPoint.dictionary4.size())));


        tempStrings.add(EntryPoint.dictionary5.get(random.nextInt(EntryPoint.dictionary5.size())));
        tempStrings.add(EntryPoint.dictionary5.get(random.nextInt(EntryPoint.dictionary5.size())));
        tempStrings.add(EntryPoint.dictionary5.get(random.nextInt(EntryPoint.dictionary5.size())));


        tempStrings.add(EntryPoint.dictionary6.get(random.nextInt(EntryPoint.dictionary6.size())));
        tempStrings.add(EntryPoint.dictionary6.get(random.nextInt(EntryPoint.dictionary6.size())));
        tempStrings.add(EntryPoint.dictionary6.get(random.nextInt(EntryPoint.dictionary6.size())));
        tempStrings.add(EntryPoint.dictionary6.get(random.nextInt(EntryPoint.dictionary6.size())));






        for(int i = 0;i<tempStrings.size();i++){
            
            if(tempStrings.get(i).length()==4)
            {
                for(int j = 0;j<4;j++){

                    if(j==0 && !row1.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row1.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==1 && !row2.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row2.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==2 && !row3.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row3.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==3 && !row4.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row4.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    
                }
                
                
            }

            if(tempStrings.get(i).length()==5)
            {

                for(int j = 0;j<5;j++){

                    if(j==0 && !row1.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row1.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==1 && !row2.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row2.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==2 && !row3.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row3.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==3 && !row4.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row4.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==4 && !row5.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row5.add((String.valueOf(tempStrings.get(i).charAt(j))));}

                }






            }

            if(tempStrings.get(i).length()==6)
            {

                for(int j = 0;j<6;j++){

                    if(j==0 && !row1.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row1.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==1 && !row2.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row2.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==2 && !row3.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row3.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==3 && !row4.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row4.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==4 && !row5.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row5.add((String.valueOf(tempStrings.get(i).charAt(j))));}
                    if(j==5 && !row6.contains(String.valueOf(tempStrings.get(i).charAt(j))))
                    { row6.add((String.valueOf(tempStrings.get(i).charAt(j))));}

                }


            }
            
          
        }

        Collections.shuffle(row1);
        Collections.shuffle(row2);
        Collections.shuffle(row3);
        Collections.shuffle(row4);
        Collections.shuffle(row5);
        Collections.shuffle(row6);


        for(int k = 0 ; k< row1.size(); k++){

            //System.out.println(row1.get(k) + "  ROW 1");

            
            lettersList1.add(new LetterBox(row1.get(k),game));
            lettersList1.get(k).goldNumber = goldCounter;
            goldCounter++;
            goldenBoolList.add(lettersList1.get(k).isGolden);


        }
        for(int k = 0 ; k< row2.size(); k++){

            //System.out.println(row2.get(k) + "  ROW 2");
            lettersList2.add(new LetterBox(row2.get(k),game));
            lettersList2.get(k).goldNumber = goldCounter;
            goldCounter++;
            goldenBoolList.add(lettersList2.get(k).isGolden);
        }
        for(int k = 0 ; k< row3.size(); k++){

            //System.out.println(row3.get(k) + "  ROW 3");
            lettersList3.add(new LetterBox(row3.get(k),game));
            lettersList3.get(k).goldNumber = goldCounter;
            goldCounter++;
            goldenBoolList.add(lettersList3.get(k).isGolden);
        }

        for(int k = 0 ; k< row4.size(); k++){

            //System.out.println(row4.get(k) + "  ROW 4");
            lettersList4.add(new LetterBox(row4.get(k),game));
            lettersList4.get(k).goldNumber = goldCounter;
            goldCounter++;
            goldenBoolList.add(lettersList4.get(k).isGolden);
        }

        for(int k = 0 ; k< row5.size(); k++){

            //System.out.println(row5.get(k) + "  ROW 5");
            lettersList5.add(new LetterBox(row5.get(k),game));
            lettersList5.get(k).goldNumber = goldCounter;
            goldCounter++;
            goldenBoolList.add(lettersList5.get(k).isGolden);
        }

        for(int k = 0 ; k< row6.size(); k++){

            //System.out.println(row6.get(k) + "  ROW 6");
            lettersList6.add(new LetterBox(row6.get(k),game));
            lettersList6.get(k).goldNumber = goldCounter;
            goldCounter++;
            goldenBoolList.add(lettersList6.get(k).isGolden);
        }


        for (LetterBox letterbox:lettersList1
             ) {
            table1.add(letterbox.render()).width(15).height(15);
            table1.row();
        }







        table1.setPosition(25f,t1);
        table1.addListener(new ActorGestureListener() {


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

              game.stone.stop();








                moveBool1 = true;
                submitBool  =true;


                if (lettersList1.size()%2==1){

                    calculatedy = Math.round((table1.getY() - 2f)/15);

                    th1 = (calculatedy * 15) + 3f ;

                }

                else{

                    calculatedy = Math.round((table1.getY() - 10)/15);

                    th1 = (calculatedy * 15) + 10 ;

                }





            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

                table1.setPosition(table1.getX(),table1.getY()+deltaY*0.8f);
                t1 = table1.getY();


                super.pan(event, x, y, deltaX, deltaY);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {


                game.stone.play();
                lastTouchedPos.set(x, y);

                moveBool1 = false;
                submitBool = false;


                           }


        });





        stage.addActor(table1);


        for (LetterBox letterbox:lettersList2
                ) {
            table2.add(letterbox.render()).width(15).height(15);
            table2.row();
        }
        table2.setPosition(25f+17,t2);


        table2.addListener(new ActorGestureListener() {


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {




                game.stone.stop();


                moveBool2 = true;
                submitBool = true;


                if (lettersList2.size()%2==1){

                    calculatedy = Math.round((table2.getY() - 2f)/15);

                    th2 = (calculatedy * 15) + 3f ;

                }

                else{

                    calculatedy = Math.round((table2.getY() - 10)/15);

                    th2 = (calculatedy * 15) + 10 ;

                }





            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

                table2.setPosition(table2.getX(),table2.getY()+deltaY*0.8f);
                t2 = table2.getY();


                super.pan(event, x, y, deltaX, deltaY);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.play();


                lastTouchedPos.set(x, y);

                moveBool2 = false;
                submitBool = false;



            }


        });


        stage.addActor(table2);


        for (LetterBox letterbox:lettersList3
                ) {
            table3.add(letterbox.render()).width(15).height(15);
            table3.row();
        }
        table3.setPosition(25f+34,t3);
        table3.addListener(new ActorGestureListener() {


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.stop();






                moveBool3 = true;
                submitBool = true;


                if (lettersList3.size()%2==1){

                    calculatedy = Math.round((table3.getY() - 2f)/15);

                    th3 = (calculatedy * 15) + 3f ;

                }

                else{

                    calculatedy = Math.round((table3.getY() - 10)/15);

                    th3 = (calculatedy * 15) + 10 ;

                }





            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

                table3.setPosition(table3.getX(),table3.getY()+deltaY*0.8f);
                t3 = table3.getY();


                super.pan(event, x, y, deltaX, deltaY);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.play();


                lastTouchedPos.set(x, y);

                moveBool3 = false;
                submitBool = false;



            }


        });
        stage.addActor(table3);

        for (LetterBox letterbox:lettersList4
                ) {
            table4.add(letterbox.render()).width(15).height(15);
            table4.row();
        }
        table4.setPosition(25f+51,t4);
        table4.addListener(new ActorGestureListener() {


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.stop();





                moveBool4 = true;
                submitBool = true;


                if (lettersList4.size()%2==1){

                    calculatedy = Math.round((table4.getY() - 2f)/15);

                    th4 = (calculatedy * 15) + 3f ;

                }

                else{

                    calculatedy = Math.round((table4.getY() - 10)/15);

                    th4 = (calculatedy * 15) + 10 ;

                }





            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

                table4.setPosition(table4.getX(),table4.getY()+deltaY*0.8f);
                t4 = table4.getY();


                super.pan(event, x, y, deltaX, deltaY);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.play();


                lastTouchedPos.set(x, y);

                moveBool4 = false;
                submitBool = false;



            }


        });
        stage.addActor(table4);

        for (LetterBox letterbox:lettersList5
                ) {
            table5.add(letterbox.render()).width(15).height(15);
            table5.row();
        }
        table5.setPosition(25f+68,t5);
        table5.addListener(new ActorGestureListener() {


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.stop();





                moveBool5 = true;
                submitBool  =true;


                if (lettersList5.size()%2==1){

                    calculatedy = Math.round((table5.getY() - 2f)/15);

                    th5 = (calculatedy * 15) + 3f ;

                }

                else{

                    calculatedy = Math.round((table5.getY() - 10)/15);

                    th5 = (calculatedy * 15) + 10 ;

                }





            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

                table5.setPosition(table5.getX(),table5.getY()+deltaY*0.8f);
                t5 = table5.getY();


                super.pan(event, x, y, deltaX, deltaY);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.play();


                lastTouchedPos.set(x, y);

                moveBool5 = false;
                submitBool = false;



            }


        });





        stage.addActor(table5);


        for (LetterBox letterbox:lettersList6
                ) {
            table6.add(letterbox.render()).width(15).height(15);
            table6.row();
        }
        table6.setPosition(25f+85,t6);
        table6.addListener(new ActorGestureListener() {


            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.stop();





                moveBool6 = true;
                submitBool = true;


                if (lettersList6.size()%2==1){

                    calculatedy = Math.round((table6.getY() - 2f)/15);

                    th6 = (calculatedy * 15) + 3f ;

                }

                else{

                    calculatedy = Math.round((table6.getY() - 10)/15);

                    th6 = (calculatedy * 15) + 10 ;

                }





            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

                table6.setPosition(table6.getX(),table6.getY()+deltaY*0.8f);
                t6 = table6.getY();


                super.pan(event, x, y, deltaX, deltaY);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.stone.play();


                lastTouchedPos.set(x, y);

                moveBool6 = false;
                submitBool  =false;



            }


        });
        stage.addActor(table6);


        ArrangeTables();

        stage.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                {
                    if (count == 2) {
                        System.out.println("Double tap!");
                        SubmitActions();
                    }

                }
            }

        });


        replayi.addListener(new ActorGestureListener(){

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if(count == 2){

                    Timer.instance().scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            if(adsController.prefGetinteger("life")>0){
                                adsController.prefSetint("life", adsController.prefGetinteger("life")-1);



                                stagePass = true;replayBool = true;
                            }
                            else{

                                adsController.showVideoAd();


                                if(adsController.prefGetinteger("life")>0){
                                adsController.prefSetint("life", adsController.prefGetinteger("life")-1);



                                stagePass = true;replayBool = true;}

                            }

                        }
                    }, 0);
                }

                else{

                    adsController.toastMaker("Double tap to replay.");
                }
                super.tap(event, x, y, count, button);
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {



                game.butClick.play();



                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        menupi.addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();
                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {

                if(count == 2){

                    Timer.instance().scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            stagePass = true;
                            menuBool = true;

                        }
                    }, 0);


                }

                else{
                    adsController.toastMaker("Double tap to return to menu.");
                }


                super.tap(event, x, y, count, button);
            }
        });


        menui.addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();
                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {

                if(count == 2){

                    Timer.instance().scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            stagePass = true;
                            menuBool = true;

                        }
                    }, 0);


                }

                else{
                    adsController.toastMaker("Double tap to return to menu.");
                }


                super.tap(event, x, y, count, button);
            }
        });



        replaypi.addListener(new ActorGestureListener(){

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if(count == 2){

                    Timer.instance().scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {

                            if(adsController.prefGetinteger("life")>0){
                                adsController.prefSetint("life", adsController.prefGetinteger("life")-1);



                                replayBool = true;
                                stagePass = true;
                            }
                            else{

                                adsController.showVideoAd();



                                if(adsController.prefGetinteger("life")>0) {
                                    adsController.prefSetint("life", adsController.prefGetinteger("life") - 1);


                                    replayBool = true;
                                    stagePass = true;
                                }
                            }

                        }
                    }, 0);
                }

                else{

                    adsController.toastMaker("Double tap to replay.");
                }



            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();
                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        sharei.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();

                adsController.share(score,isGold,game.timer);

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });



        resButim.addListener(new InputListener(){


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();


                if(exitCounter!=5) {


                    removePause();


                    switch (sitIndex) {
                        case 0: {
                            EnumClass.state = EnumClass.State.STARTING;
                            break;
                        }
                        case 1: {
                            EnumClass.state = EnumClass.State.RUN;
                            break;
                        }
                        case 2:


                        {

                            EnumClass.state = EnumClass.State.PAUSE;


                            break;
                        }

                        case 3: {
                            EnumClass.state = EnumClass.State.SCORESCREEN;
                            break;
                        }
                        case 4: {
                            break;
                        }
                        case 5: {
                            break;
                        }

                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {






                super.touchUp(event, x, y, pointer, button);
            }
        });

        exitim.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.butClick.play();

                if(exitCounter<5)
                exitCounter++;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

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



        Gdx.input.setCatchBackKey(true);
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                if(exitCounter!=5) {
                    if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK))

                        if (EnumClass.state != EnumClass.State.PAUSE) {
                            EnumClass.state = EnumClass.State.PAUSE;


                            addPause();

                        } else {


                            removePause();
                            switch (sitIndex) {
                                case 0: {
                                    EnumClass.state = EnumClass.State.STARTING;
                                    break;
                                }
                                case 1: {
                                    EnumClass.state = EnumClass.State.RUN;
                                    break;
                                }
                                case 2:


                                {

                                    EnumClass.state = EnumClass.State.PAUSE;


                                    break;
                                }

                                case 3: {
                                    EnumClass.state = EnumClass.State.SCORESCREEN;
                                    break;
                                }
                                case 4: {
                                    break;
                                }
                                case 5: {
                                    break;
                                }

                            }

                        }
                }
                return false;



            }


        };




        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(stage);

        im.addProcessor(backProcessor);
       Gdx.input.setInputProcessor(im);
        for (LetterBox timeLetter: timeUp
                ) {

            timeLetter.render().setPosition(18+(17*timeUpArr),300+timeUpArr*30);
            timeUpArr++;
            stage.addActor(timeLetter.render());

        }

        stage.addActor(frameim);
        hudFrame.setTouchable(Touchable.disabled);
        frameim.setTouchable(Touchable.disabled);
        stage.addActor(hudFrame);




        stage.addActor(sharei);
        stage.addActor(menui);
        stage.addActor(replayi);

        stage.addActor(dividervertim);
        stage.addActor(dividerhorim);
        stage.addActor(lvlTable);
        stage.addActor(pauseIm);
        stage.addActor(resButim);
        stage.addActor(soundOnim);
        stage.addActor(musicim);
        stage.addActor(exitim);
        stage.addActor(replaypi);
        stage.addActor(menupi);



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();

        if(!adsController.prefGetbool("music")){

            game.music.setVolume(0);

        }
        else{
            game.music.setVolume(0.2f);
        }

        if(!adsController.prefGetbool("sound"))
        {

            game.stone.setVolume(0);
            game.explode.setVolume(0);
            game.butClick.setVolume(0);
            game.endBeep.setVolume(0);
            game.startBeep.setVolume(0);
            game.gameOver.setVolume(0);
        }

        else
            {

                game.stone.setVolume(0.4f);
                game.explode.setVolume(0.6f);
                game.butClick.setVolume(1f);
                game.endBeep.setVolume(1f);
                game.startBeep.setVolume(1f);
                game.gameOver.setVolume(1f);

        }






        switch (EnumClass.state) {

            case RUN: {

                actorAdder = true;
                sitIndex = 1;

                if (timer > 0)
                    timer = timer - delta;




                if (moveBool1) {
                    t1 += (th1 - t1) * 4 * delta;
                    table1.setPosition(table1.getX(), t1);


                }

                if (moveBool2) {
                    t2 += (th2 - t2) * 4 * delta;
                    table2.setPosition(table2.getX(), t2);
                }

                if (moveBool3) {
                    t3 += (th3 - t3) * 4 * delta;
                    table3.setPosition(table3.getX(), t3);
                }

                if (moveBool4) {
                    t4 += (th4 - t4) * 4 * delta;
                    table4.setPosition(table4.getX(), t4);
                }


                if (moveBool5) {
                    t5 += (th5 - t5) * 4 * delta;
                    table5.setPosition(table5.getX(), t5);
                }


                if (moveBool6) {
                    t6 += (th6 - t6) * 4 * delta;
                    table6.setPosition(table6.getX(), t6);
                }

                if(frameim.getY()>115f)
                    frameim.setY(frameim.getY()-7f);

                else{
                    frameim.setY(115f);
                }


                break;
            }
            case PAUSE: {

                addPause();

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

                    exitim.setSize(103.71f,15);
                    exitim.setPosition(135/2-exitim.getWidth()/2,exitim.getY());

                    quitCounter-=delta;

                    if(quitCounter<=0){

                        Gdx.app.exit();
                    }


                }

                break;
            }
            case RESUME: {

                break;
            }
            case STOPPED: {

                break;
            }
            case STARTING: {
                actorAdder = true;

                beginFl -= delta;

                if (beginFl > 0) {

                    if (beginFl > 0.5f)
                        beginFloat += delta / 16;
                    else
                        beginFloat -= delta / 16;


                    game.beginFont.getData().setScale(beginFloat);

                } else
                    beginFloat = 0.08f;
                if (beginFl < 0) {
                    beginbool = true;
                    beginFl = 1;

                }
                if (beginbool) {
                    beginTimer -= 1;
                    if(beginTimer>=0)
                        game.startBeep.play();
                    beginbool = false;


                }

                if (beginTimer < 0) {

                    sitIndex = 1;
                    EnumClass.state = EnumClass.State.RUN;
                }


                break;
            }
            case SCORESCREEN: {




                if(scoreBool){

                    if(adsController.prefGetinteger("level") != lg.LevelReturn(adsController.prefGetinteger("cumscore")+score)){

                        isLevel =true;

                    }

                    if(!isGold){
                        adsController.prefSetint("level", lg.LevelReturn(adsController.prefGetinteger("cumscore")+score));
                        adsController.prefSetint("cumscore",adsController.prefGetinteger("cumscore")+ score);

                    }
                    else{
                        adsController.prefSetint("level", lg.LevelReturn(adsController.prefGetinteger("cumscore")+score*2));
                        adsController.prefSetint("cumscore",adsController.prefGetinteger("cumscore")+ score*2);

                    }



                    scoreBool = false;


                }





                if(!isLevel) {
                    levelLabel.setText(String.valueOf("TOTAL EXPERIENCE\n" + adsController.prefGetinteger("cumscore")+" / "+lg.lvlRoof(adsController.prefGetinteger("level")) + "\n")
                            + "LEVEL\n" + adsController.prefGetinteger("level"));

                    levelLabel.setAlignment(Align.center);
                    levelLabel.setOrigin(Align.center);

                }

                else{

                    levelLabel.setColor(1, 0.8f, 0.4f, 1);
                    levelLabel.setText(String.valueOf("TOTAL EXPERIENCE\n" + adsController.prefGetinteger("cumscore")+" / "+lg.lvlRoof(adsController.prefGetinteger("level")) + "\n")
                            + "LEVEL UP\n" + adsController.prefGetinteger("level"));

                    levelLabel.setAlignment(Align.center);
                    levelLabel.setOrigin(Align.center);


                }



                for(LetterBox letterb : timeUp){



                    if(letterb.render().getY()>115f) {
                        letterb.render().setY(letterb.render().getY() - 5f);
                    }

                    else
                    {


                        scoreScreenTimer = scoreScreenTimer - delta/6;
                    }



                  //  timeUpTemp+= (timeUpY - timeUpTemp) * delta/3;
                    //letterb.render().setPosition( letterb.render().getX(), timeUpTemp);


                }

                if(scoreScreenTimer<=0){


                    frameim.setY(moveUpTemp);
                    hudFrame.setY(moveUpTemp);
                   // bgim.setY(moveUpTemp);



                    for (int i = 0; i<timeUp.size();i++
                         ) {

                        timeUp.get(i).render().setY(moveUpTimers);
                        if(160/timeUp.get(i).render().getY()<1)
                            timeUp.get(i).render().setScale(160/timeUp.get(i).render().getY());


                    }

                    for(int i =0; i<timeUp.size();i++){
                        if(timeUp.get(5).render().getX()>80 && 160/timeUp.get(0).render().getY()<1) {
                            timeUp.get(i).render().setX(timeUp.get(i).render().getX() - ((0.05f * i + 0.05f)));

                            timeUp.get(i).render().setX(timeUp.get(i).render().getX()-0.3f);
                        }




                    }



                        divtempx += (dividervertX - divtempx)*delta*2;
                        divtempy += (dividerhorY - divtempy) * delta*2;

                        dividervertim.setPosition(divtempx,0);
                        dividerhorim.setPosition(0,divtempy);

                        lvlTable.setPosition(divtempx/2-lvlTable.getWidth()/2,divtempy/2-lvlTable.getHeight()/2+40);

                        sharei.setPosition(divtempx/2-sharei.getWidth()/2,divtempy/2-sharei.getHeight()/2);


                    menui.setPosition(divtempx/2-menui.getWidth()/2,divtempy/2-menui.getHeight()/2-30);
                    replayi.setPosition(divtempx/2-replayi.getWidth()/2,divtempy/2-replayi.getHeight()/2-15);

                   // levelLabel.setPosition(30, 30);






                    moveUpTemp += (moveUp - moveUpTemp)*delta;
                    moveUpTimers += (moveTimerPieces - moveUpTimers)*delta*2;



                }


                th1=-300;
                th2=-300;
                th3=-300;
                th4=-300;
                th5=-300;
                th6=-300;




                    t1 += (th1 - t1) * 1 * delta;
                    table1.setPosition(table1.getX(), t1);





                    t2 += (th2 - t2) * 1 * delta;
                    table2.setPosition(table2.getX(), t2);



                    t3 += (th3 - t3) * 1 * delta;
                    table3.setPosition(table3.getX(), t3);



                    t4 += (th4 - t4) * 1 * delta;
                    table4.setPosition(table4.getX(), t4);




                    t5 += (th5 - t5) * 1 * delta;
                    table5.setPosition(table5.getX(), t5);




                    t6 += (th6 - t6) * 1 * delta;
                    table6.setPosition(table6.getX(), t6);


                    if(table1.getY()<-200){
                        table1.remove();table2.remove();table3.remove();table4.remove();table5.remove();table6.remove();
                    }




                break;
            }
        }



        for(int i = 0 ; i<lettersList1.size();i++){
            lettersList1.get(i).update(delta);
            if(getStageLocation(lettersList1.get(i).render(), table1).y < 120 && getStageLocation(lettersList1.get(i).render(), table1).y > 110)
            {lettersList1.get(i).isOverlap = true;}
            else
                lettersList1.get(i).isOverlap = false;
        }
        for(int i = 0 ; i<lettersList2.size();i++){
            lettersList2.get(i).update(delta);
            if(getStageLocation(lettersList2.get(i).render(), table2).y < 120 && getStageLocation(lettersList2.get(i).render(), table2).y > 110)
            {lettersList2.get(i).isOverlap = true;}
            else
                lettersList2.get(i).isOverlap = false;
        }
        for(int i = 0 ; i<lettersList3.size();i++){
            lettersList3.get(i).update(delta);
            if(getStageLocation(lettersList3.get(i).render(), table3).y < 120 && getStageLocation(lettersList3.get(i).render(), table3).y > 110)
            {lettersList3.get(i).isOverlap = true;}
            else
                lettersList3.get(i).isOverlap = false;
        }
        for(int i = 0 ; i<lettersList4.size();i++){
            lettersList4.get(i).update(delta);
            if(getStageLocation(lettersList4.get(i).render(), table4).y < 120 && getStageLocation(lettersList4.get(i).render(), table4).y > 110)
            {lettersList4.get(i).isOverlap = true;}
            else
                lettersList4.get(i).isOverlap = false;
        }
        for(int i = 0 ; i<lettersList5.size();i++){
            lettersList5.get(i).update(delta);
            if(getStageLocation(lettersList5.get(i).render(), table5).y < 120 && getStageLocation(lettersList5.get(i).render(), table5).y > 110)
            {lettersList5.get(i).isOverlap = true;}
            else
                lettersList5.get(i).isOverlap = false;
        }
        for(int i = 0 ; i<lettersList6.size();i++){
            lettersList6.get(i).update(delta);
            if(getStageLocation(lettersList6.get(i).render(), table6).y < 120 && getStageLocation(lettersList6.get(i).render(), table6).y > 110)
            {lettersList6.get(i).isOverlap = true;}
            else
                lettersList6.get(i).isOverlap = false;
        }

/*
        for (LetterBox letterBox : lettersList1
                ) {


            letterBox.update(Gdx.graphics.getDeltaTime());


            if (getStageLocation(letterBox.render(), table1).y < 120 && getStageLocation(letterBox.render(), table1).y > 110)
                letterBox.isOverlap = true;
            else
                letterBox.isOverlap = false;


        }

        for (LetterBox letterBox : lettersList2
                ) {


            if (getStageLocation(letterBox.render(), table2).y < 120 && getStageLocation(letterBox.render(), table2).y > 110)
                letterBox.isOverlap = true;
            else
                letterBox.isOverlap = false;


            letterBox.update(Gdx.graphics.getDeltaTime());


        }

        for (LetterBox letterBox : lettersList3
                ) {


            if (getStageLocation(letterBox.render(), table3).y < 120 && getStageLocation(letterBox.render(), table3).y > 110)
                letterBox.isOverlap = true;
            else
                letterBox.isOverlap = false;


            letterBox.update(Gdx.graphics.getDeltaTime());


        }

        for (LetterBox letterBox : lettersList4
                ) {

            if (getStageLocation(letterBox.render(), table4).y < 120 && getStageLocation(letterBox.render(), table4).y > 110)
                letterBox.isOverlap = true;
            else
                letterBox.isOverlap = false;


            letterBox.update(Gdx.graphics.getDeltaTime());


        }

        for (LetterBox letterBox : lettersList5
                ) {

            if (getStageLocation(letterBox.render(), table5).y < 120 && getStageLocation(letterBox.render(), table5).y > 110)
                letterBox.isOverlap = true;
            else
                letterBox.isOverlap = false;


            letterBox.update(Gdx.graphics.getDeltaTime());


        }

        for (LetterBox letterBox : lettersList6
                ) {

            if (getStageLocation(letterBox.render(), table6).y < 120 && getStageLocation(letterBox.render(), table6).y > 110)
                letterBox.isOverlap = true;
            else
                letterBox.isOverlap = false;


            letterBox.update(Gdx.graphics.getDeltaTime());


        }

*/

 {
    if (timer > 1) {
        //LETTER OVER DETECTION

        for (LetterBox letterBox : lettersList1
                ) {




            if (!letterBox.DetectLetter().equals("0")) {

                detectedLetter.set(0, letterBox.DetectLetter());
                break;

            } else {

                detectedLetter.set(0, letterBox.DetectLetter());

            }

        }


        for (LetterBox letterBox : lettersList2
                ) {



            if (!letterBox.DetectLetter().equals("0")) {

                detectedLetter.set(1, letterBox.DetectLetter());
                break;

            } else {

                detectedLetter.set(1, letterBox.DetectLetter());

            }


        }
        for (LetterBox letterBox : lettersList3
                ) {


            if (!letterBox.DetectLetter().equals("0")) {

                detectedLetter.set(2, letterBox.DetectLetter());
                break;

            } else {

                detectedLetter.set(2, letterBox.DetectLetter());

            }


        }
        for (LetterBox letterBox : lettersList4
                ) {


            if (!letterBox.DetectLetter().equals("0")) {

                detectedLetter.set(3, letterBox.DetectLetter());
                break;

            } else {

                detectedLetter.set(3, letterBox.DetectLetter());

            }

        }
        for (LetterBox letterBox : lettersList5
                ) {


            if (!letterBox.DetectLetter().equals("0")) {

                detectedLetter.set(4, letterBox.DetectLetter());
                break;

            } else {

                detectedLetter.set(4, letterBox.DetectLetter());

            }

        }
        for (LetterBox letterBox : lettersList6
                ) {


            if (!letterBox.DetectLetter().equals("0")) {

                detectedLetter.set(5, letterBox.DetectLetter());
                break;

            } else {

                detectedLetter.set(5, letterBox.DetectLetter());

            }


        }




    }


}


        blueBloom.update(Gdx.graphics.getDeltaTime());

        if(timer<1 && (EnumClass.state != EnumClass.State.SCORESCREEN && EnumClass.state != EnumClass.State.PAUSE)) {
            scoreBool = true;
            game.gameOver.play();
            EnumClass.state = EnumClass.State.SCORESCREEN;
            sitIndex = 3;
            timer = 0;

        }

        game.batch.begin();


        if(EnumClass.state == EnumClass.State.SCORESCREEN)
        {
            if(adsController.prefGetinteger("life")>0){
                gl.setText(game.beginFont,"LIFE: "+String.valueOf(adsController.prefGetinteger("life")));
                game.beginFont.draw(game.batch,"LIFE: "+String.valueOf(adsController.prefGetinteger("life")),divtempx/2-gl.width/2,divtempy/2-gl.height/2-45);}
            else{
                gl.setText(game.beginFont,"LIFE: WATCH AN AD");
                game.beginFont.draw(game.batch,"LIFE: WATCH AN AD",divtempx/2-gl.width/2,divtempy/2-gl.height/2-45);}
        }

        if(EnumClass.state == EnumClass.State.PAUSE)
        {



            if(adsController.prefGetinteger("life")>0){
                gl.setText(game.beginFont,"LIFE: "+String.valueOf(adsController.prefGetinteger("life")));
                game.beginFont.draw(game.batch,"LIFE: "+String.valueOf(adsController.prefGetinteger("life")),135/2-gl.width/2,pauseIm.getY()+50);}
            else{
                gl.setText(game.beginFont,"LIFE: WATCH AN AD");
                game.beginFont.draw(game.batch,"LIFE: WATCH AN AD",135/2-gl.width/2,pauseIm.getY()+50);}
        }

        blueBloom.draw(game.batch);
        if (shownScore != score) {

            shownScore += 10;
            xhs++;

            game.bitmapFont.getData().setScale(0.01f * Math.abs((float) Math.sin(xhs)) + 0.08f);
        } else {
            xhs = 0;
            game.bitmapFont.getData().setScale(0.08f);
        }




        gl.setText(game.bitmapFont, String.valueOf(score));





        timerGlyph.setText(game.timerFont, String.valueOf(Math.round(timer)));






       if(EnumClass.state != EnumClass.State.PAUSE && EnumClass.state != EnumClass.State.SCORESCREEN && EnumClass.state != EnumClass.State.STARTING) {
            if (!isGold) {

                game.bitmapFont.draw(game.batch, "" + String.valueOf(shownScore), bitmapfontx + 11 - gl.width / 2, bitmapfonty);
            } else {

                if (fa >= 0 && scoreDimmer) {
                    game.bitmapFont.draw(game.batch, "" + String.valueOf(shownScore), bitmapfontx + 11 - gl.width / 2, bitmapfonty);


                    fa = fa - delta;
                    game.bitmapFont.setColor(1, 1, 1, fa);

                } else {

                    scoreDimmer = false;
                    if (fa <= 1)
                        fa = fa + delta;


                    game.bitmapFont.draw(game.batch, "" + String.valueOf(shownScore) + " x2", bitmapfontx + 11 - gl.width / 2, bitmapfonty);
                    game.bitmapFont.setColor(1, 0.8f, 0.4f, fa);

                }


            }




           game.foundWordsBit.draw(game.batch, foundWordString, 20, bitmapfonty);



           game.timerFont.draw(game.batch, String.valueOf(Math.round(timer)), 135 / 2 - timerGlyph.width / 2, bitmapfonty);




           if(timer<6)
           redder-=delta;


           if(timer<5 && redder<=0){
               redder = 1f;

               game.endBeep.play();
               game.timerFont.setColor(1,0,0,1);


           }



        }
        if(EnumClass.state == EnumClass.State.SCORESCREEN && frameim.getY()<=-100f){
            game.foundWordsBit.draw(game.batch, foundWordString, 100, scoreY);


            if(Gdx.input.isTouched()){





                if(Gdx.input.getX()>Gdx.graphics.getWidth()/3 && Gdx.input.getY()<Gdx.graphics.getHeight()/3 ){

                    scoreY += 1f;

                }

                if(Gdx.input.getX()>Gdx.graphics.getWidth()/3&& Gdx.input.getY()>Gdx.graphics.getHeight()/3*2){
                    scoreY -=1f;
                }

            }



            if(!isGold){
                gl.setText(game.bitmapFont, "SCORE");

                game.bitmapFont.draw(game.batch, "SCORE", 135/3 - gl.width / 2, 180);

                gl.setText(game.scoreFont,String.valueOf(shownScore));

                game.scoreFont.draw(game.batch,String.valueOf(shownScore),135/3 - gl.width/2,170);


            }

            else {
                gl.setText(game.bitmapFont, "SCORE");

                game.bitmapFont.draw(game.batch, "SCORE", 135/3 - gl.width / 2, 180);

                gl.setText(game.scoreFont,String.valueOf(shownScore*2));

                game.scoreFont.draw(game.batch,String.valueOf(shownScore*2),135/3 - gl.width/2,140);

                game.bitmapFont.setColor(1, 0.8f, 0.4f, fa);
                game.scoreFont.setColor(1,0.8f,0.4f,fa);


            }






       }
       if(beginTimer>=0 && EnumClass.state == EnumClass.State.STARTING) {
           if(yh>250/2+40)
           yh -=delta*25;

           gl.setText(game.bitmapFont,"DOUBLE TAP TO SUBMIT");
           game.bitmapFont.draw(game.batch,"DOUBLE TAP TO SUBMIT",135/2-gl.width/2,yh+30);

           gl.setText(game.bitmapFont, "ONLY 4,5,6 LETTER WORDS");
           game.bitmapFont.draw(game.batch,"ONLY 4,5,6 LETTER WORDS",135/2-gl.width/2,yh+20);

           gl.setText(game.bitmapFont, "READY");
           game.bitmapFont.draw(game.batch,"READY",135/2-gl.width/2,yh);

           game.beginFont.draw(game.batch, String.valueOf(beginTimer) + "", 135 / 2 - game.beginFont.getRegion().getRegionWidth()/2*game.beginFont.getScaleX()/10, 300 / 2 +game.beginFont.getAscent()/2*game.beginFont.getScaleY()/10);








       }






















        game.batch.end();
        if(Gdx.input.getAccelerometerX()>20)
        {ArrangeTables();}
        if (Gdx.input.isTouched(2)) {
            ArrangeTables();

        }


        if(stage.getRoot().getColor().a<=0 && replayBool){
            passScreen();
        }


        if(stage.getRoot().getColor().a<=0 && menuBool){
           menuScreen();
        }

        if(stagePass){
            stage.getRoot().setColor(1,1,1,fx);

            fx-=delta;
        }

        if(stage.getRoot().getColor().a<1 && !stagePass){
            stage.getRoot().setColor(1,1,1,fx);
            fx+=delta/2;
        }

        game.foundWordsBit.setColor(1,1,1,stage.getRoot().getColor().a);
        game.bitmapFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.beginFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.scoreFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.timerFont.setColor(1,1,1,stage.getRoot().getColor().a);
        game.scoreText.setColor(1,1,1,stage.getRoot().getColor().a);


    }
    public void passScreen(){



        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                game.setScreen(new GameScreenClass(game,adsController));

            }
        }, 0);


    }

    public void menuScreen(){



        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                game.setScreen(new MenuScreenClass(game,adsController));

            }
        }, 0);


    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {


        EnumClass.state = EnumClass.State.PAUSE;

    }

    @Override
    public void resume() {
/*

               */ EnumClass.state = EnumClass.State.PAUSE;/*



*/
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        blueBloom.dispose();


    }

    public  Vector2 getStageLocation(Actor actor, Table table) {

        v2.x = table.getX();
        v2.y = table.getY();


        return actor.localToParentCoordinates(v2);
    }


    //SUBMIT ACTIONS CLASS
    public void SubmitActions(){

        if(detectedLetter.get(0).equals("0")||detectedLetter.get(1).equals("0")||detectedLetter.get(2).equals("0")||detectedLetter.get(3).equals("0")) {


        }

        else if(detectedLetter.get(4).equals("0")&&detectedLetter.get(5).equals("0")){


            for(int i = 0;i<4;i++){
                word = word + detectedLetter.get(i);
            }

        }
        else if(detectedLetter.get(5).equals("0")){

            for(int i = 0; i<5;i++){
                word = word + detectedLetter.get(i);
            }

        }

        else {

            for (int i = 0; i < 6; i++) {
                word = word + detectedLetter.get(i);
            }

        }

        if(word.length() == 4) {
            if (EntryPoint.dictionary4.contains(word) && !foundWords.contains(word)) {
                game.explode.play();

                blueBloom.start();


                //System.out.println("4LIST CORRECTTTTTTTTTTT");
                foundWords.add(word);

                for (LetterBox letterbox : lettersList1
                        ) {

                    if (letterbox.isOverlap) {
                        letterbox.render().setColor(1, 0.8f, 0.4f, 1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);

                        score = score + letterbox.getPoint();
                        
                    }

                }
                for (LetterBox letterbox : lettersList2
                        ) {

                    if (letterbox.isOverlap) {
                        letterbox.render().setColor(1, 0.8f, 0.4f, 1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox : lettersList3
                        ) {

                    if (letterbox.isOverlap) {
                        letterbox.render().setColor(1, 0.8f, 0.4f, 1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox : lettersList4
                        ) {

                    if (letterbox.isOverlap) {
                        letterbox.render().setColor(1, 0.8f, 0.4f, 1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox : lettersList5
                        ) {

                    if (letterbox.isOverlap) {
                        letterbox.render().setColor(1, 0.8f, 0.4f, 1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox : lettersList6
                        ) {

                    if (letterbox.isOverlap) {
                        letterbox.render().setColor(1, 0.8f, 0.4f, 1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }


                }
            }
        }
        if(word.length() == 5){
            if(EntryPoint.dictionary5.contains(word) && !foundWords.contains(word)){
                game.explode.play();
                blueBloom.start();

                foundWords.add(word);
                for (LetterBox letterbox:lettersList1
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList2
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList3
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList4
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList5
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList6
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
            }
        }

        if(word.length() == 6){
            if(EntryPoint.dictionary6.contains(word) && !foundWords.contains(word)){


                foundWords.add(word);
                game.explode.play();
                blueBloom.start();
                for (LetterBox letterbox:lettersList1
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList2
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList3
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList4
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList5
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
                for (LetterBox letterbox:lettersList6
                        ) {

                    if(letterbox.isOverlap){
                        letterbox.render().setColor(1,0.8f,0.4f,1);
                        letterbox.isGolden = true;
                        goldenBoolList.set(letterbox.goldNumber,true);
                        score = score + letterbox.getPoint();
                    }

                }
            }
        }




          if(  goldenBoolList.contains(false)){

            isGold = false;
          }
          else
              isGold = true;








        word = "";
        foundWordString = "";
        for (String found: foundWords
             ) {

            foundWordString =  found+"\n"+  foundWordString ;
           foundWordString =  foundWordString.toUpperCase();
           
           
           


        }



    }

    public void addPause(){
        if(actorAdder) {

            resButim.toFront();
            actorAdder = false;






        }
        pauseIm.setPosition(135/2 - pauseIm.getWidth()/2, tempY);
        resButim.setPosition(resButim.getX(),pauseIm.getY()+70);
        soundOnim.setPosition(soundOnim.getX(),pauseIm.getY()+150);
        musicim.setPosition(musicim.getX(),pauseIm.getY()+170);
        replaypi.setPosition(135/2-replaypi.getWidth()/2, pauseIm.getY()+130);
        menupi.setPosition(135/2-menupi.getWidth()/2, pauseIm.getY()+110);
        exitim.setPosition(exitim.getX(),pauseIm.getY()+90);
        tempY +=(pauseTargetY - tempY)*Gdx.graphics.getDeltaTime()*10;





    }
    public void removePause(){
       pauseIm.setY(500);
       resButim.setY(500);
       soundOnim.setY(500);
       musicim.setY(500);
       replaypi.setY(500);
       menupi.setY(500);


       if(exitCounter!=5){
           exitim.setY(500);

           exitCounter=0;
       }

       tempY = 500;
    }

    public void ArrangeTables(){
        if (lettersList1.size()%2==1)
            th1 = 108f;
        else
            th1 = 100;
        if (lettersList2.size()%2==1)
            th2 = 108f;
        else
            th2=100;
        if (lettersList3.size()%2==1)
            th3 = 108f;
        else
            th3 = 100;
        if (lettersList4.size()%2==1)
            th4 = 108f;
        else
            th4 = 100;
        if (lettersList5.size()%2==1)
            th5 = 108f;
        else
            th5 = 100;
        if (lettersList6.size()%2==1)
            th6 = 108f;
        else
            th6 = 100;
    }


}
