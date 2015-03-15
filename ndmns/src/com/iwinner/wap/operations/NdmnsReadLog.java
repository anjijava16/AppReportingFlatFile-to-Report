package com.iwinner.wap.operations;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.iwinner.wap.utils.WapConstants;

public class NdmnsReadLog {
	public static Logger logger=Logger.getLogger(NdmnsReadLog.class);
	public static Object startFileReading() {
	logger.info("Enter into the startFileReading() ");	
		try {
			List<NdmnsLogForm> listofContent=new ArrayList<NdmnsLogForm>();
			List<NdmnsLogForm> listofContentErrors=new ArrayList<NdmnsLogForm>();
			//File Read Operation
             List<String> filePathName=listOfFiles();
             for(String fileName:filePathName){
            	 System.out.println(fileName+new Date());
			File fileRead = new File(fileName);
			BufferedReader br=new BufferedReader(new FileReader(fileRead));
			String line=WapConstants.EMPTY_SEPARATION;
			while((line=br.readLine())!=WapConstants.NULL){
				//Split the Line 
				String lineByLine[]=line.split(WapConstants.SEMICOLON_SEPARATION);
				//Finding the date from the lineByLine[0]
				String insideTheLineOne=lineByLine[WapConstants.ZERO];
				//System.out.println(lineByLine[1]);
                String dateOfFileProcessingTime=insideTheLineOne.substring(insideTheLineOne.indexOf(WapConstants.YEAR_START),WapConstants.TOTAL_DATE_UPTO);
                // Get the OperationName
                String operationInfoStartFrom=lineByLine[WapConstants.ONE];
                NdmnsLogForm ndmnsLogForm=new NdmnsLogForm();
                String operationName= operationInfoStartFrom.substring(operationInfoStartFrom.indexOf(WapConstants.OPERATION_START_FROM), operationInfoStartFrom.length());
                if(operationName.startsWith(WapConstants.REQUEST_COMMONAPI)){
                	String retrieveCommonApi=operationName.substring(operationName.indexOf("opId"),operationName.lastIndexOf(WapConstants.END_BRACKET));
                	String retrieveCommonObs[]=retrieveCommonApi.split(WapConstants.EMPTY_SPACE);
                	if(retrieveCommonObs[0].equals(WapConstants.OPID_SPOTIFY)&&lineByLine[1].contains(WapConstants.SUCCESS_COMMONAPI)){
                		ndmnsLogForm.setDateOfAccess(dateOfFileProcessingTime);
                		ndmnsLogForm.setOpId(retrieveCommonObs[0]);
                		ndmnsLogForm.setMethodId(retrieveCommonObs[1]);
                		ndmnsLogForm.setMsisdn(retrieveCommonObs[2]);
                		ndmnsLogForm.setReturnMessage(lineByLine[1]);
                		System.out.println("Getting the FileData opId=["+retrieveCommonObs[0]+"], MethodID [="+retrieveCommonObs[1]+"],Msisdn [="+retrieveCommonObs[2]+"],getting Data Of AccessTime [="+dateOfFileProcessingTime+"]");
                		logger.debug("Getting the FileData opId=["+retrieveCommonObs[0]+"], MethodID [="+retrieveCommonObs[1]+"],Msisdn [="+retrieveCommonObs[2]+"],getting Data Of AccessTime [="+dateOfFileProcessingTime+"]");
                		listofContent.add(ndmnsLogForm);
                		ReportGenerationOperation.buildExcel(WapConstants.headerInfo, listofContent,WapConstants.SUCCESS_EXCEL_FILE );
                	}
                	if(retrieveCommonObs[0].equals(WapConstants.OPID_SPOTIFY)&&lineByLine[1].contains(WapConstants.ERROR_COMMONAPI)){
                		ndmnsLogForm.setDateOfAccess(dateOfFileProcessingTime);
                		ndmnsLogForm.setOpId(retrieveCommonObs[0]);
                		ndmnsLogForm.setMethodId(retrieveCommonObs[1]);
                		ndmnsLogForm.setMsisdn(retrieveCommonObs[2]);
                		ndmnsLogForm.setReturnMessage(lineByLine[1]);
                		listofContentErrors.add(ndmnsLogForm);
                		ReportGenerationOperation.buildExcel(WapConstants.headerInfo, listofContentErrors,WapConstants.ERROR_EXCEL_FILE );
                	}
                }
			}
			}
             
		} catch (Exception e) {
			logger.error("Error  into the startFileReading() "+e.getMessage());
              e.printStackTrace();
		}
		logger.info("Exit into the startFileReading() ");
		return null;
	}
	public static  List<String> listOfFiles(){
		logger.info("Enter Into the listOfFiles() ");
		List<String> filePath=new ArrayList<String>();
		File inputFolder=new File(WapConstants.INPUT_FOLDER_PATH);
		logger.debug("Inside the listOffiles( ) fileName is "+inputFolder);
        for(File filePathName:inputFolder.listFiles()){
        	logger.debug("Inside the listOffiles( ) geting the getAbsoultPath  fileName is "+filePathName.getAbsolutePath());
        	filePath.add(filePathName.getAbsolutePath());
        }
        logger.info("Exit Into the listOfFiles() ");
		return filePath;
	}
}
