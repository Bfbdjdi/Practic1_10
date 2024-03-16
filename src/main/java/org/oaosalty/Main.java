package org.oaosalty;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String summarizer(ArrayList<byte[]> arrayToProcess) //объединяет содержимые файлов
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int num = 0;
        try
        {
            for (byte[] data : arrayToProcess) { num++; output.write(data); output.write(32/*Знак пробела*/); }
        }
        catch (IOException err) //вероятность вознкновения ошибки КРАЙНЕ МАЛА
        {
            System.out.println("Файл #" + num + "содержит битые данные. Указанные файлы не могут быть объединены.\n" + err.getMessage());
        }

        return output.toString();
    }

    public static void main(String[] args) {

        System.out.println("Белин Георгий Алексеевич, РИБО-01-22, Вариант 1\n");
        Scanner scan = new Scanner(System.in);

        int numOfFilesToMerge;

        while (true) //Ввод числа файлов для использования
        {
            System.out.println("Сколько файлов необходимо склеить?");
            String strNumOfFilesToMerge = scan.nextLine();

            if (!IsItNum.Check(strNumOfFilesToMerge))
            {
                System.out.println("Было введено не число. Повторите ввод. \n");
                continue;
            }

            numOfFilesToMerge = Integer.parseInt(strNumOfFilesToMerge);
            break;
        }

        ArrayList<String> filePaths = new ArrayList<>(); //сюда поместить пути к файлам
        int pathsEntered = 1; //счётчик количества введённых ссылок

        while (pathsEntered <= numOfFilesToMerge) //собираем список путей ко всем файлам
        {
            System.out.println("Введите полный путь к файлу #" + pathsEntered + ":");
            String enteredPath = scan.nextLine();

            if (FileUtils.fileExistenceChecker(enteredPath))
            {
                filePaths.add(enteredPath);
                pathsEntered += 1;
            }
            else
            {
                System.out.println("Файл не найден. Путь к нему точно введён правильно?\n");
            }
        }

        ArrayList<byte[]> dataToProcess = new ArrayList<>(); //список, в котором аккуратно лежат данные из файлов

        int num = 0; //счётчик, чтобы при ошибке мог вывестись номер бракованного файла
        for (String data : filePaths) //копируем данные из файлов в /|\ массив
        {
            num += 1;
            try
            {
                byte[] dataFromASingleFile = FileUtils.readBytesFromFile(data);
                dataToProcess.add(dataFromASingleFile);
            }
            catch (IOException err)
            {
                System.out.println("При чтении файла #" + num + " возникла ошибка: " + err.getMessage());
            }
        }

        String unitedData = summarizer(dataToProcess); //содержимое всех файлов в одном String
        FileUtils.writeStringToFile(filePaths.get(0)/*путь к первому указанному файлу*/, unitedData);

        System.out.println("Готово.");
    }
}