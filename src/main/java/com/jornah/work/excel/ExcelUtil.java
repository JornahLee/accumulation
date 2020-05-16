package com.jornah.work.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    private static DecimalFormat df = new DecimalFormat("0");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DecimalFormat nf = new DecimalFormat("0");
//    private static DecimalFormat nf = new DecimalFormat("0.00");

    public static ArrayList<ArrayList<Object>> readExcel(File file, int sheetIndex) {
        if (file == null) {
            return null;
        }
        if (file.getName().endsWith("xlsx")) {

            return readExcel2007(file, sheetIndex);
        } else {

            return readExcel2003(file, sheetIndex);
        }
    }

    public static ArrayList<ArrayList<Object>> readExcel(File file) {
        if (file == null) {
            return null;
        }
        if (file.getName().endsWith("xlsx")) {

            return readExcel2007(file, 0);
        } else {

            return readExcel2003(file, 0);
        }
    }

    /*
     * @return 将返回结果存储在ArrayList内，存储结构与二位数组类似
     * lists.get(0).get(0)表示过去Excel中0行0列单元格
     */
    public static ArrayList<ArrayList<Object>> readExcel2003(File file, int sheetIndex) {
        try {
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
            ArrayList<Object> colList;
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);
            HSSFRow row;
            HSSFCell cell;
            Object value;
            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                colList = new ArrayList<Object>();
                if (row == null) {

                    if (i != sheet.getPhysicalNumberOfRows()) {
                        rowList.add(colList);
                    }
                    continue;
                } else {
                    rowCount++;
                }
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

                        if (j != row.getLastCellNum()) {
                            colList.add("");
                        }
                        continue;
                    }
                    switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        System.out.println(i + "行" + j + " 列 is String type");
                        value = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        System.out.println(i + "行" + j + " 列 is Number type ; DateFormt:" + value.toString());
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        System.out.println(i + "行" + j + " 列 is Boolean type");
                        value = Boolean.valueOf(cell.getBooleanCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        System.out.println(i + "行" + j + " 列 is Blank type");
                        value = "";
                        break;
                    default:
                        System.out.println(i + "行" + j + " 列 is default type");
                        value = cell.toString();
                    }
                    colList.add(value);
                }
                rowList.add(colList);
            }

            return rowList;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<ArrayList<Object>> readExcel2007(File file, int SheetIndex) {
        try {
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
            ArrayList<Object> colList;
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = wb.getSheetAt(SheetIndex);
            XSSFRow row;
            XSSFCell cell;
            Object value;
            int readRows=sheet.getPhysicalNumberOfRows();
            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount <readRows ; i++) {
                row = sheet.getRow(i);
                colList = new ArrayList<Object>();
                if (row == null) {

                    if (i != sheet.getPhysicalNumberOfRows()) {
                        rowList.add(colList);
                    }
                    continue;
                } else {
                    rowCount++;
                }
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

                        if (j != row.getLastCellNum()) {
                            colList.add("");
                        }
                        continue;
                    }
                    switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        value = Boolean.valueOf(cell.getBooleanCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                    }
                    colList.add(value);
                }
                rowList.add(colList);
            }

            return rowList;
        } catch (Exception e) {
            System.out.println("exception");
            return null;
        }
    }

    public static void writeExcel(ArrayList<ArrayList<Object>> result, String path, int sheetIndex) throws Exception {
        if (result == null) {
            return;
        }
        File file = new File(path);
        XSSFWorkbook wb = new XSSFWorkbook();
        wb.createSheet("sheet1");

        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
        int ii=0;
        int jj=0;
        try {
            for (int i = 0; i < result.size(); i++) {
                XSSFRow row = sheet.createRow(i);
                if (result.get(i) != null) {
                    for (int j = 0; j < result.get(i).size(); j++) {
                        ii=i;
                        jj=j;
                        XSSFCell cell = row.createCell(j);
                        if(result.get(i).get(j)!=null) {
                            cell.setCellValue(result.get(i).get(j).toString());
                        }else {
                            cell.setCellValue("");
                        }
                    }
                }
            }
        }catch(Exception e) {
            System.out.println(ii);
            System.out.println(jj);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @param target cell has been transformed to String
     * 
     * @param str targetStr contains str (support regex)
     */
    public static int[][] findString(ArrayList<ArrayList<Object>> target, String str, int[] desCols) {
        if (str == null||"".equals(str.trim())) {
            return null;
        }
//        String regex="\\bom_\\w+\\b";
        String regex=str;
        Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher=null;
        int[][] res = new int[300][2];
        int size = 0;
        //find all row
        for (int i = 0; i < target.size(); i++) {
            //find in this cells in current row and right col
            for (int j = 0; j < target.get(i).size(); j++) {
                for (int t = 0; t < desCols.length; t++) {
                  //if current col in desCols  then find in this col
                    if (desCols[t] == j) {
                        String obj = target.get(i).get(j).toString();
//                        System.out.println("match col's cell :"+obj);
                        matcher=pattern.matcher(obj);
                        boolean isFound=matcher.find();
                        try {
                            
                            if (isFound) {
                                res[size][0] = i;
                                res[size][1] = j;
                                System.out.println(size+":"+obj);
                                size++;
                                System.out.println();
                            }
                        }catch(Exception e) {
                            System.out.println(size);
                        }
                    }
                }
 
            }
        }
        if(size==0) {
            return null;
        }
        return res;
    }

    public static DecimalFormat getDf() {
        return df;
    }

    public static void setDf(DecimalFormat df) {
        ExcelUtil.df = df;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        ExcelUtil.sdf = sdf;
    }

    public static DecimalFormat getNf() {
        return nf;
    }

    public static void setNf(DecimalFormat nf) {
        ExcelUtil.nf = nf;
    }

}