package com.iwinner.wap.operations;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ReportGenerationOperation {
	public  static Logger logger=Logger.getLogger(ReportGenerationOperation.class);
	public static void buildExcel(List<String> rowOfLogsInfo, List listOfMsisdn,String fileName)
			throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		logger.info("Enter Into the buildExcel "+new Date());
		try {
			String reportName= fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
			logger.info("SpotifyFileName "+reportName);
			HSSFSheet sheet = wb.createSheet("ErrorReport");
			HSSFCellStyle headstyle = wb.createCellStyle();
			HSSFCellStyle rowsstyle = wb.createCellStyle();
			HSSFFont fhead = wb.createFont();
			HSSFFont frows = wb.createFont();
			fhead.setFontHeightInPoints((short) 10);
			fhead.setColor((short) HSSFColor.BROWN.index);
			fhead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			fhead.setFontName("Verdana");
			headstyle.setFont(fhead);
			headstyle.setFillBackgroundColor(HSSFColor.GREY_50_PERCENT.index);
			frows.setFontHeightInPoints((short) 8);
			frows.setFontName("Verdana");
			rowsstyle.setFont(frows);
			HSSFRow row = sheet.createRow(0);
			if (rowOfLogsInfo != null) {
				for (int i = 0; i < rowOfLogsInfo.size(); i++) {
					HSSFCell cell = row.createCell((short) i);
					cell.setCellStyle(headstyle);
					HSSFRichTextString str = new HSSFRichTextString(
							(String) rowOfLogsInfo.get(i));
					cell.setCellValue(str);
				}
			}

			// select id,HOSTNAME,VISTORRANID,ipAddress,viewDateAndTime,browser
			// from vistior order by viewDateAndTime desc LIMIT 120
			if (listOfMsisdn != null) {
				NdmnsLogForm ndmnsLog = null;
				for (int i = 0; i < listOfMsisdn.size(); i++) {
					row = sheet.createRow(i + 1);
					ndmnsLog = (NdmnsLogForm) listOfMsisdn.get(i);

					HSSFCell cell0 = row.createCell((short) 0);
					cell0.setCellStyle(rowsstyle);
					HSSFRichTextString str0 = new HSSFRichTextString(
							(String) ndmnsLog.getDateOfAccess());
					cell0.setCellValue(str0);

					HSSFCell cell1 = row.createCell((short) 1);
					cell1.setCellStyle(rowsstyle);
					HSSFRichTextString str1 = new HSSFRichTextString(
							(String) ndmnsLog.getOpId());
					cell1.setCellValue(str1);

					HSSFCell cell2 = row.createCell((short) 2);
					cell2.setCellStyle(rowsstyle);
					HSSFRichTextString str2 = new HSSFRichTextString(
							(String) ndmnsLog.getMethodId());
					cell2.setCellValue(str2);

					HSSFCell cell3 = row.createCell((short) 3);
					cell3.setCellStyle(rowsstyle);
					HSSFRichTextString str3 = new HSSFRichTextString(
							(String) ndmnsLog.getMsisdn());
					cell3.setCellValue(str3);

					HSSFCell cell4 = row.createCell((short) 4);
					cell4.setCellStyle(rowsstyle);
					HSSFRichTextString str4 = new HSSFRichTextString(
							(String) ndmnsLog.getReturnMessage());
					cell4.setCellValue(str4);

			logger.debug("Inside the file  DateOFAccess[="+ndmnsLog.getDateOfAccess()+"],getOpId [="+(String) ndmnsLog.getOpId()+"],getMethodId [="
					+(String) ndmnsLog.getMethodId()+"],getMsisdn [="+(String) ndmnsLog.getMsisdn()+"], getReturnMessage [="+(String) ndmnsLog.getReturnMessage()+"]");		
				}
				Date date =new Date();
				 
				//String currentTime=new SimpleDateFormat("ddmmyyyy_hh_mm_ss").format(date);
				File file=new File(fileName+".xls");
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				fos.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error into the buildExcel () "+e.getMessage()); 
		}
		logger.info("Exit Into the buildExcel "+new Date());
	}
	
}
