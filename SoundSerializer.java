package byog.World;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class SoundSerializer implements Serializable {

    private byte[] buf;
    private int randomInteger =0;
    private String randomString = "";
    //AudioStream audioStream = null;

    public SoundSerializer(String fileN) throws FileNotFoundException, IOException {
        File file = new File(fileN);

        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        buf = new byte[1024];

        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum); //no doubt here is 0
            //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            System.out.println("read " + readNum + " bytes,");
        }

        buf = bos.toByteArray();
    }


    /**
     *fonction to test the sound after deserialization
     */
    public void playSound(){
        InputStream is = new ByteArrayInputStream(buf);
       /* audioStream = null;
        try {
            audioStream = new AudioStream(is);
        } catch (IOException ex) {
            Logger.getLogger(SoundSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioPlayer.player.start(audioStream);*/
    }
    public void stopSound(){
        //AudioPlayer.player.stop(audioStream);
    }
}