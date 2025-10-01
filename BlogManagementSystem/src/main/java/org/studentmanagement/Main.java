package org.studentmanagement;

import org.service.BlogService;
import org.service.UserService;

import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static UserService userService = new UserService();
    public static void main(String[] args) {
        System.out.println("1.Register a user\n2. Create a Blog\n3. Get User details\n4. Delete a user");
        int ch = in.nextInt();
        in.nextLine();
        switch (ch) {
            case 1:
                registerUser();
                break;
            case 2:
                createBlog();
                break;
            case 3:
                getUserById();
                break;
            case 4:
                deleteUser();
                break;
        }
    }

    public static void registerUser() {

        System.out.println("Enter name of the user");
        String name = in.nextLine();
        System.out.println("Enter email of the user");
        String email = in.nextLine();
        userService.registerUser(name, email);
    }

    public static void createBlog() {
        BlogService blogService = new BlogService();
        System.out.println("enter the userID to save to that user:");
        int userId = in.nextInt();
        in.nextLine();
        System.out.println("enter title of blog");
        String title = in.nextLine();
        System.out.println("enter content of blog");
        String content = in.nextLine();
        blogService.createBlog(userId, title, content);
    }

    public static void getUserById(){
        System.out.println("enter the userID to get user:");
        int userId = in.nextInt();
        userService.getUserById(userId);
    }

    public static void deleteUser(){
        System.out.println("enter the userID to delete user:");
        int userId = in.nextInt();
        userService.deleteUser(userId);
    }
}