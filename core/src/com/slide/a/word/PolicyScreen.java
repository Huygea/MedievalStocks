package com.slide.a.word;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PolicyScreen implements Screen{
    private OrthographicCamera camera;

    public static final float WORLD_HEIGHT = 240;
    public static final float WORLD_WIDTH = 135;
    private Viewport viewport;
    private Stage stage;
    private Table table;
    private Label label;
    private  Label.LabelStyle ls;
    Image agree,disagree,blackdot;


    private com.slide.a.word.EntryPoint game;
    private com.slide.a.word.AdsController adsController;

    public PolicyScreen(final EntryPoint game, final AdsController adsController){

        this.game = game;
        this.adsController = adsController;

        adsController.hideBannerAd();

        float aspectRatio = (float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        camera = new OrthographicCamera(aspectRatio * WORLD_WIDTH, WORLD_HEIGHT);
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH , WORLD_HEIGHT,camera );
        stage = new Stage(viewport, game.batch);

        agree = new Image(game.agree);
        disagree = new Image(game.disagree);
        blackdot = new Image(game.blackdot);

        blackdot.setSize(135,35);
        blackdot.setPosition(0,0);

        agree.setSize(30,15);
        disagree.setSize(30,15);

        agree.setPosition(25,10);
        disagree.setPosition(80,10);




        ls = new Label.LabelStyle();
        ls.font = game.policyBmap;
        ls.font.getData().setScale(0.04f);
        table = new Table();
        table.setDebug(true);
        String s = "Feyzi Emrah Basar built the Slide a Word app as an Ad Supported app. This SERVICE is provided by Feyzi Emrah Basar at no cost and is intended for use as is.\n" +
                "\n" +
                "This page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.\n" +
                "\n" +
                "If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.\n" +
                "\n" +
                "The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at Slide a Word unless otherwise defined in this Privacy Policy.\n" +
                "\n" +
                "Information Collection and Use\n" +
                "\n" +
                "For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information, including but not limited to No personal data is collected by this app, other than 3rd party advertisement services.. The information that I request will be retained on your device and is not collected by me in any way.\n" +
                "\n" +
                "The app does use third party services that may collect information used to identify you.\n" +
                "\n" +
                "Link to privacy policy of third party service providers used by the app\n" +
                "\n" +
                "Google Play Services\n" +
                "AdMob\n" +
                "Log Data\n" +
                "\n" +
                "I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.\n" +
                "\n" +
                "Cookies\n" +
                "\n" +
                "Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.\n" +
                "\n" +
                "This Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.\n" +
                "\n" +
                "Service Providers\n" +
                "\n" +
                "I may employ third-party companies and individuals due to the following reasons:\n" +
                "\n" +
                "To facilitate our Service;\n" +
                "To provide the Service on our behalf;\n" +
                "To perform Service-related services; or\n" +
                "To assist us in analyzing how our Service is used.\n" +
                "I want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.\n" +
                "\n" +
                "Security\n" +
                "\n" +
                "I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.\n" +
                "\n" +
                "Links to Other Sites\n" +
                "\n" +
                "This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.\n" +
                "\n" +
                "Children’s Privacy\n" +
                "\n" +
                "These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do necessary actions.\n" +
                "\n" +
                "Changes to This Privacy Policy\n" +
                "\n" +
                "I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page.\n" +
                "\n" +
                "Contact Us\n" +
                "\n" +
                "If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me.";
        label = new Label(s,ls);
        label.setWrap(true);
        label.setSize(120,200);
        label.setPosition(10,-212);

        table.add(label).fill().pad(5);

        table.setPosition(0,0);
        stage.addActor(label);
        stage.addActor(blackdot);
        stage.addActor(agree);
        stage.addActor(disagree);

        agree.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreenClass(game,adsController));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        disagree.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

               Gdx.app.exit();

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        stage.addListener(new ActorGestureListener(){


            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {

                System.out.println("Y: "+label.getY());
                super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {


                if(label.getY()<=300 && label.getY()>=-215)
               label.setY(label.getY()+deltaY);



                super.pan(event, x, y, deltaX, deltaY);
            }
        });




        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act();

        if(label.getY()<-215)
            label.setY(-215);

        if(label.getY()>300)
            label.setY(300);



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



















/*e */