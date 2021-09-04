import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static <Constructors, Fields> void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner myInp = new Scanner(System.in);
        System.out.println("Please enter class name");
        String className = myInp.next();
        //System.out.println(className);
        try {
            Class userInputClass = Class.forName(className);
            boolean input = false;
            do {
                System.out.println("*******************************************************");
                System.out.println("Class Reader Application!");
                System.out.println("*******************************************************");
                System.out.println("1. Methods");
                System.out.println("2. Class");
                System.out.println("3. SubClasses");
                System.out.println("4. Parent classes");
                System.out.println("5. Constructors");
                System.out.println("6. Data Members");
                System.out.println("0 Exit");
                int opt = myInp.nextInt();
                switch (opt) {
                    case 1:
                        PrintWriter methodFile = new PrintWriter(new FileOutputStream("methods.txt", true));
                        Method[] methods = userInputClass.getMethods();
                        System.out.println("Methods");
                        for (Method method : methods) {
                            System.out.println(method);
                            methodFile.println(method);
                        }
                        methodFile.close();
                        input = true;
                        break;
                    case 2:
                        System.out.println("Class: " + userInputClass.getClass());
                        PrintWriter pwclassName = new PrintWriter(new FileOutputStream("Class.txt", true));
                        pwclassName.println(userInputClass.getClass());
                        pwclassName.close();
                        input = true;
                        break;
                    case 3:
                        PrintWriter pwsubclassName = new PrintWriter(new FileOutputStream("Subclassess.txt", true));
                        Class[] subClassess = userInputClass.getClasses();
                        for (Class subclassess : subClassess) {
                            System.out.println(subclassess);
                            pwsubclassName.println(subclassess);
                        }
                        pwsubclassName.close();
                        input = true;
                        break;

                    case 4:
                        PrintWriter parentClass = new PrintWriter(new FileOutputStream("Parent_Class.txt", true));
                        parentClass.println(userInputClass.getSuperclass());
                        System.out.println(userInputClass.getSuperclass());
                        parentClass.close();
                        input = true;
                        break;
                    case 5:
                        PrintWriter consFile = new PrintWriter(new FileOutputStream("Constructors.txt", true));
                        Constructor[] constructors = userInputClass.getConstructors();
                        for (Constructor constructor : constructors) {
                            System.out.println(constructor);
                            consFile.println(constructor);
                        }
                        consFile.close();
                        input = true;
                        break;
                    case 6:
                        PrintWriter fields = new PrintWriter(new FileOutputStream("Data members.txt", true));
                        Field[] field = userInputClass.getFields();
                        for (Field fieldsName : field) {
                            System.out.println(fieldsName);
                            fields.println(fieldsName);
                        }
                        fields.close();
                        input = true;
                        break;

                    case 0:
                        input = false;
                        exit(0);
                }
            } while (input);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }
}
