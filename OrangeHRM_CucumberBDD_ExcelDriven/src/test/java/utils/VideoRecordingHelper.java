package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class VideoRecordingHelper {
    private Process process;

    /**
     * Start recording using ffmpeg. The ffmpeg binary path can be provided via system property:
     * -Dffmpeg.path="/path/to/ffmpeg" or environment variable FFMPEG_PATH.
     * If not provided, expects ffmpeg to be on PATH.
     */
    public void startRecording(String videoFilePath) {
        try {
            String ffmpegPath = System.getProperty("ffmpeg.path");
            if (ffmpegPath == null || ffmpegPath.isEmpty()) {
                ffmpegPath = System.getenv("FFMPEG_PATH");
            }
            if (ffmpegPath == null || ffmpegPath.isEmpty()) {
                ffmpegPath = "ffmpeg"; // assume on PATH
            }

            String[] command = {
                    ffmpegPath,
                    "-f", "gdigrab",
                    "-framerate", "30",
                    "-i", "desktop",
                    "-vcodec", "libx264",
                    "-preset", "ultrafast",
                    "-y", videoFilePath
            };

            File logFile = new File("ffmpeg_output.log");
            process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .redirectOutput(logFile)
                    .start();

            new Thread(() -> captureLogs(process.getInputStream())).start();

            System.out.println("Started recording: " + videoFilePath);
        } catch (IOException e) {
            System.err.println("Error starting recording: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void captureLogs(InputStream inputStream) {
        try {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                System.out.print((char) byteRead);
            }
        } catch (IOException e) {
            System.err.println("Error capturing FFmpeg logs: " + e.getMessage());
        }
    }

    public void stopRecording() {
        if (process != null) {
            try {
                if (process.isAlive()) {
                    OutputStream os = process.getOutputStream();
                    os.write("q".getBytes());
                    os.flush();
                    os.close();
                    if (!process.waitFor(30, TimeUnit.SECONDS)) {
                        process.destroy();
                    }
                    System.out.println("Stopped recording.");
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error stopping recording: " + e.getMessage());
            }
        }
    }
}
