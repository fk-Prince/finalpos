package User;

import java.io.*;

public class UserRepository {
    private static final File userFile = new File("User.txt");

    public static boolean isUserExist(User user) throws IOException {
        boolean isUserExist = false;
        if (!userFile.exists()) userFile.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(userFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] lines = line.split(",");
            if (lines[0].equals(user.getUsername()) && lines[1].equals(user.getPassword())) {
                isUserExist = true;
            }
        }
        return isUserExist;
    }

    public static boolean isUsernameExist(String username) throws IOException {
        if (!userFile.exists()) userFile.createNewFile();
        return new BufferedReader(new FileReader(userFile))
                .lines().map(lines -> lines.split(","))
                .anyMatch(lines -> username.equalsIgnoreCase(lines[0]));
    }

    public static void addUser(User user) throws IOException {
        if (!userFile.exists()) userFile.createNewFile();
        BufferedWriter br = new BufferedWriter(new FileWriter(userFile, true));
        br.write(user.getUsername() + "," + user.getPassword() + "\n");
        br.close();
    }
}
