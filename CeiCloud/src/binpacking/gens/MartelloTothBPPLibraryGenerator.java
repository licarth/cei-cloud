package binpacking.gens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import binpacking.BPP;
import common.LibraryGenerator;

public class MartelloTothBPPLibraryGenerator implements LibraryGenerator<BPP>{

	@Override
	public List<BPP> generateInstances() {
		ArrayList<BPP> instances = new ArrayList<BPP>();

		// Directory path here
		String path = "./src/binpacking/instances_lib/MartelloToth"; 

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 

		for (int i = 0; i < listOfFiles.length; i++) 
		{
			File file = listOfFiles[i];
			if (file.isFile()) 
			{
				instances.add(generateInstance(listOfFiles[i]));
			}
		}

		return instances;
	}

	private BPP generateInstance(File file) {
		BPP p = null;
		ArrayList<Integer> itemSizes = new ArrayList<Integer>();

		String fileName = file.getName();
		if (fileName.endsWith(".bpp") || fileName.endsWith(".BPP"))
		{
			//Process file name to get problems parameters.
			System.out.println(fileName);
			Pattern patt = Pattern.compile("^N([0-9])C([0-9])W([0-9])_([A-Z]).*");
			Matcher m = patt.matcher(fileName);

			if (m.find()) {
				System.out.println(m.group(1)+m.group(2)+m.group(3)+m.group(4));
				p.setBinSize(new Integer(m.group(2)));
				p.setItemMaxSize(100);
				p.setItemMinSize(new Integer(m.group(3)));
			}

			//	Opens the file
			File f = new File("src/binpacking/instances_lib/");

			//	Lis le fichier. chaque ligne = nouvel item.
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					itemSizes.add(new Integer(line));
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return p;
	}

}
