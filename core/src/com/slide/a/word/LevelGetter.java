package com.slide.a.word;

import java.util.ArrayList;

public class LevelGetter {


    ArrayList<Integer> levelList;

    int a ;

    public LevelGetter(){

        levelList = new ArrayList<Integer>();



    }

    public void arrayListSetter(){

        for(int k = 0 ; k<50;k++){

            if(k-1>=0)
            levelList.add((levelList.get(k-1)+k*30000));

            else
                levelList.add(30000);


            System.out.println(levelList.get(k));

        }
    }

    public int LevelReturn(int cumScore){



        for(int i = 0; i<levelList.size();i++){



            if(i!=0) {
                if (cumScore > levelList.get(i - 1) && cumScore <= levelList.get(i)) {

                    if (i < 50)
                        a = i ;
                    else
                        a = 50;

                    break;

                }
            }

            else {a=0;}





        }


        return a;

    }

    public int lvlRoof(int lvl){

        if(lvl < levelList.size())
       return levelList.get(lvl);
        else
            return levelList.get(49);

    }

}
