package gifmaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import javax.imageio.ImageIO;

/**
 * Overarching class. Contains main method.
 * @author earthstar
 *
 */
public class GIFMaker {
    private Style style;
    private AnimatedGifEncoder gifEncoder;
    //Where file is to be saved
    private String destination;
    private Map<String, TalkspriteAnimation> animationMap;
    //GIFMaker automatically generates which names are connected to emotions
    private Map<String, HashSet<String>> nameToEmotion;
    private List<SceneInfo> sceneList;
    
    public GIFMaker(Style style){
        this.style = style;
        gifEncoder = new AnimatedGifEncoder();
        this.setDestination(null);
        animationMap = new HashMap<String, TalkspriteAnimation>();
        nameToEmotion = new HashMap<String, HashSet<String>>();
        compileAnimations();
    }
    
    /**
     * Creates a map of String name, TalkspriteAnimation animation.
     * Looks for files in folder /data that are of the form 
     * name_emotion_number. Creates an entry in a map of the form
     * name_emotion:TalkspriteAnimation. Entries must be .png format.
     */
    public void compileAnimations(){
        File dataLocation = new File("data");
        File[] imageFiles = dataLocation.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name){
                //Only accepts .png files of form
                //text_text_number. 
                return name.matches("^\\w+_\\w+_\\d+.png$");
            }
        });
        //For each file, get the bufferedimage
        for (File f: imageFiles){
            BufferedImage image = null;
            try{
                image = ImageIO.read(f);
                //Get rid of the ".png" part of the name
                String name = f.getName();
                name = name.substring(0, name.length() - 4);
                //Split the name into three parts, add those parts to 
                //nameToEmotion, and animationMap
                String[] nameEmotionID = name.split("_");
                if (!nameToEmotion.containsKey(nameEmotionID[0])){
                    nameToEmotion.put(nameEmotionID[0], new HashSet<String>());
                }
                nameToEmotion.get(nameEmotionID[0]).add(nameEmotionID[1]);
                //generate name for animationMap
                String animationName = nameEmotionID[0]+"_"+nameEmotionID[1];
                if (!animationMap.containsKey(animationName)){
                    animationMap.put(animationName, new TalkspriteAnimation());
                }
                animationMap.get(animationName).addFrame(image, Integer.parseInt(nameEmotionID[2]));
            } catch (IOException ex){
                System.out.println("Can't read file");
            }
        }
    }
    
    

    public Map<String, TalkspriteAnimation> getAnimationMap(){
        return animationMap;
    }
    
    public Map<String, HashSet<String>> getNameToEmotion(){
        return nameToEmotion;
    }
    
    /**
     * Returns the talksprite associated with a name, or null
     * if it doesn't exist
     * @param name
     * @return
     */
    public TalkspriteAnimation getAnimation(String name){
        return animationMap.get(name);
    }
    
    public Style getStyle() {
        return style;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    /*
    public static void main(String[] args){
        AlterniaboundStyle s = new AlterniaboundStyle();
        GIFMaker g = new GIFMaker(s);
        System.out.println(g.getAnimationMap());
        System.out.println(g.getNameToEmotion());
        System.out.println(g.getAnimationMap().get("karkat_normal").getImages());
    }*/

    public List<SceneInfo> getSceneList() {
        return sceneList;
    }

    public void addScene(SceneInfo scene){
        sceneList.add(scene);
    }
    
    public static void main(String[] args){
        GIFMaker g = new GIFMaker(new TestStyle());
        SceneInfo s = new SceneInfo(g);
        s.setText("GODDAMN WHY AM I OFF CENTER");
        s.setTalksprite("karkat_normal");
        s.makeFrameInfoList();
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start("data/test.gif");
        encoder.setRepeat(0);
        s.addFrames(encoder);
        encoder.finish();
        /*encoder.setDelay(s.calculateTiming());
        encoder.setRepeat(0);
        for (BufferedImage f: frames){
            encoder.addFrame(f);
        }
        encoder.finish();*/
    }

}
