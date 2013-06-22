package gifmaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import javax.imageio.ImageIO;

/**
 * Overarching class. Contains main method. TODO find some way
 * of storing the timing information for each sprite
 * @author earthstar
 *
 */
public class GifMaker {
    private Style style;
    private Map<String, Style> styleMap;
    private AnimatedGifEncoder gifEncoder;
    //Where file is to be saved
    private String destination;
    private Map<String, TalkspriteAnimation> animationMap;
    //GIFMaker automatically generates which names are connected to emotions
    private Map<String, HashSet<String>> nameToEmotion;
    private List<SceneInfo> sceneList;
    
    @Deprecated
    public GifMaker(Style style){
        this.style = style;
        gifEncoder = new AnimatedGifEncoder();
        gifEncoder.setRepeat(0);
        this.setDestination(null);
        animationMap = new HashMap<String, TalkspriteAnimation>();
        nameToEmotion = new HashMap<String, HashSet<String>>();
        sceneList = new ArrayList<SceneInfo>();
        //compileAnimations();
    }
    
    public GifMaker(){
        //Which constructor does GUI use?
        styleMap = new HashMap<String, Style>();
        styleMap.put("Test", new TestStyle());
        //styleMap.put("Alterniabound", new AlterniaboundStyle());
        new GifMaker(null);
        gifEncoder = new AnimatedGifEncoder();
        gifEncoder.setRepeat(0);
        gifEncoder.setQuality(20);
        this.setDestination(null);
        animationMap = new HashMap<String, TalkspriteAnimation>();
        nameToEmotion = new HashMap<String, HashSet<String>>();
        sceneList = new ArrayList<SceneInfo>();
    }
    
    /**
     * Creates a map of String name, TalkspriteAnimation animation.
     * Looks for files in folder /data that are of the form 
     * name_emotion_number. Creates an entry in a map of the form
     * name_emotion:TalkspriteAnimation. Entries must be .png format.
     * Should be called after style is selected
     * TODO use config files
     */
    public void compileAnimations(){
        File dataLocation = new File(style.getSpriteLocation());
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
                animationMap.get(animationName).addFrame(image, 
                        Integer.parseInt(nameEmotionID[2]), this.style.getDefaultTiming());
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
    
    //Helper method to sort list
    public static
    <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
      List<T> list = new ArrayList<T>(c);
      java.util.Collections.sort(list);
      return list;
    }
    
    /**
     * @return an alphabetical array of animations from animationMap
     */
    public String[] getAnimationNames(){
        Set<String> animationSet = animationMap.keySet();
        Collection<String> unsorted = animationMap.keySet();
        List<String> sorted = asSortedList(unsorted);
        String[] ans = new String[sorted.size()];
        sorted.toArray(ans);
        return ans;
    }
    
    /**
     * 
     * @return a list of valid characters
     */
    public List<String> getCharacters(){
        return null; //TODO
    }
    
    /**
     * 
     * @return valid animations for a particular character
     */
    public List<String> getCharacterAnimations(String name){
        return null; //TODO
    }
    
    public Map<String, Style> getStyleMap(){
        return styleMap;
    }
    
    public void setStyle(Style style){
        this.style = style;
        compileAnimations();
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
    
    public List<SceneInfo> getSceneList() {
        return sceneList;
    }

    public void addScene(SceneInfo scene){
        sceneList.add(scene);
    }
    
    public AnimatedGifEncoder getGifEncoder(){
        return gifEncoder;
    }
    
    public void compileGIF(String filename){
        gifEncoder.start(filename);
        for (SceneInfo s: sceneList){
            
            s.addFrames(gifEncoder);
        }
        gifEncoder.finish();
    }
    
    public static void main(String[] args){
        GifMaker g = new GifMaker();
        g.setStyle(new AlterniaboundMinimized());
        SceneInfo s1 = new SceneInfo(g);
        s1.setTalksprite("karkat_Normal");
        s1.setText("YOU CAN REQUEST I JABBER LIKE A MANIAC.");
        s1.setTextColor(0x626262);
        s1.makeFrameInfoList();
//        SceneInfo s2 = new SceneInfo(g);
//        s2.setTalksprite("karkat_Normal");
//        s2.setText("YOU CAN REQUEST I JABBER LIKE A MANIAC.");
//        s2.setTextColor(0x626262);
//        s2.makeFrameInfoList();

//        SceneInfo s3 = new SceneInfo(g);
//        s3.setText("Nepeta: :33 < i have b33n practicing my compurrter skills and making a funny mewving picture maker!");
//        s3.setTalksprite("nepeta_PointedEyes");
//        s3.setTextColor(0x416600);
//        s3.setTalkspriteTiming(200);
//        s3.makeFrameInfoList();
//        SceneInfo s4 = new SceneInfo(g);
//        s4.setText("Karkat: YOU MEAN EVEN MORE PEOPLE WILL BE PUTTING WORDS IN MY MOUTH!");
//        s4.setTextColor(0x626262);
//        s4.setTalksprite("karkat_Bugout");
//        s4.setIsLeft(false);
//        s4.makeFrameInfoList();
//        SceneInfo s5 = new SceneInfo(g);
//        s5.setText("Nepeta: :33 < and other things!");
//        s5.setTextColor(0x416600);
//        s5.setTalksprite("nepeta_SparklyEyes");
//        s5.setTalkspriteTiming(200);
//        s5.makeFrameInfoList();
        g.addScene(s1);
//        g.addScene(s2);
//        g.addScene(s3);
//        g.addScene(s4);
//        g.addScene(s5);
        g.compileGIF("test.gif");
    }

}
