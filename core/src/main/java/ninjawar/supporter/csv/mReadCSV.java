package ninjawar.supporter.csv;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import ninjawar.mylib.LibSysCustom;
import ninjawar.myscr.Res;
import ninjawar.supporter.SupportTranslate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class mReadCSV {
    public static void readCSV() {
        try {
            Files files = Gdx.files;
            FileHandle fileHandle = files.internal(LibSysCustom.res + "/language/" + "naruto-langue.csv");
            if (fileHandle.exists()) {
                CSVParser csvParser = getCsvParser(new BufferedReader(new InputStreamReader(new DataInputStream(new ByteArrayInputStream(fileHandle.readBytes())), StandardCharsets.UTF_8)));
                if (csvParser != null) {
                    SupportTranslate.clear();
                    Iterator<CSVRecord> it = csvParser.iterator();
                    while (it.hasNext()) {
                        CSVRecord csvRecord = it.next();
                        String id = csvRecord.get(0);
                        String variable = csvRecord.get(1);
                        String value1 = csvRecord.get(2);
                        String value2 = csvRecord.get(3);
                        String value3 = csvRecord.get(4);
                        SupportTranslate supportTranslate = new SupportTranslate();
                        supportTranslate.id = Res.convertStringToNumber(id);
                        supportTranslate.variable = variable;
                        supportTranslate.setValue1(value1);
                        supportTranslate.setValue2(value2);
                        supportTranslate.setValue3(value3);
                        SupportTranslate.addLangue(supportTranslate);
                    }
                }
                csvParser.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CSVParser getCsvParser(BufferedReader reader) {
        CSVParser csvParser = null;
        try {
            return new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withHeader("STT", "Tên biến", "Việt Nam", "English", "Indonesia").withIgnoreHeaderCase().withTrim());
        } catch (Exception e) {
            try {
                csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("STT", "Tên biến", "Việt Nam", "English", "Indonesia").withIgnoreHeaderCase().withTrim());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return csvParser;
        }
    }
}
