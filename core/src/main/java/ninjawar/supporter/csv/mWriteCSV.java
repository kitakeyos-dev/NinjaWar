package ninjawar.supporter.csv;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Vector;
import ninjawar.model.Langue1;
import ninjawar.model.Langue2;
import ninjawar.model.Langue3;
import ninjawar.model.mText;
import ninjawar.myscr.Res;
import ninjawar.supporter.SupportTranslate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class mWriteCSV {
    public static void wirteCSV() {
        try {
            Vector<SupportTranslate> vecSupportTranslates = exportLangue();
            File fCheck = new File("C:/temp/");
            if (!fCheck.exists()) {
                fCheck.mkdir();
            }
            CSVPrinter csvPrinter = new CSVPrinter(Files.newBufferedWriter(Paths.get("C:/temp/" + "naruto-langue.csv", new String[0]), new OpenOption[0]), CSVFormat.DEFAULT.withHeader("STT", "Tên biến", "Việt Nam", "English", "Indonesia"));
            int index = 0;
            Iterator<SupportTranslate> it = vecSupportTranslates.iterator();
            while (it.hasNext()) {
                SupportTranslate supportTranslate = it.next();
                csvPrinter.printRecord(Integer.valueOf(index), supportTranslate.variable, supportTranslate.getValue1(), supportTranslate.getValue2(), supportTranslate.getValue3());
                index++;
            }
            csvPrinter.flush();
            csvPrinter.close();
            System.out.println("Write file csv successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<SupportTranslate> exportLangue() {
        Vector<SupportTranslate> vecs = new Vector<>();
        for (Field field : mText.class.getFields()) {
            SupportTranslate supportTranslate = new SupportTranslate(field.getName(), field.getType().isAssignableFrom(String.class) ? SupportTranslate.TYPE_CLASS_STRING : SupportTranslate.TYPE_CLASS_STRING_ARRAY);
            Langue1.load();
            supportTranslate.value1 = Res.getStringResources(field);
            supportTranslate.values1 = Res.getStringArrayResources(field);
            Langue2.load();
            supportTranslate.value2 = Res.getStringResources(field);
            supportTranslate.values2 = Res.getStringArrayResources(field);
            Langue3.load();
            supportTranslate.value3 = Res.getStringResources(field);
            supportTranslate.values3 = Res.getStringArrayResources(field);
            vecs.add(supportTranslate);
        }
        Langue1.load();
        return vecs;
    }
}
