package file_sort;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorter {

    private static final int CHUNK_SIZE = 10000;

    public File sortFile(File dataFile) throws IOException {
        List<File> chunks = splitFileIntoChunks(dataFile);
        sortChunks(chunks);
        return mergeChunks(chunks);
    }

    private List<File> splitFileIntoChunks(File dataFile) throws IOException {
        List<File> chunks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            int i = 0;
            List<Long> chunk = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                chunk.add(Long.parseLong(line));
                if (chunk.size() == CHUNK_SIZE) {
                    writeInChunk(chunks, i, chunk);
                    chunk.clear();
                }
            }
            if (!chunk.isEmpty()) {
                writeInChunk(chunks, i, chunk);
            }
        }
        return chunks;
    }

    private void writeInChunk(List<File> chunks, int i, List<Long> chunk) throws IOException {
        Collections.sort(chunk);
        File chunkFile = Files.createTempFile("chunk_" + i, "txt").toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chunkFile))) {
            for (long number : chunk) {
                writer.write(number + "\n");

            }
        }

        chunks.add(chunkFile);
    }

    private void sortChunks(List<File> chunks) throws IOException {
        for (File chunk : chunks) {
            List<Long> numbers = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(chunk))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    numbers.add(Long.parseLong(line));
                }
            }

            Collections.sort(numbers);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(chunk))) {
                for (long number : numbers) {
                    writer.write(number + "\n");
                }
            }

        }
    }

    private File mergeChunks(List<File> chunks) throws IOException {
        if (chunks.isEmpty()) {
            return null;
        } else if (chunks.size() == 1) {
            return chunks.get(0);
        } else {
            List<BufferedReader> readers = new ArrayList<>();
            for (File chunk : chunks) {
                readers.add(new BufferedReader(new FileReader(chunk)));
            }

            File mergedFile = new File("src/file_sort/resources/merged.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFile));
            List<Long> values = new ArrayList<>();

            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                if (line != null) {
                    values.add(Long.parseLong(line));
                }
            }

            while (!values.isEmpty()) {
                long min = Collections.min(values);
                writer.write(min + "\n");

                for (int i = 0; i < readers.size(); i++) {
                    BufferedReader reader = readers.get(i);
                    if (values.get(i) == min) {
                        String line = reader.readLine();
                        if (line != null) {
                            values.set(i, Long.parseLong(line));
                        } else {
                            values.remove(i);
                            readers.remove(i);
                        }
                        break;
                    }
                }

            }
            writer.close();
            for (BufferedReader reader : readers) {
                reader.close();
            }
            return mergedFile;
        }
    }
}


