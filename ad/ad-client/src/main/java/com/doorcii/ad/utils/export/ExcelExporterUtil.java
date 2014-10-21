package com.doorcii.ad.utils.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 导出xml文件
 * @author Jacky
 */
public class ExcelExporterUtil {
	
	private static final String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
	
	public static void exportExcel(Collection<?> dataSet,Class<?> clazz,OutputStream os) throws Exception {
		exportExcel(null,dataSet,clazz,os);
	}
	
	public static void exportExcel(String [] headers,Collection<?> dataSet,Class<?> clazz,OutputStream os) throws Exception {
		exportExcel(null,headers,dataSet,defaultDatePattern,clazz,os);
	}
	
	public static void exportExcel(String title , String [] headers,Collection<?> dataSet,Class<?> clazz,OutputStream os) throws Exception {
		exportExcel(title,headers,dataSet,defaultDatePattern,clazz,os);
	}
	
	public static void exportExcel(String title,String [] headers,Collection<?> dataSet,String datePattern,Class<?> clazz,OutputStream os) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth(15);
		HSSFCellStyle style = createDefaultTitleStyle(workbook);
	    HSSFFont font = createDefaultTitleFont(workbook);
	    style.setFont(font);
	    HSSFCellStyle style2 = createDefaultBodyStyle(workbook);
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        style2.setFont(font2);
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
	        HSSFCell cell = row.createCell(i);
	        cell.setCellStyle(style);
	        HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	        cell.setCellValue(text);
        }
 
        Iterator<?> it = dataSet.iterator();
        int index = 0;
        while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         Object t = (Object) it.next();
	         Field[] fields = t.getClass().getDeclaredFields();
	         for (int i = 0; i < fields.length; i++) {
	            HSSFCell cell = row.createCell(i);
	            cell.setCellStyle(style2);
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get"
	                   + fieldName.substring(0, 1).toUpperCase()
	                   + fieldName.substring(1);
	            if(field.getType().equals(boolean.class)) {
	            	getMethodName = "is"
	 	                   + fieldName.substring(0, 1).toUpperCase()
	 	                   + fieldName.substring(1);
	            }
                Class<?> tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                      new Class[] {});
                Object value = getMethod.invoke(t, new Object[] {});
                String textValue = null;
                if (value instanceof Boolean) {
                    boolean bValue = (Boolean) value;
                    textValue = String.valueOf(bValue);
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                    textValue = sdf.format(date);
                }  else if (value instanceof byte[]) {
                    row.setHeightInPoints(60);
                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                         1023, 255, (short) 6, index, (short) 6, index);
                    anchor.setAnchorType(2);
                } else{
                    textValue = value.toString();
                }
                if(textValue!=null){
                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                   Matcher matcher = p.matcher(textValue);
                   if(matcher.matches()){
                      cell.setCellValue(Double.parseDouble(textValue));
                   }else{
                      HSSFRichTextString richString = new HSSFRichTextString(textValue);
                      HSSFFont font3 = workbook.createFont();
                      font3.setColor(HSSFColor.BLUE.index);
                      richString.applyFont(font3);
                      cell.setCellValue(richString);
                   }
                }
	       }
      }
	  workbook.write(os);
	}
	
	public static void exportExcelWithPro(Properties pro,OutputStream os) {
		
	}
	
	private static HSSFFont createDefaultTitleFont(HSSFWorkbook workbook) {
	   HSSFFont font = workbook.createFont();
       font.setColor(HSSFColor.VIOLET.index);
       font.setFontHeightInPoints((short) 12);
       font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       return font;
	}
	
	private static HSSFCellStyle createDefaultTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    return style;
	}
	
	private static HSSFCellStyle createDefaultBodyStyle(HSSFWorkbook workbook) {
	   HSSFCellStyle style = workbook.createCellStyle();
	   style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	   style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	   style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	   style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	   style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	   style.setBorderTop(HSSFCellStyle.BORDER_THIN);
       style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       return style;
	}
	
	 public static void main(String[] args) throws Exception {
	      // 测试学生
	      String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
	      List<Student> dataset = new ArrayList<Student>();
	      dataset.add(new Student("10000001", "张三", 20, true, new Date()));
	      dataset.add(new Student("20000002", "李四", 24, false, new Date()));
	      dataset.add(new Student("30000003", "王五", 22, true, new Date()));
	      try {
	 
	         OutputStream out = new FileOutputStream("E://a.xls");
	         ExcelExporterUtil.exportExcel("sheet name",headers, dataset,Student.class, out);
	         out.close();
	         System.out.println("excel导出成功！");
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
}
