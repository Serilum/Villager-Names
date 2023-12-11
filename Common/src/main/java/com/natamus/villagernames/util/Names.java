package com.natamus.villagernames.util;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.DataFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.villagernames.config.ConfigHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Names {
	public static List<String> customVillagerNames = new ArrayList<String>();
	
	public static void setCustomNames() throws IOException {
		String dirpath = DataFunctions.getConfigDirectory() + File.separator + "villagernames";
		File dir = new File(dirpath);
		File file = new File(dirpath + File.separator + "customnames.txt");
		
		if (dir.isDirectory() && file.isFile()) {
			Path customNamePath = Paths.get(dirpath + File.separator + "customnames.txt");
			String cn = Files.readString(customNamePath);

			if (StringFunctions.sequenceCount(cn, ",") == 2 && cn.contains("Rick") && cn.contains("Bob") && cn.contains("Eve")) { // old config
				FileChannel.open(customNamePath, StandardOpenOption.WRITE).truncate(0).close();
			}
			else {
				cn = cn.replace("\n", "").replace("\r", "").strip();

				String[] cns = cn.split(",");
				for (String n : cns) {
					String name = n.strip();

					if (!name.isEmpty()) {
						customVillagerNames.add(name);
					}
				}
			}
		}
		else {
			boolean ignored = dir.mkdirs();
			
			PrintWriter writer = new PrintWriter(dirpath + File.separator + "customnames.txt", StandardCharsets.UTF_8);
			writer.close();
		}
	}
	
	public static String getRandomName() {
		List<String> villagerNameList = new ArrayList<String>();

		if (ConfigHandler.useCustomNames && !customVillagerNames.isEmpty()) {
			if (ConfigHandler.useBothCustomAndDefaultNames) {
				villagerNameList.add(randomFromList(customVillagerNames));
			}
			else {
				String name = randomFromList(customVillagerNames);

				if (ConfigHandler.shouldCapitalizeNames) {
					return StringFunctions.capitalizeEveryWord(name);
				}
				return name;
			}
		}

		if (ConfigHandler.useDefaultFemaleNames) {
			villagerNameList.add(randomFromList(GlobalVariables.femaleNames));
		}

		if (ConfigHandler.useDefaultMaleNames) {
			villagerNameList.add(randomFromList(GlobalVariables.maleNames));
		}

		villagerNameList.removeIf(name -> name.equals(""));
		
		if (villagerNameList.isEmpty()) {
			return "";
		}

		String name = randomFromList(villagerNameList);

		if (ConfigHandler.shouldCapitalizeNames) {
			return StringFunctions.capitalizeEveryWord(name);
		}
		return name;
	}

	private static String randomFromList(List<String> list) {
		if (list.size() == 0) {
			return "";
		}
		return list.get(GlobalVariables.random.nextInt(list.size()));
	}
}
