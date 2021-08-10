package com.peg.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 文件读写工具类
 * @author lam
 *
 */
public class FileUtil {
    
    /**
     * 解析数据
     * @param item
     * @return
     * @throws Exception
     */
    public static List<String[]>  parseCsvExlFile(FileItem item) throws Exception 
    {
        String name = item.getName(); 
        if(StringUtils.isBlank(name))
        {
            return null;
        }
        //扩展名 
        String message = ""; 
        long size = item.getSize(); 
        if(size > (30*1024*1024)){ 
            message = "文件"+item.getName()+"大超过了30M，上传失败！"; 
            throw new Exception(message); 
        } 
        // 扩展名格式： 
        String extName = FilenameUtils.getExtension(name); 
        // 定义允许上传的文件类型 
        List<String> fileTypes = new ArrayList<String>(); 
        fileTypes.add("xls");
        fileTypes.add("xlsx");
        fileTypes.add("csv");
        if(!fileTypes.contains(extName.toLowerCase())){ 
            message = "只允许上传EXCEL或者csv文件"; 
            throw new Exception(message); 
        } 
        
        if (("csv").equals(extName))
        {
            return CsvFileUtil.readCsvFileStream(item.getInputStream(), null);
        }
        else
        {
            Map<String, List<String[]>> map = parseExcel(item);
            return  map.entrySet().iterator().next().getValue();
        }
    }
    
    /**
     * 解析上传的Excel文件
     * @param item
     * @return
     * @throws Exception
     */
    private static Map<String, List<String[]>>  parseExcel(FileItem item) throws Exception 
    {
        //文件名
        String name = item.getName(); 
        if(StringUtils.isBlank(name))
        {
            return null;
        }
        
        String extName = ""; 
        // 扩展名格式： 
        extName = FilenameUtils.getExtension(name); 
        
        try
        {
            Map<String, List<String[]>>  map =  ReadExcleUtil.readExcel(item.getInputStream(), extName);
            return map;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage()); 
        } 
        
    }
}
