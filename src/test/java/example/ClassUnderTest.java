package example;

/**
 * Created by besil on 09/06/2016.
 */
public class ClassUnderTest {

    private Collaborator listener;

    public void setListener(Collaborator listener) {
        this.listener = listener;
    }

    public void addDocument(String title, byte[] document) {
//        System.out.println("ClassUnderTest: Adding document");
        listener.documentAdded(title);
    }
}