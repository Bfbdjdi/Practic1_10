package org.oaosalty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
    public static byte[] readBytesFromFile(String path) throws IOException
    {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[fis.available()]; //available возвращает количество доступных для чтения байтов из входного потока
        fis.read(data);//чтение из потока в массив data
        fis.close(); //закрытие потока
        return data;
    }

    public static void writeStringToFile(String path, String data)
    {
        //Передаваемый путь содержит название первого файла. Нужно последнего убрать в конце строки, а потом
        //на его место добавить новое название
        for (int i = path.length() - 1; i > -1; i--) //убираем название файла, оставляя только путь к нему
        {
            if (path.charAt(i) == '\\')
            {
                path = path.substring(0, i+1);
                break;
            }
        }

        System.out.println("Какое у выходного файла должно быть название (без расширения)?");
        Scanner scan = new Scanner(System.in);
        String fileOutputName = scan.nextLine();

        try
        {
            FileWriter fw = new FileWriter(path + fileOutputName + ".txt");
            fw.write(data);
            fw.close();
        }
        catch (IOException err)
        {
            System.out.println("Произошла ошибка создания файла. Путь, случаем, не является защищённым системой? Не использованы ли недопустимые символы?\n" + err.getMessage());
        }
    }

    public static boolean fileExistenceChecker(String enteredPath)
    {
        File fileExistenceChecker = new File(enteredPath);
        return fileExistenceChecker.isFile();
    }
}
