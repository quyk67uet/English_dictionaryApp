package org.features.dictionary;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.voicerss.tts.*;
public class VoiceRSS {
    private static final String apiKey = "ea05786b551c46efb3dee38b70b55eb3";
    public static final String audioPath = "english_dictionary/src/main/resources/assets/sounds/voice.mp3";

    public static void voiceSpeak(String text, String language) throws Exception {
        VoiceProvider tts = new VoiceProvider(apiKey);
		
        VoiceParameters params = new VoiceParameters(text, language);
        params.setCodec(AudioCodec.MP3);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);
		
        byte[] voice = tts.speech(params);
		
        FileOutputStream fos = new FileOutputStream(audioPath);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();
    }

}