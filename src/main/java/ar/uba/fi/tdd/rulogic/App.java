package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.DBParser;
import ar.uba.fi.tdd.rulogic.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
		String dbContent = new ResourceReader("rules.db").read();
		DBParser parser = new DBParser(dbContent);
		DrKnow drKnow = new DrKnow(parser.getRules(), parser.getFacts());

		try {
			System.out.println("Starving minds, welcome to Dr. Know! Where fast food for thought"); 
			System.out.println("is served up 24 hours a day, in 40,000 locations nationwide.");
			System.out.println("Ask. Dr Know - there's nothing I don't!\n");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String query = br.readLine();
			while (query != null) {
				System.out.println(drKnow.ask(query));
				query = br.readLine();
			}
		} catch (IOException e) {
		}
    }
}
