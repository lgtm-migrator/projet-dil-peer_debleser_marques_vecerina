import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        String dataDir = "C:\\Heig-vd\\DIL\\projet-dil-peer_debleser_marques_vecerina\\";
        Document doc = new Document(dataDir + "README.md");

// Save the document into HTML.
        doc.save(dataDir + "Document_out.html", SaveFormat.HTML);
    }
}
