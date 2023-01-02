package com.example.blood_donation.core;

import com.example.blood_donation.model.User;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static final String DATA_FILE = "data.bin";
    public static final String USER_SESSION = "session.bin";
    public static final String BLOOD_REQUEST = "blood_request.bin";

    public <T> T loadObjectFromFile(File file) {
        T t = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            t = (T) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return t;
    }

    public <T> boolean saveObjectToFile(File file, T object) {
        boolean saved = false;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            saved = true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return saved;
    }

}
