package com.example.blood_donation.core;

import com.example.blood_donation.core.enums.BloodGroup;
import com.example.blood_donation.model.User;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {

    private FileManager fileManager = new FileManager();

    public boolean saveUsersToFile(ArrayList<User> users) {
        return fileManager.saveObjectToFile(new File(FileManager.DATA_FILE), users);
    }

    public ArrayList<User> loadUsers() {
        ArrayList<User> users = fileManager.loadObjectFromFile(new File(FileManager.DATA_FILE));
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public boolean saveUserSession(User user) {
        return fileManager.saveObjectToFile(new File(FileManager.USER_SESSION), user);
    }

    public User getLoggedInUser() {
        return fileManager.loadObjectFromFile(new File(FileManager.USER_SESSION));
    }

    public ArrayList<User> seedUsers() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(
                new User("Rahman Atiqur", "+8801525126385", "asd123asd", "Male", "Zindabazar",BloodGroup.O_POSITIVE, LocalDate.parse("1/2/1997", dateTimeFormatter)),
                new User("Jahura Aysha", "+8801752126315", "asd123asd", "Female", "Noysharak",BloodGroup.O_NEGATIVE, LocalDate.parse("12/10/1998", dateTimeFormatter)),
                new User("Majhar Sohel", "+8801341154751", "asd123asd", "Male", "Tilagor", BloodGroup.AB_POSITIVE, LocalDate.parse("8/4/2001", dateTimeFormatter)),
                new User("Tahsin Shohag", "+8801715116324", "asd123asd", "Male", "Zindabazar", BloodGroup.AB_NEGATIVE, LocalDate.parse("14/10/2000", dateTimeFormatter)),
                new User("Mamun Mehedi", "+8801614151324", "asd123asd", "Male", "Islampur",BloodGroup.O_POSITIVE, LocalDate.parse("21/12/2002", dateTimeFormatter)),
                new User("Nasim Sabbir", "+8801712136481", "asd123asd", "Male", "Major Tilla",BloodGroup.A_POSITIVE, LocalDate.parse("27/1/2000", dateTimeFormatter)),
                new User("Begum Monira", "+8801341146588", "asd123asd", "Female", "Shahporan", BloodGroup.AB_POSITIVE, LocalDate.parse("12/12/2000", dateTimeFormatter)),
                new User("Biswas Farhad", "+8801752154351", "asd123asd", "Male", "Amberkhana",BloodGroup.B_NEGATIVE, LocalDate.parse("3/9/1999", dateTimeFormatter)),
                new User("Islam Shohag", "+8801752156485", "asd123asd", "Male", "Subidbazar", BloodGroup.AB_NEGATIVE, LocalDate.parse("5/7/2000", dateTimeFormatter)),
                new User("Siddika Roksana", "+8801752357114", "asd123asd", "Female", "Rikabi Bazar",BloodGroup.O_NEGATIVE, LocalDate.parse("25/8/1999", dateTimeFormatter)),
                new User("Nishat Rahman", "+8801715262389", "asd123asd", "Female", "Lamabazar",BloodGroup.O_POSITIVE, LocalDate.parse("14/12/2000", dateTimeFormatter)),
                new User("Saif Hasan", "+8801721456365", "asd123asd", "Male", "Mirjajangal", BloodGroup.AB_POSITIVE, LocalDate.parse("27/7/1998", dateTimeFormatter)),
                new User("Sadia Rahman", "+8801714326189", "asd123asd", "Female", "Tilagor",BloodGroup.O_POSITIVE, LocalDate.parse("5/7/2001", dateTimeFormatter)),
                new User("Abdul Fahad", "+8801315156283", "asd123asd", "Male", "Mirabazar",BloodGroup.O_POSITIVE, LocalDate.parse("12/1/1998", dateTimeFormatter)),
                new User("Salma Akter", "+8801734152142", "asd123asd", "Female", "Lamabazar",BloodGroup.O_NEGATIVE, LocalDate.parse("16/5/1999", dateTimeFormatter)),
                new User("Jannatul Sharmin", "+8801341556386", "asd123asd", "Female", "Zindabazar", BloodGroup.AB_POSITIVE, LocalDate.parse("21/4/2000", dateTimeFormatter)),
                new User("Muhammad Irfan", "+8801721154382", "asd123asd", "Male", "Mirjajangal", BloodGroup.AB_NEGATIVE, LocalDate.parse("19/7/1999", dateTimeFormatter)),
                new User("Fatema Khatun", "+8801914126346", "asd123asd", "Female", "Uposhohor",BloodGroup.B_POSITIVE, LocalDate.parse("26/5/2000", dateTimeFormatter)),
                new User("Abdullah Ahad", "+8801621455326", "asd123asd", "Male", "Zindabazar",BloodGroup.O_POSITIVE, LocalDate.parse("5/5/1999", dateTimeFormatter)),
                new User("Taslima Akter", "+8801745136326", "asd123asd", "Female", "Noyasarak",BloodGroup.B_NEGATIVE, LocalDate.parse("7/1/2002", dateTimeFormatter)),
                new User("Tania Begum", "+8801821455351", "asd123asd", "Female", "Sheikhghat", BloodGroup.AB_NEGATIVE, LocalDate.parse("9/6/2000", dateTimeFormatter)),
                new User("Hasan Murad", "+8801321456516", "asd123asd", "Male", "Rikabi Bazar",BloodGroup.O_POSITIVE, LocalDate.parse("22/10/2000", dateTimeFormatter)),
                new User("Asma Begum", "+8801341256587", "asd123asd", "Female", "Rikabi Bazar",BloodGroup.A_POSITIVE, LocalDate.parse("23/5/2001", dateTimeFormatter)),
                new User("Khadiza Begum", "+8801731455361", "asd123asd", "Female", "Amberkhana",BloodGroup.O_NEGATIVE, LocalDate.parse("15/9/1998", dateTimeFormatter)),
                new User("Syed Farhad", "+8801712322338", "asd123asd", "Male", "Noyasarak",BloodGroup.A_NEGATIVE, LocalDate.parse("4/9/1999", dateTimeFormatter)),
                new User("Farjana Akter", "+8801515256429", "asd123asd", "Female", "Jail Road", BloodGroup.AB_POSITIVE, LocalDate.parse("24/7/2000", dateTimeFormatter)),
                new User("Abdur Rauf", "+8801932455519", "asd123asd", "Male", "Uposhohor", BloodGroup.AB_POSITIVE, LocalDate.parse("9/4/2000", dateTimeFormatter)),
                new User("Sheikh Kawsar", "+8801731251331", "asd123asd", "Male", "Islampur",BloodGroup.O_POSITIVE, LocalDate.parse("18/10/1997", dateTimeFormatter)),
                new User("Ahmed Ohi", "+8801814136246", "asd123asd", "Male", "Shahporan", BloodGroup.AB_NEGATIVE, LocalDate.parse("8/5/1999", dateTimeFormatter)),
                new User("Nasrin Rabeya", "+8801751252382", "asd123asd", "Female", "Amberkhana",BloodGroup.O_NEGATIVE, LocalDate.parse("9/5/2000", dateTimeFormatter)),
                new User("Nusrat Renu", "+8801541352329", "asd123asd", "Female", "Islampur",BloodGroup.B_POSITIVE, LocalDate.parse("26/2/1998", dateTimeFormatter)),
                new User("Kazi Rafi", "+8801763459331", "asd123asd", "Male", "Lamabazar",BloodGroup.B_NEGATIVE, LocalDate.parse("19/10/1997", dateTimeFormatter)),
                new User("Tahmina Sarmin", "+8801744136182", "asd123asd", "Female", "Zindabazar",BloodGroup.O_POSITIVE, LocalDate.parse("4/2/1997", dateTimeFormatter)),
                new User("Ayesha Khatun", "+880183993234", "asd123asd", "Female", "Tilagor", BloodGroup.AB_POSITIVE, LocalDate.parse("12/12/1999", dateTimeFormatter)),
                new User("Nazmul Roni", "+8801713215431", "asd123asd", "Male", "Sheikhghat",BloodGroup.A_NEGATIVE, LocalDate.parse("4/8/2002", dateTimeFormatter))
                ));

        saveUsersToFile(users);
        return users;

    }

    public void logoutUser() {
        saveUserSession(null);
    }
}
