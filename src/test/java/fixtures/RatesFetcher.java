package fixtures;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Map;

import com.google.gson.Gson;

import model.IRatesFetcher;

public class RatesFetcher implements IRatesFetcher {

	@Override
	public Map<?, ?> getAllRates() {
		Map<?, ?> map = null;

		try {
			// create Gson instance
			Gson gson = new Gson();

			ClassLoader classLoader = this.getClass().getClassLoader();

			// create a reader
			Reader reader = Files.newBufferedReader((new File(classLoader.getResource("rates.json").getFile())).toPath());

			// convert JSON file to map
			 map = gson.fromJson(reader, Map.class);

			//print map entries
			//for (Map.Entry<?, ?> entry : map.entrySet()) {
			//	System.out.println(entry.getKey() + "=" + entry.getValue());
			//}

			// close reader
			reader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return map;
	}
}
