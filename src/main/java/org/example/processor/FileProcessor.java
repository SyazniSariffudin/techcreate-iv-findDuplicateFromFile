package org.example.processor;

import org.example.dto.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class FileProcessor<T extends Person> {

    public void readFileAndFindDuplicate() throws IOException, URISyntaxException {
        URL res = getClass().getClassLoader().getResource(getFileName());
        Path path = Paths.get(res.toURI());
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            List<T> cachedFileData = new ArrayList<>();
            String lineData;
            while ((lineData = bufferedReader.readLine()) != null) {
                String[] rows = lineData.split(getFileDelimiter());
                T t = fileToObj(rows);
                Optional<T> duplicate = findDuplicate(t, cachedFileData);
                duplicate.ifPresent(obj -> System.out.printf("%s: %s duplicate with %s%n", getFileName(), t.getId(), obj.getId()));
            }
        }
    }

    public Optional<T> findDuplicate(T t, List<T> cachedFileData) {
        Optional<T> duplicate = Optional.empty();
        if (cachedFileData.contains(t)) {
            T firstDuplicate = cachedFileData.get(cachedFileData.indexOf(t));
            duplicate = Optional.of(firstDuplicate);
        }
        cachedFileData.add(t);
        return duplicate;
    }

    public String getFileDelimiter() {
        return ",";
    }

    public abstract T fileToObj(String[] rows);

    public abstract String getFileName();
}
