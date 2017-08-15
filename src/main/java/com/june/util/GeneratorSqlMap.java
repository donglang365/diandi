package com.june.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
  
  
public class GeneratorSqlMap {  
//	private  Logger logger = Logger.getLogger(this.getClass().getName());
  
    public void generator() throws Exception{  
    	 String path=this.getClass().getResource("/").getPath();
    	PropertyConfigurator.configure(path+"config\\log4j.properties");
//    	logger.info("aaaa");
        List<String> warnings = new ArrayList<String>();  
        boolean overwrite = true;         
        System.out.println(path);
        File configFile = new File(path+"config\\generatorConfig.xml");   
        ConfigurationParser cp = new ConfigurationParser(warnings);  
        Configuration config = cp.parseConfiguration(configFile);  
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,  
                callback, warnings);  
        myBatisGenerator.generate(null);  
  
  
    }   
    public static void main(String[] args) throws Exception {  
        try {  
            GeneratorSqlMap generatorSqlmap = new GeneratorSqlMap();  
            generatorSqlmap.generator();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
}