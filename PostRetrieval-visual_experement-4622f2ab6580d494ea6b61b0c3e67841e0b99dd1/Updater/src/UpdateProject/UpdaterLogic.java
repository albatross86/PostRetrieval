package UpdateProject;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergei on 17.06.2016.
 */
public class UpdaterLogic{

    private static final int BUFFER_SIZE = 1024;

    // путь к сохраненному архиву с обновлениями.
    String tempFiles = new File(".").getAbsolutePath() + "\\tempFolder\\";
    // путь к папке с программой.
    String pathToFiles = new File(".").getAbsolutePath(); // "C:\\Programming\\";
    // ссылка на файл с новой версией
    String linkVersionFile = "http://www.spacelore.dp.ua/programming/check_version.txt";

    File temp;

    public UpdaterLogic(){
        temp = new File(tempFiles);
        File files = new File(pathToFiles);
        if (!files.exists() || !temp.exists()){
            files.mkdirs();
            temp.mkdirs();
        }
    }


    /********************************************************************************/
    // метод для получения из .txt файла новой версии программы в первой строке
    // и ссылкой на новую версию во второй строке
    // возвращает список с новой версией и ссылкой на новую версию.
    public  List<String> readNewVersionFile(String urlStr) throws IOException{
        List<String> list = new ArrayList<String>();

            URL url = new URL(urlStr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            while (bufferedReader.read() != -1)
                list.add(bufferedReader.readLine());

            bufferedReader.close();
        return list;
    }


    /********************************************************************************/
    // метод для извлечения имени файла с новой версие й из ссылки на него.
    private String getFileName(String url){
        String name = url.substring(url.lastIndexOf('/')+1, url.lastIndexOf('.'));
        // добавляем файлу расширение и возвращаем.
        return name + ".exe";
    }

    /********************************************************************************/
    // метод для получения ссылки на новую версию из файла на сервере.
    // по полученной ссылке качает этот файл.
    void getNewVersion(List<String> newVersionList) throws IOException{
        String tmp = newVersionList.get(1);
        String url = tmp.substring(tmp.indexOf("[link]") + 6, tmp.indexOf("[/link]"));

        downloadUsingNIO(url, tempFiles);
    }

    /********************************************************************************/
    // метод для скачивания файла по ссылке и сохранения его с опеределенным именем
    private void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());

        FileOutputStream fos = new FileOutputStream(new File(file + getFileName(urlStr)));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }


    /********************************************************************************/
    // метод для удаления файлов и каталогов в каталогах
    void delete(File file){

        // если файл не существует
        if(!file.exists())
            return;
        // если это папка
        if(file.isDirectory())
        {
            for(File f : file.listFiles())
                delete(f);
            file.delete();
        }
            file.delete();

    }


    /********************************************************************************/
    // метод для копирования файлов.
    // метод нужно немного переписать, чтобы определиться с try-catch.
    // т.к., закрывать потоки лучше наверняка в finally.
    void copyNewVersion(File sourceLocation, File targetLocation) throws IOException {
        // если то, что копируем, директория.
        if (sourceLocation.isDirectory()) {
            // если путь, куда копируем, не создан.
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            // массив имен объектов, хранящихся в директории.
            String[] children = sourceLocation.list();
            for (String aChildren : children) {
                // рекурсивный вызов метода (для проверки isDirectory).
                copyNewVersion(new File(sourceLocation, aChildren),
                        new File(targetLocation, aChildren));
            }
        } else {
            // если то, что копируем, файлы.
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            // побитовое копирование из instream в outstream.
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // закрываем потоки.
            in.close();
            out.close();

            closeUpdater_startMainProgramm();
        }
    }

    private void closeUpdater_startMainProgramm() {

        try {
            Runtime.getRuntime().exec("CommaN.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}