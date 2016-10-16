package com.prisbox.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
	public static List<NAV> parse(String rawData){
		List<NAV> list = new ArrayList<NAV>();
		String trPattern = "<tr>(.*?)</tr>";
		Pattern tr = Pattern.compile(trPattern);
		Matcher mtr = tr.matcher(rawData);
		while(mtr.find()){
			String temp = mtr.group();
			NAV nav = new NAV();
			nav.setDate(temp.substring(8, 18));
			nav.setNetAssetValue(temp.substring(44, 50));
			nav.setHistoricalNetValue(temp.substring(76, 82));
			int span = 5;
//			System.out.println(temp.indexOf(112));
			if(temp.charAt(112)=='-'){
				span = 6;
			}
			nav.setDailyGrowthRate(temp.substring(112,112+span));
			list.add(nav);
			System.out.println(nav.toString());
		}
		return list;
	};
	public static void toFile(String file,List<NAV> data) throws Exception{
		File newFile = new File(file);
		if(newFile.exists()){
			throw new Exception("文件已存在");
		}else{
			newFile.createNewFile();
		}
		if(true){
			FileWriter fw = null;
			BufferedWriter writer = null;
			try {
				fw = new FileWriter(newFile);
				writer = new BufferedWriter(fw);
				for(NAV nav : data){
					writer.write(nav.toString());
					writer.newLine();
				}
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					writer.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rawData = "<tr>1</tr><tr>2</tr><tr>3</tr><tr>4</tr><tr>5</tr><tr>6</tr>";
		String trPattern = "<tr>(.*?)</tr>";
		Pattern tr = Pattern.compile(trPattern);
		Matcher mtr = tr.matcher(rawData);
		while(mtr.find()){
			String temp = mtr.group();
//			NAV nav = new NAV();
			System.out.println(temp);
		}
	}

}
