package com.natamus.villagernames.util;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.DataFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.villagernames.config.ConfigHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Names {
	public static List<String> customnames = null;
	
	public static void setCustomNames() throws IOException {
		String dirpath = DataFunctions.getConfigDirectory() + File.separator + "villagernames";
		File dir = new File(dirpath);
		File file = new File(dirpath + File.separator + "customnames.txt");
		
		if (dir.isDirectory() && file.isFile()) {
			String cn = Files.readString(Paths.get(dirpath + File.separator + "customnames.txt", new String[0]));
			cn = cn.replace("\n", "").replace("\r", "");
			
			String[] cns = cn.split(",");
			customnames = Arrays.asList(cns);
		}
		else {
			dir.mkdirs();
			
			PrintWriter writer = new PrintWriter(dirpath + File.separator + "customnames.txt", StandardCharsets.UTF_8);
			writer.println("Rick,");
			writer.println("Bob,");
			writer.println("Eve");
			writer.close();
			
			customnames = new ArrayList<>(Arrays.asList("Rick", "Bob", "Eve"));
		}
	}
	
	public static String getRandomName() {
		List<String> allnames = null;
		if (ConfigHandler._useFemaleNames && ConfigHandler._useMaleNames) {
			allnames = Stream.concat(GlobalVariables.femalenames.stream(), GlobalVariables.malenames.stream()).collect(Collectors.toList());
		}
		else if (ConfigHandler._useFemaleNames) {
			allnames = GlobalVariables.femalenames;
		}
		else if (ConfigHandler._useMaleNames) {
			allnames = GlobalVariables.malenames;
		}
		
		if (ConfigHandler._useCustomNames && customnames != null) {
			if (allnames == null) {
				allnames = customnames;
			}
			else {
				allnames = Stream.concat(customnames.stream(), allnames.stream()).collect(Collectors.toList());
			}
		}
		
		if (allnames == null) {
			return "";
		}
		
		String name = allnames.get(GlobalVariables.random.nextInt(allnames.size())).toLowerCase();
		return StringFunctions.capitalizeEveryWord(name);
	}
}
