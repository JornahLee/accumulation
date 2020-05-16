package com.jornah.work.excel.easyexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;

public class AliEasyExcelUtil {
    public static void main(String[] args) throws Exception {
        File file=new File("D:\\myWinBat\\EasyExcelDemo.xlsx");
        if(!file.exists()) {
            file.createNewFile();
        }
        InputStream in=new FileInputStream(file);
        OutputStream out=new FileOutputStream(file);
        AnalysisEventListener<ExcelPropertyIndexModel> listener=new AnalysisEventListener<ExcelPropertyIndexModel>() {

            @Override
            public void invoke(ExcelPropertyIndexModel object, AnalysisContext context) {
                System.out.println("Row: "+context.getCurrentRowNum()+" Data: "+object.toString());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("doAfterAllAnalysed...");
            }
        };
        System.setProperty("java.io.tmpdir","D:\\myWinBat\\EasyExcelTempDir");
        ExcelReader excelReader=new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
        excelReader.read(new Sheet(1,0,ExcelPropertyIndexModel.class));
//        new Sheet().getHead()
        excelReader.finish();
        
        
        ExcelWriter er=new ExcelWriter(out, ExcelTypeEnum.XLSX);
//        er.write(data, sheet, table)
    }
    /**
     * @author P1323153
     *
     */
    public static class ExcelPropertyIndexModel extends BaseRowModel{
        @ExcelProperty(value="姓名",index=0)
        private String name;
        
        @ExcelProperty(value="年龄",index=1)
        private String age;
        
        @ExcelProperty(value="邮箱",index=2)
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "[name=" + name + ", age=" + age + ", email=" + email + "]";
        }
        
    }
    

}
