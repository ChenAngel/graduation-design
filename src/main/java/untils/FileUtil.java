package untils;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import chenangel.graduationdesign.admin.LibConst;
import chenangel.graduationdesign.generator.model.Reader;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class FileUtil {
    //文件上传工具类
    public static void uploadFile(byte[] file,String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //EXCEL文件解析
    public static List<Reader> addreaderbyexcel(String path){
        jxl.Workbook readwb = null;
        try
        {
            List<Reader> readers = new LinkedList<>();
            //构建Workbook对象, 只读Workbook对象
            //直接从本地文件创建Workbook
            InputStream instream = new FileInputStream(path);
            readwb = Workbook.getWorkbook(instream);
            //Sheet的下标是从0开始
            //获取第一张Sheet表
            Sheet readsheet = readwb.getSheet(0);
            //获取Sheet表中所包含的总列数
            int rsColumns = readsheet.getColumns();
            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();
            //获取指定单元格的对象引用
            for (int i = 1; i < rsRows; i++) {
                Reader reader = new Reader();
                for (int j = 0; j < rsColumns; j++) {
                    Cell cell = readsheet.getCell(j, i);
                    String content = cell.getContents();
                    if (j==0) {
                        reader.setReaderid(content);
                    }
                    if (j==1) {
                        if (content.equals("")) {
                            reader.setPassword(LibConst.default_passowrd);
                        }else{
                            reader.setPassword(content);
                        }
                    }
                    if (j==2) {
                        reader.setReadername(content);
                    }
                    if (j==3) {
                        reader.setReaderclass(content);
                    }
                    if (j==4) {
                        reader.setBorrowhistory(content);
                    }
                    if (j==5) {
                        reader.setSex(content);
                    }
                    if (j==6) {
                        reader.setBirthday(content);
                    }
                    if (j==7) {
                        reader.setIdentification(content);
                    }
                    if (j==8) {
                        reader.setTel(content);
                    }
                }
                readers.add(reader);
            }
            return readers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readwb.close();
        }
        return null;
    }
}
   /* //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
    jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File(
            "F:/红楼人物1.xls"), readwb);
    //读取第一张工作表
    jxl.write.WritableSheet ws = wwb.getSheet(0);
    //获得第一个单元格对象
    jxl.write.WritableCell wc = ws.getWritableCell(0, 0);
//判断单元格的类型, 做出相应的转化
            if (wc.getType() == CellType.LABEL) {
                    Label l = (Label) wc;
                    l.setString("新姓名");
                    }
                    //写入Excel对象
                    wwb.write();
                    wwb.close();
*/