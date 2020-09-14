package com.kyora.studio.vote.util;

import com.kyora.studio.vote.config.EvoteProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.util.UUID;

import static com.google.common.io.Files.move;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectoryService {

    private final EvoteProperties evoteProperties;

    public String createFile(String newDir, String fileName, String orgId) {

        String dir = evoteProperties.getPath().getTmp() + orgId + File.separator + newDir;
        File directory = new File(dir);
        if (!directory.exists())
            directory.mkdirs();
        return dir + File.separator + fileName;
    }

    public void moveToDirectory(File from, File to) {
        try {
            log.debug("REQ MOVE FROM: {}, TO: {}", from.getPath(), to.getPath());
            move(from, to);
            log.debug("REQ MOVE DONE AND FILE FROM EXISTS: {}", from.exists());
        } catch (IOException e) {
            log.warn("REQ MOVE FILE EXCEPTION: {}", e.getMessage());
        }
    }

    public void deleteFile(File file) {
        try {
            log.debug("REQ DELETE FILE: {}", file.getPath());
            Files.deleteIfExists(file.toPath());
        } catch (FileSystemException ex) {
            log.warn("REQ DELETE FILE CATCH FILE SYSTEM EXCEPTION: {}", ex.getMessage());
        } catch (IOException ex) {
            log.warn("REQ DELETE FILE: {}", ex.getMessage());
        }
    }


    public String createFile(String newDir, String fileName) {

        String dir = evoteProperties.getPath().getTmp() + File.separator + newDir;

        File file = new File(dir + File.separator + fileName);
        if (file.exists()) {
            try {
                log.debug("FILE IS EXISTS: {}", file.exists());
                boolean deleteIfExists = Files.deleteIfExists(file.toPath());
                log.debug("FILE DELETED: {}", deleteIfExists);
                if (deleteIfExists) {
                    return createFile(newDir, fileName);
                }
            } catch (FileSystemException e) {
                log.warn("FILE SYSTEM EXCEPTION: {}", e.getMessage());
                if (!file.delete()) {
                    // wait a bit then retry on Windows
                    if (file.exists()) {
                        for (int i = 0; i < 6; i++) {
                            try {
                                Thread.sleep(500);
                                System.gc();
                                if (file.delete()) {
                                    log.debug("YOU ARE DELETED FUCKER: {}", file.getPath());
                                    return createFile(newDir, fileName);
                                }
                                break;
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.debug("REQ CREATE FILE IN DIRECTORY: {}, FILENAME: {}", newDir, fileName);
        File directory = new File(dir);
        if (!directory.exists()) {
            log.debug("DIRECTORY NOT EXISTS, CREATED DIRECTORY");
            boolean mkdirs = directory.mkdirs();
            log.debug("DIRECTORY FILE EXISTS: {}, CREATED DIRECTORY: {}", directory.exists(), mkdirs);
        }
        return dir + File.separator + fileName;
    }

    public String createDirectory(String newDir) {

        String dir = evoteProperties.getPath().getTmp() + newDir;
        File directory = new File(dir);
        if (!directory.exists())
            directory.mkdirs();
        return dir;
    }

    public String createFileTree(String path, String npwp, int masa, int tahun, int pembetulan, String fileName) {
        String dir = evoteProperties.getPath().getTmp() + File.separator + npwp + File.separator + tahun + File.separator + masa + File.separator + pembetulan + File.separator + path;

        File directory = new File(dir);
        if (!directory.exists())
            directory.mkdirs();

        String finalPath = dir + File.separator + fileName;
        log.debug("REQ CREATE FILE TREE: {}", finalPath);
        return finalPath;
    }

    public String createFileTreeWithoutFolder(String npwp, int masa, int tahun, int pembetulan, String fileName) {
        String dir = evoteProperties.getPath().getTmp() + File.separator + npwp + File.separator + tahun + File.separator + masa + File.separator + pembetulan;
        File directory = new File(dir);
        if (!directory.exists())
            directory.mkdirs();
        return dir + File.separator + fileName;
    }

    public String getFileTree(String path, String npwp, int masa, int tahun, int pembetulan, String fileName) {
        String dir = evoteProperties.getPath().getTmp() + File.separator + npwp + File.separator + tahun + File.separator + masa + File.separator + pembetulan + File.separator + path;
        return dir + File.separator + fileName;
    }

    public String createFileNameLapor(String npwp, int masa, int tahun, int pembetulan, String kodeForm, String extension) {
        return npwp +
                String.format("%02d", masa) +
                String.format("%02d", masa) +
                tahun +
                String.format("%02d", pembetulan) +
                kodeForm +
                "." + extension;
    }

    public String createFileName(String npwpPemotong, int masa, int tahun, int pembetulan, String kodeForm, String format) {
        return new StringBuilder()
                .append(kodeForm)
                .append("-")
                .append(npwpPemotong)
                .append(String.format("%02d", masa))
                .append(String.format("%02d", masa))
                .append(tahun)
                .append(String.format("%02d", pembetulan))
                .append("-")
                .append(UUID.randomUUID().toString())
                .append("." + format).toString();

    }


    public String createBpPdfFileName(String npwpPemotong, int masa, int tahun, int pembetulan, String kodeForm, String noBupot) {
        return new StringBuilder()
                .append(kodeForm)
                .append("-")
                .append(npwpPemotong)
                .append(String.format("%02d", masa))
                .append(String.format("%02d", masa))
                .append(tahun)
                .append(String.format("%02d", pembetulan))
                .append("-")
                .append(noBupot)
                .append("-")
                .append(UUID.randomUUID().toString())
                .append(".pdf").toString();

    }

    public String createFileNameEbilling(String type, String id, String ext) {
        return "EBILLING" +
                "-" +
                type +
                "-" +
                id +
                "." +
                ext;
    }


    public String createFileNameZip() {
        log.info("create filename zip");
        return new StringBuilder()
                .append(UUID.randomUUID().toString())
                .append(".zip").toString();
    }

    public String createFileNameCsv() {
        return new StringBuilder()
                .append(UUID.randomUUID().toString())
                .append(".csv").toString();
    }

    public String createFileNameCsv(String fileName) {
        return new StringBuilder()
                .append(fileName)
                .append(UUID.randomUUID().toString())
                .append(".csv")
                .toString();
    }

    public String createTempFile(String fileName) {
        String dir = evoteProperties.getPath().getTmp() + "TEMP";
        File directory = new File(dir);
        if (!directory.exists())
            directory.mkdirs();
        return dir + File.separator + fileName;
    }


}
