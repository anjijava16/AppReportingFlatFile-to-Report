package com.iwinner.wap.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WapConstants {
	private static ResourceBundle CONFIG = ResourceBundle
			.getBundle("com.iwinner.wap.utils.config");

	// NDMNS File Processing Info
	public static String SUCCESS_WRITE_NDMNS_LOG = "";
	public static String FAIL_WRITE_NDMNS_LOG = "";
	public static String INPUT_FOLDER_PATH = "";
	public static String SUCCESS_EXCEL_FILE = "";
	public static String ERROR_EXCEL_FILE = "";
	public static String YEAR_START = "2015";
	public static Integer TOTAL_DATE_UPTO = 20;
	public static String SEMICOLON_SEPARATION = ";";
	public static String EMPTY_SEPARATION = "";
	public static String NULL = null;
	public static Integer ZERO = 0;
	public static Integer ONE = 0;
	public static Integer TWO = 0;
	public static Integer THREEE = 0;
	public static String OPERATION_START_FROM = "OperationName :";
	public static String REQUEST_COMMONAPI = "OperationName : RetrieveCommonApiRequest[";
	public static String SUCCESS_COMMONAPI = "SUCCESS";
	public static String ERROR_COMMONAPI = "ERROR";
	public static String EMPTY_SPACE = " ";
	public static String OPID_SPOTIFY = "opId=2";
	public static String END_BRACKET = "]";

	static {
		INPUT_FOLDER_PATH = CONFIG.getString("INPUT_FOLDER_PATH");
		SUCCESS_EXCEL_FILE = CONFIG.getString("SUCCESS_EXCEL_FILE");
		ERROR_EXCEL_FILE = CONFIG.getString("ERROR_EXCEL_FILE");

	}
	public static List<String> headerInfo = new ArrayList<String>();
	static {
		headerInfo.add("ProcessingTime");
		headerInfo.add("OpId");
		headerInfo.add("MethodId");
		headerInfo.add("MSISDN");
		headerInfo.add("ReturnMessage");
	}

	public static void main(String[] args) {
		System.out.println(WapConstants.INPUT_FOLDER_PATH);
	}
}
