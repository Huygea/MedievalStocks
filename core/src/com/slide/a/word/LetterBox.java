package com.slide.a.word;





import com.badlogic.gdx.math.Circle;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by user on 9.04.2018.
 */



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.slide.a.word.EntryPoint;

import java.util.ArrayList;

import static com.slide.a.word.GameScreenClass.mainRect;


/**
 * Created by user on 6.06.2017.
 */

public class LetterBox extends Actor {




    float x,y;
    public static float SPEED = 60f;
    public Image cube;

    public  boolean isOverlap = false;
    String letter;
    int point;

    public boolean isGolden = false;
    public int goldNumber;

    final com.slide.a.word.EntryPoint game;
    public boolean remove = false;




    public LetterBox (String letter, final EntryPoint game)
    {


        this.game = game;

        this.letter=letter;


       // yoltex = new Texture(letter+".png");
       // rect = new Rectangle();




        if(letter.equals("a")||letter.equals("e")||letter.equals("i")||letter.equals("o")||letter.equals("u")||letter.equals("l")||
                letter.equals("n")||letter.equals("s")||letter.equals("t")||letter.equals("r"))
            point = 100;

        else if(letter.equals("d")||letter.equals("g"))
            point = 200;
        else if(letter.equals("b")||letter.equals("c")||letter.equals("m")||letter.equals("p"))
            point = 300;
        else if(letter.equals("f")||letter.equals("h")||letter.equals("v")||letter.equals("w")||letter.equals("y"))
            point = 400;
        else if(letter.equals("k"))
            point = 500;
        else if(letter.equals("j")||letter.equals("x"))
            point = 800;
        else if(letter.equals("q")||letter.equals("z"))
            point = 1000;
        else
            point = 0;








            cube = new Image(game.ta.createSprite(letter));

            cube.setSize(15,15);









        }















    public void update(float delta)
    {




        DetectLetter();




    }

    public Actor render()
    {
        return cube;

    }

    public String DetectLetter(){

        if(isOverlap)
            return letter;
        else
            return "0";


    }

    public int getPoint(){
        return point;
    }

}

