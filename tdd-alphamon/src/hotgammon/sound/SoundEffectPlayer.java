package hotgammon.sound;

/*
 *  SimpleAudioPlayer.java
 *
 *  This file is part of jsresources.org
 */

/*
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 |<---            this code is formatted to fit into 80 columns             --->|
 */

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.DataLine.Info;

/**
 * <titleabbrev>SimpleAudioPlayer</titleabbrev> <title>Playing an audio file
 * (easy)</title>
 * 
 * <formalpara><title>Purpose</title> <para>Plays a single audio file.</para></formalpara>
 * 
 * <formalpara><title>Usage</title> <cmdsynopsis> <command>java
 * SimpleAudioPlayer</command> <replaceable class="parameter">audiofile</replaceable>
 * </cmdsynopsis> </formalpara>
 * 
 * <formalpara><title>Parameters</title> <variablelist> <varlistentry> <term><option><replaceable
 * class="parameter">audiofile</replaceable></option></term> <listitem><para>the
 * name of the audio file that should be played</para></listitem>
 * </varlistentry> </variablelist> </formalpara>
 * 
 * <formalpara><title>Bugs, limitations</title>
 * 
 * <para>Only PCM encoded files are supported. A-law, &mu;-law, ADPCM, ogg
 * vorbis, mp3 and other compressed data formats are not supported. For playing
 * these, see <olink targetdoc="AudioPlayer" targetptr="AudioPlayer">AudioPlayer</olink>.</para>
 * 
 * </formalpara>
 * 
 * <formalpara><title>Source code</title> <para> <ulink
 * url="SimpleAudioPlayer.java.html">SimpleAudioPlayer.java</ulink> </para>
 * </formalpara>
 * 
 */
public class SoundEffectPlayer {

    public enum sound {
        DIE, CHECKER
    };

    private Clip audioClip;
    private Info info;
    private AudioFormat audioFormat;
    private AudioInputStream audioStream;

    public static void main(String[] args) {
        new SoundEffectPlayer(sound.DIE);
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SoundEffectPlayer(sound snd) {

        File sndFile = null;
        switch (snd) {
        case DIE:
            sndFile = new File("build/resource/die.wav");
            break;
        case CHECKER:
            sndFile = new File("build/resource/checker.wav");
        default:
            break;
        }
        /*
         * We have to read in the sound file.
         */
        audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(sndFile);
        } catch (Exception e) {
            /*
             * In case of an exception, we dump the exception including the
             * stack trace to the console output. Then, we return;.
             */
            e.printStackTrace();
            return;
        }

        audioFormat = audioStream.getFormat(); // they are in same format
        info = new DataLine.Info(Clip.class, audioFormat);
        try {
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(new LineListener() {

                public void update(LineEvent event) {
                    if (event.getType().equals(LineEvent.Type.STOP)) {
                        audioClip.close();
                        audioClip.setFramePosition(0);
                    }
                }

            });

            audioClip.open(audioStream);
            audioClip.start();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        

    }

}
