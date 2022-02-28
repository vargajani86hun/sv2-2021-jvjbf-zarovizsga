package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();

    public int calculateSumOfVideos() {
        return channels.stream().mapToInt(Channel::getNumberOfVideos).sum();
    }

    public List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }

    public void readDataFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                addChannel(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    private void addChannel(String channelName, int subscriptions, int numberOfVideos) {
        channels.add(new Channel(channelName, subscriptions, numberOfVideos));
    }
}
