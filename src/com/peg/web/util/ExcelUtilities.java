package com.peg.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * excel导出工具类
 * @author song
 *
 */
public class ExcelUtilities {
	
	/**
	 * 生产excel
	 * @param commentlist
	 * @param report
	 * @param fname
	 * @throws Exception
	 */
	public static void toExcelFileByArray(String[] title ,List<String[]> list, String fname) throws Exception {		
		// 判断该文件是否存在，若存在，则将该文件重命名
		String fileDir = fname;
		File excelFile = new File(fileDir);
		backupFile(excelFile);
		
		int[] headerWidth = new int[title.length]; //头宽度
		int[] contentWidth = new int[title.length]; //列宽度
		WritableWorkbook workBook = null;
		WritableSheet sheet = null;
		try{
			
			workBook = Workbook.createWorkbook(excelFile);
			sheet = workBook.createSheet("首页", 0);
			sheet.setRowView(0, 700); //对标题栏的高度进行设定
			
			//设置头部字体格式
	        WritableFont font =new WritableFont(WritableFont.createFont("宋体"), 12);  
	        WritableCellFormat headerFormat = new WritableCellFormat(font);
	        headerFormat.setBackground(Colour.GRAY_25);
	    	headerFormat.setAlignment(Alignment.CENTRE);//水平对齐
	    	headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直对齐
	    	headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//边框
	    	headerFormat.setWrap(false);//不自动换行
	    	
	    	
	    	//设置内容字体格式
	        WritableFont font2 =new WritableFont(WritableFont.createFont("宋体"), 11);  
	        WritableCellFormat contentFormat = new WritableCellFormat(font2);
	        contentFormat.setAlignment(Alignment.LEFT);//水平对齐
	    	contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直对齐
	    	contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);//边框
	    	contentFormat.setWrap(false);//不自动换行
	    	
			// 添加标题
			for (int i = 0; i < title.length; i++) {
				String header = title[i];
				headerWidth[i] = calculateWidth(header); //存入到头部
				sheet.addCell(new Label(i, 0, header,headerFormat));
			}
			
			// 添加数据
			for (int row = 0; row < list.size(); row++) {
				String[] datas = list.get(row);
				for (int column = 0; column < title.length ; column++) {
					if(row==0){//如果是第一行内容，需要根据内容的长度设置 单元格宽度
						//System.out.println(column+" "+datas[column]);
						contentWidth[column] = calculateWidth(datas[column]); //存入到内容
						if(contentWidth[column]<8){
							contentWidth[column]=8;
						}
					}
					sheet.addCell(new Label(column, row + 1, datas[column],contentFormat));
				}
			}
			int[] columnsWidth = getColumnWidth(headerWidth, contentWidth);
			if(columnsWidth==null){
				throw new Exception("Excel格式宽度异常！");
			}
			for (int i = 0; i < columnsWidth.length; i++)
			{
				sheet.setColumnView(i, columnsWidth[i]);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("报表导出异常！");
		}finally{
			workBook.write();
			workBook.close();
		}
	}
	
	
	/**
	 * 判断该文件是否存在，若存在，则将该文件重命名
	 */
	private static void backupFile(File excelFile) {
		if (excelFile.exists()) {
			String fileBakDir = excelFile.getAbsolutePath() + ".bak";
			File excelBakFile = new File(fileBakDir);
			if (excelBakFile.exists()) {
				excelBakFile.delete();
			}
			excelFile.renameTo(excelBakFile);
		}
	}
	
	/**
	 * 根据内容计算宽度
	 * @param content
	 * @return
	 */
	private static int calculateWidth(String content){
		float width = 0;
		int width2 = 0;
		if(content==null){
			return 0;
		}
		for (int i = 0; i < content.length(); i++)
		{
			char c = content.charAt(i);
			if(isChineseChar(c)){
				width += 2 ;
			}else{
				width += 1 ;
			}
		}
		width2 = (int)(width*1.5);
		return width2;
	}
	
	/**
	 * 判断是否中文
	 * @param str
	 * @return
	 */
	private static boolean isChineseChar(char c){
		boolean result = false;
		if (c >= 19968 && c <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
	        result = true;
	    }
	    return result;
   }
	
	/**
	 * 获取列的格式宽度
	 * @param headers
	 * @param contents
	 * @return
	 */
	private static int[] getColumnWidth(int[]headers , int[]contents){
		if(headers==null || headers.length<=0 || 
			contents==null || contents.length<=0 ){
			return null;
		}
		int[] columnsWidth = new int[headers.length];
		for (int i = 0; i < headers.length; i++)
		{
			columnsWidth[i] = (headers[i]-contents[i])>=0 ? headers[i]:contents[i];
		}
		return columnsWidth;
	}
	
	/**
	 * 下载Excel
	 * 
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void downloadExcel(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String downLoadPath = storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();

		// 删除对应的文件
		File f = new File(downLoadPath);
		if (f.exists())
			f.delete();
	}

}
