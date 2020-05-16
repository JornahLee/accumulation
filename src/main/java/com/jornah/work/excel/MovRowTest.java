package com.jornah.work.excel;

import java.io.File;
import java.util.ArrayList;

public class MovRowTest {
    public static void main(String[] args)  {
        String file1 = "D:\\myWinBat\\excelBatInput\\test.xlsx";
        File file=new File(file1);
        ArrayList<ArrayList<Object>> fileRes = ExcelUtil.readExcel(file, 0);
        FindJobFlow fj=new FindJobFlow();
        //mov 5 to 1
        ArrayList<Object> row = fileRes.get(0);
//        ArrayList<Object> row = fileRes.get(4);
//        fj.movRow(fileRes,row,4,0);
        
        fj.movRow(fileRes,row,0,4);
        try {
            ExcelUtil.writeExcel(fileRes, file1, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
