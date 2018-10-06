package com.example.ydi1_feelsbook;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// EmotionCounter is use to count the number of specific emotions when user click on any emotion it will count the numbers of this emotion in the tweetList and return a value count
public class EmotionCounter {
    private int countLove;
    private int countJoy;
    private int countSurprise;
    private int countAnger;
    private int countSadness;
    private int countFear;

    public void getCount(String emotion, ArrayList<String> tweetList) {
        int count = 0;
        for (String line : tweetList) {
            Pattern p = Pattern.compile("\\|" + emotion + "\\|");
            Matcher m = p.matcher(line);
            while (m.find()) {
                count++;
                break;
            }
        }
        if (emotion.equals("love")) {
            countLove = count;
        }
        else if (emotion.equals("joy")) {
            countJoy = count;
        }
        else if (emotion.equals("sadness")) {
            countSadness = count;
        }
        else if (emotion.equals("anger")) {
            countAnger = count;
        }
        else if (emotion.equals("surprise")) {
            countSurprise = count;
        }
        else if (emotion.equals("fear")) {
            countFear = count;
        }
    }

    public int getCountLove() {
        return countLove;
    }
    public int getCountJoy() {
        return countJoy;
    }
    public int getCountSurprise(){
        return countSurprise;
    }
    public int getCountAnger() {
        return countAnger;
    }
    public int getCountSadness() {
        return countSadness;
    }
    public int getCountFear() {
        return countFear;
    }
}
