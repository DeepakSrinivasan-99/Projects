package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class VideoRecordingHelper {
    private Process process;

    public void startRecording(String videoFilePath) {
        try {
            String ffmpegPath = "F:\\PROGRAMMING LANGUAGE\\Learn Cucumber BDD with Java -MasterClass Selenium Framework_RahulShetty\\ffmpeg-2024-12-23-git-6c9218d748-full_build\\bin\\ffmpeg.exe"; // Ensure this path is correct
            String[] command = {
                ffmpegPath,
                "-f", "gdigrab",
                "-framerate", "30",
                "-i", "desktop",
                "-vcodec", "libx264",
                "-preset", "ultrafast",
                "-y", videoFilePath
            };

            // Redirect both output and error streams to log files
            File logFile = new File("ffmpeg_output.log");
            process = new ProcessBuilder(command)
                    .redirectErrorStream(true)  // Combine error and output streams
                    .redirectOutput(logFile)    // Capture output to the log file
                    .start();

            // Capture logs to the console as well
            new Thread(() -> captureLogs(process.getInputStream())).start();

            System.out.println("Started recording: " + videoFilePath);
        } catch (IOException e) {
            System.err.println("Error starting recording: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to capture logs from the process's input stream and print them
    private void captureLogs(InputStream inputStream) {
        try {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                System.out.print((char) byteRead);  // Print logs to the console
            }
        } catch (IOException e) {
            System.err.println("Error capturing FFmpeg logs: " + e.getMessage());
        }
    }

    public void stopRecording() {
        if (process != null) {
            try {
                if (process.isAlive()) {
                    // Send 'q' to FFmpeg process to stop recording
                    OutputStream os = process.getOutputStream();
                    os.write("q".getBytes());
                    os.flush();
                    os.close(); // Close OutputStream

                    // Wait for the process to finish gracefully
                    if (!process.waitFor(30, TimeUnit.SECONDS)) {  // 30 seconds wait
                        // Forcefully kill the process if it doesn't stop
                        System.out.println("Forcefully stopping the recording...");
                        process.destroy();
                    }
                    System.out.println("Stopped recording.");
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error stopping recording: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
