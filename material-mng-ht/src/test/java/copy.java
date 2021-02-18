import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;

public class copy {

    @Test
    public void test(){
        String oldPath = "D:\\ZM\\新建文件夹";
        String newpath = "D:\\ZM";
        File file = new File(oldPath);
        //文件名称列表
        String[] filePath = file.list();

        if (!(new File(newpath)).exists()) {
            (new File(newpath)).mkdir();
        }

        for (int i = 0; i < filePath.length; i++) {
            File fileOld = new File(oldPath+file.separator+filePath[i]);
            File fileNew = null;
            if (i<10){
                fileNew = new File(newpath+file.separator+"0"+i+"day.jpg");
            }else {
                fileNew = new File(newpath+file.separator+i+"day.jpg");
            }
            try {
                FileUtils.copyFile(fileOld, fileNew);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------------------");
    }

    public  void copyFile(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(oldFile);
            out = new FileOutputStream(file);
            byte[] buffer=new byte[2097152];
            while((in.read(buffer)) != -1){
                out.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
